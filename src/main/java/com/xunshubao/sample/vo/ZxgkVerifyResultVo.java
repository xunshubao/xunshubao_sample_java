package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行公开核验接口
 */
public class ZxgkVerifyResultVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5804718445587654179L;
    private long pageNo = 1;
    private int pageSize = 10;
    private long total;

    private String extra;

    // 失信记录数
    private long shixinTotal;
    // 失信全都未执行
    private long shixinAllUnperformQty;

    // 限高令记录数
    private long xglTotal;

    //执行总记录数
    private long zhixingTotal;

    //累计被执行金额
    private BigDecimal zhixingTotalExecMoney;

    private long zhongbenTotal;

    private BigDecimal zhongbenTotalExecMoney;

    private BigDecimal zhongbenUnperformExecMoney;

    private List<ZxgkDataListVo> list = new ArrayList<>();

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getShixinTotal() {
        return shixinTotal;
    }

    public void setShixinTotal(long shixinTotal) {
        this.shixinTotal = shixinTotal;
    }

    public long getShixinAllUnperformQty() {
        return shixinAllUnperformQty;
    }

    public void setShixinAllUnperformQty(long shixinAllUnperformQty) {
        this.shixinAllUnperformQty = shixinAllUnperformQty;
    }

    public long getXglTotal() {
        return xglTotal;
    }

    public void setXglTotal(long xglTotal) {
        this.xglTotal = xglTotal;
    }

    public long getZhixingTotal() {
        return zhixingTotal;
    }

    public void setZhixingTotal(long zhixingTotal) {
        this.zhixingTotal = zhixingTotal;
    }

    public BigDecimal getZhixingTotalExecMoney() {
        return zhixingTotalExecMoney;
    }

    public void setZhixingTotalExecMoney(BigDecimal zhixingTotalExecMoney) {
        this.zhixingTotalExecMoney = zhixingTotalExecMoney;
    }

    public long getZhongbenTotal() {
        return zhongbenTotal;
    }

    public void setZhongbenTotal(long zhongbenTotal) {
        this.zhongbenTotal = zhongbenTotal;
    }

    public BigDecimal getZhongbenTotalExecMoney() {
        return zhongbenTotalExecMoney;
    }

    public void setZhongbenTotalExecMoney(BigDecimal zhongbenTotalExecMoney) {
        this.zhongbenTotalExecMoney = zhongbenTotalExecMoney;
    }

    public BigDecimal getZhongbenUnperformExecMoney() {
        return zhongbenUnperformExecMoney;
    }

    public void setZhongbenUnperformExecMoney(BigDecimal zhongbenUnperformExecMoney) {
        this.zhongbenUnperformExecMoney = zhongbenUnperformExecMoney;
    }

    public List<ZxgkDataListVo> getList() {
        return list;
    }

    public void setList(List<ZxgkDataListVo> list) {
        this.list = list;
    }

}
