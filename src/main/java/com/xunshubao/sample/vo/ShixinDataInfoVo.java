package com.xunshubao.sample.vo;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ShixinDataInfoVo extends ZxgkDataListVo {
    @Serial
    private static final long serialVersionUID = -7682399468997430780L;
    private String extra;
    private String courtName;
    private String areaName;
    private String gistCaseCode;
    private String gistUnit;
    private String duty;
    private String performance;
    private String performedPart;
    private String unperformPart;
    private String disruptTypeName;
    private String age;
    private String gender;
    private String businessEntity;

    private List<SifaPartyVo> parties = new ArrayList<>();

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getGistCaseCode() {
        return gistCaseCode;
    }

    public void setGistCaseCode(String gistCaseCode) {
        this.gistCaseCode = gistCaseCode;
    }

    public String getGistUnit() {
        return gistUnit;
    }

    public void setGistUnit(String gistUnit) {
        this.gistUnit = gistUnit;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getPerformedPart() {
        return performedPart;
    }

    public void setPerformedPart(String performedPart) {
        this.performedPart = performedPart;
    }

    public String getUnperformPart() {
        return unperformPart;
    }

    public void setUnperformPart(String unperformPart) {
        this.unperformPart = unperformPart;
    }

    public String getDisruptTypeName() {
        return disruptTypeName;
    }

    public void setDisruptTypeName(String disruptTypeName) {
        this.disruptTypeName = disruptTypeName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    public List<SifaPartyVo> getParties() {
        return parties;
    }

    public void setParties(List<SifaPartyVo> parties) {
        this.parties = parties;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
