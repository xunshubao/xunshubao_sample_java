package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;

public class SifaPartyVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -7188165015761618162L;
    private String partyType;
    private String partyName;
    private String partyCode;

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }
}
