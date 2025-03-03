package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ShixinVerifyResultVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 2182783563828435607L;
    private String extra;
    private long total;
    private long pageNo = 1;
    private int pageSize = 10;
    /**
     * 全部未履行数量
     */
    private long allUnperformQty;

    private List<ZxgkDataListVo> list = new ArrayList<>();

    public long getAllUnperformQty() {
        return allUnperformQty;
    }

    public void setAllUnperformQty(long allUnperformQty) {
        this.allUnperformQty = allUnperformQty;
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
