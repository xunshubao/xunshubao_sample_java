package com.xunshubao.sample.vo;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;


public class ZhixingDataInfoVo extends ZxgkDataListVo {
    @Serial
    private static final long serialVersionUID = 3324139099709844768L;
    private String extra;
    private String courtName;
    private String gender;

    private String execMoney;

    private List<SifaPartyVo> parties = new ArrayList<>();

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<SifaPartyVo> getParties() {
        return parties;
    }

    public void setParties(List<SifaPartyVo> parties) {
        this.parties = parties;
    }

    public String getExecMoney() {
        return execMoney;
    }

    public void setExecMoney(String execMoney) {
        this.execMoney = execMoney;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
