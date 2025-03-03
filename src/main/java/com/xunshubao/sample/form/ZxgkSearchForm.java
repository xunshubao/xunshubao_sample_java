package com.xunshubao.sample.form;

import java.io.Serial;
import java.io.Serializable;

public class ZxgkSearchForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 5807048077207352959L;

    public ZxgkSearchForm() {

    }

    public ZxgkSearchForm(String requestId, String name) {
        this.requestId = requestId;
        this.name = name;
    }

    public ZxgkSearchForm(String requestId, String name, String cardNum) {
        this.requestId = requestId;
        this.name = name;
        this.cardNum = cardNum;
    }

    public ZxgkSearchForm(String requestId, String name, String cardNum, String hashParam, String hashType) {
        this.requestId = requestId;
        this.name = name;
        this.cardNum = cardNum;
        this.hashParam = hashParam;
        this.hashType = hashType;
    }

    private String requestId;
    private String name;
    private String cardNum;
    private String hashParam;
    private String hashType;
    private String dataType;
    private String publishDate;
    private String publishFromDate;
    private String publishToDate;
    private String delist;
    private String caseCode;
    private String pageNo;
    private String pageSize;
    private String extra;

    private String dataId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getHashParam() {
        return hashParam;
    }

    public void setHashParam(String hashParam) {
        this.hashParam = hashParam;
    }

    public String getHashType() {
        return hashType;
    }

    public void setHashType(String hashType) {
        this.hashType = hashType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishFromDate() {
        return publishFromDate;
    }

    public void setPublishFromDate(String publishFromDate) {
        this.publishFromDate = publishFromDate;
    }

    public String getPublishToDate() {
        return publishToDate;
    }

    public void setPublishToDate(String publishToDate) {
        this.publishToDate = publishToDate;
    }

    public String getDelist() {
        return delist;
    }

    public void setDelist(String delist) {
        this.delist = delist;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
