package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;

public class ZxgkDataListVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 6756265164082543251L;
    private String dataId;

    private String dataType;
    //案号
    private String caseCode;
    //立案日期
    private String regDate;
    //姓名 & 企业名称
    private String name;

    private String cardNum;
    private String delist;
    private String publishDate;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getDelist() {
        return delist;
    }

    public void setDelist(String delist) {
        this.delist = delist;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
