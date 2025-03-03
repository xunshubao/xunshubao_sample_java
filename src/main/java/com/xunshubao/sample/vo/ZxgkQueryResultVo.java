package com.xunshubao.sample.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行公开查询结果封装
 *
 * @param <T>
 */
public class ZxgkQueryResultVo<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5611702185135221373L;
    private long pageNo = 1;
    private int pageSize = 10;
    private long total = 0;

    private String extra;

    //数据结果列表
    private List<T> list = new ArrayList<>();

    public ZxgkQueryResultVo() {
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
