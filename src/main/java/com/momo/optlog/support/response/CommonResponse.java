package com.momo.optlog.support.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse {

    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS_MSG = "success";

    private int code = SUCCESS_CODE;

    private String msg = SUCCESS_MSG;

    private Object data;

    public static CommonResponse okResult(Object data) {
        return okResult(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static CommonResponse okResult(String msg, Object data) {
        return okResult(SUCCESS_CODE, msg, data);
    }

    public static CommonResponse okResult(int code, String msg, Object data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(code);
        commonResponse.setMsg(msg);
        commonResponse.setData(data);
        return commonResponse;
    }
}
