package com.acdt.cn.its.vo;

import java.util.List;

public class GetBusStation {
    private List<BusStation> BusTionList;


    public List<BusStation> getBusTionList() {
        return BusTionList;
    }

    public void setBusTionList(List<BusStation> busTionList) {
        BusTionList = busTionList;
    }

    @Override
    public String toString() {
        return "GetBusStation{" +
                "BusTionList=" + BusTionList +
                '}';
    }
}
