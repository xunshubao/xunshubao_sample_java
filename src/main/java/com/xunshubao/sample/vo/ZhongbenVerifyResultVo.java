package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ZhongbenVerifyResultVo implements Serializable {

    @Serial
    private static final long serialVersionUID = -4912388941289743338L;
    private String extra;
    private long total;
    private long pageNo = 1;
    private int pageSize = 10;

    private BigDecimal totalExecMoney;

    private BigDecimal unperformExecMoney;

    private List<ZxgkDataListVo> list = new ArrayList<>();


    public BigDecimal getTotalExecMoney() {
        return totalExecMoney;
    }

    public void setTotalExecMoney(BigDecimal totalExecMoney) {
        this.totalExecMoney = totalExecMoney;
    }

    public BigDecimal getUnperformExecMoney() {
        return unperformExecMoney;
    }

    public void setUnperformExecMoney(BigDecimal unperformExecMoney) {
        this.unperformExecMoney = unperformExecMoney;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ZxgkDataListVo> getList() {
        return list;
    }

    public void setList(List<ZxgkDataListVo> list) {
        this.list = list;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
