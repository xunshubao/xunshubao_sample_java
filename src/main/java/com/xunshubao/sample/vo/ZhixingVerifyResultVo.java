package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ZhixingVerifyResultVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -3793065254469701788L;
    private String extra;
    private long total;
    private long pageNo = 1;
    private int pageSize = 10;

    /**
     * 累计被执行金额
     */
    private BigDecimal totalExecMoney;

    private List<ZxgkDataListVo> list = new ArrayList<>();

    public BigDecimal getTotalExecMoney() {
        return totalExecMoney;
    }

    public void setTotalExecMoney(BigDecimal totalExecMoney) {
        this.totalExecMoney = totalExecMoney;
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
