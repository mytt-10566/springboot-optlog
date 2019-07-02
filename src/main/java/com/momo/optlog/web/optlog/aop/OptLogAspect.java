package com.momo.optlog.web.optlog.aop;

import com.momo.optlog.services.optlog.OptLogService;
import com.momo.optlog.services.optlog.dto.OptLogAddDTO;
import com.momo.optlog.services.user.UserService;
import com.momo.optlog.services.user.po.User;
import com.momo.optlog.support.util.IpUtil;
import com.momo.optlog.support.util.JsonUtil;
import com.momo.optlog.web.optlog.annotation.OptLogger;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class OptLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptLogAspect.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OptLogService optLogService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 定义切点，表示标注了OptLogger注解的目标类方法
     */
    @Pointcut("@annotation(com.momo.optlog.web.optlog.annotation.OptLogger)")
    public void optLogAspect() {
    }

    /**
     * 前置通知 用于拦截有OptLogger注解注释的方法
     *
     * @param joinPoint 连接点
     * @return
     */
    @Before("optLogAspect()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            String token = request.getParameter("token");
            if (StringUtils.isBlank(token)) {
                return;
            }
            User user = userService.getByToken(token);
            if (user == null) {
                return;
            }

            String ip = IpUtil.getRemoteHost(request);
            String opt = getOptLogMethodDesc(joinPoint);
            String args = JsonUtil.obj2json(getMethodArgs(joinPoint));
            this.recordOptLog(ip, user.getUserName(), opt, args);
        } catch (Exception e) {
            LOGGER.error("record admin opt fail", e);
        }
    }

    private void recordOptLog(String ip, String userName, String opt, String args) {
        taskExecutor.submit(() -> {
            OptLogAddDTO optLogDTO = OptLogAddDTO.builder()
                    .userName(userName)
                    .opt(opt)
                    .ip(ip)
                    .args(args)
                    .build();
            if (!optLogService.add(optLogDTO)) {
                LOGGER.error("save opt log fail, params={}", JsonUtil.obj2json(optLogDTO));
            }
        });
    }


    /**
     * 获取OptLogger注解的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws NoSuchMethodException
     */
    public static String getOptLogMethodDesc(JoinPoint joinPoint) throws NoSuchMethodException {
        // 获取拦截的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        Class[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        Method method = methodSignature.getDeclaringType().getMethod(methodName, parameterTypes);

        // 方法上的注解
        boolean isPresent = method.isAnnotationPresent(OptLogger.class);
        if (isPresent) {
            OptLogger optLogger = method.getAnnotation(OptLogger.class);
            return optLogger.value();
        }
        return "";
    }


    /**
     * 获取方法参数
     *
     * @param joinPoint
     * @return
     */
    public static List<Object> getMethodArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return new ArrayList<>(0);
        }

        return Arrays.stream(args)
                .filter(arg ->
                        !(arg instanceof Model)
                                && !(arg instanceof ModelMap)
                                && !(arg instanceof BeanPropertyBindingResult)
                                && !(arg instanceof MultipartFile))
                .collect(Collectors.toList());
    }
}
