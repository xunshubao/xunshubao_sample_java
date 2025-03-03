package com.xunshubao.sample.vo;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class XglDataInfoVo extends ZxgkDataListVo {
    @Serial
    private static final long serialVersionUID = 6388796621471772368L;
    private String extra;
    private String courtName;
    private String areaName;
    private String gender;
    private String companyName;

    private String filePath;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
