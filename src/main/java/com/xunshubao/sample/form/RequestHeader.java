package com.xunshubao.sample.form;

import java.io.Serial;
import java.io.Serializable;

public class RequestHeader implements Serializable {
    @Serial
    private static final long serialVersionUID = 2677654233904412421L;

    public RequestHeader() {
    }

    public RequestHeader(String appKey, String timestamp, String token,
                         String signType, String encryption, String requestId) {
        this.appKey = appKey;
        this.timestamp = timestamp;
        this.token = token;
        this.signType = signType;
        this.requestId = requestId;
        this.encryption = encryption;
    }

    private String appKey;
    private String timestamp;
    private String token;
    private String signType;
    private String requestId;
    private String encryption;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }
}
