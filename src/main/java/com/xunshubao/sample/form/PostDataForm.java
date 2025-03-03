package com.xunshubao.sample.form;

import java.io.Serial;
import java.io.Serializable;

public class PostDataForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1299000167144716265L;

    public PostDataForm() {
    }

    public PostDataForm(RequestHeader requestHeader, String requestBody) {
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    private RequestHeader requestHeader;

    private String requestBody;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
