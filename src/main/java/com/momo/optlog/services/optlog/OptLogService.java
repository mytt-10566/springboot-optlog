package com.momo.optlog.services.optlog;

import com.momo.optlog.services.optlog.dto.OptLogAddDTO;
import com.momo.optlog.services.optlog.po.OptLog;
import com.momo.optlog.support.util.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员操作日志
 *
 * @author moqinggen
 * @date 2019/07/01
 */
@Service
public class OptLogService {

    /**
     * 新增操作日志
     *
     * @param optLogDTO
     * @return
     */
    public boolean add(OptLogAddDTO optLogDTO) {
        OptLog optLog = ModelMapperUtil.strictMap(optLogDTO, OptLog.class);
        optLog.setCreateTime(System.currentTimeMillis());
        optLog.setUpdateTime(System.currentTimeMillis());
        // TODO 保存
        return true;
    }

    public List<OptLog> getOptLogList() {
        return null;
    }
}
