package com.momo.optlog.web.optlog;

import com.momo.optlog.services.optlog.OptLogService;
import com.momo.optlog.services.optlog.po.OptLog;
import com.momo.optlog.support.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OptLogController {

    @Autowired
    private OptLogService optLogService;

    @GetMapping("list")
    public CommonResponse list() {
        List<OptLog> optLogList = optLogService.getOptLogList();
        return CommonResponse.okResult(optLogList);
    }

}
