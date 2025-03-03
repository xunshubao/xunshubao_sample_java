package com.xunshubao.sample.vo;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ZhongbenDataInfoVo extends ZxgkDataListVo {
    @Serial
    private static final long serialVersionUID = -6821926108397752407L;
    private String extra;
    private String courtName;
    private String execMoney;
    private String unperformPart;
    private String endDate;

    private List<SifaPartyVo> parties = new ArrayList<>();

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getUnperformPart() {
        return unperformPart;
    }

    public void setUnperformPart(String unperformPart) {
        this.unperformPart = unperformPart;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
