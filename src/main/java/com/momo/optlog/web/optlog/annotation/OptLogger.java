package com.momo.optlog.web.optlog.annotation;

import java.lang.annotation.*;

/**
 * 操作日志记录器
 *
 * @author moqinggen
 * @date 2019/07/01
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLogger {

    /**
     * 描述信息
     */
    String value() default "";

}
