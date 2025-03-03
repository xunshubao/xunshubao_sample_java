package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;

public class ResultVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 346016576890039856L;
    private String code;
    private String msg;
    private String requestId;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
