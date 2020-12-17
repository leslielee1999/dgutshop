package org.dgutstu.dgutshop.db.domain;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 15:49 2020/12/16
 */
public class OrderItemVo {
    private int cid;
    private int[] pids;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int[] getPids() {
        return pids;
    }

    public void setPids(int[] pids) {
        this.pids = pids;
    }

    @Override
    public String toString() {
        return "OrderItemVo{" +
                "cid=" + cid +
                ", pids=" + Arrays.toString(pids) +
                '}';
    }
}
