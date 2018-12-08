package com.acdt.cn.its.vo;

import java.util.List;

public class GetParkFree {

    public List<ParkFreeId> getParkFreeIdLit() {
        return ParkFreeIdLit;
    }

    public void setParkFreeIdLit(List<ParkFreeId> parkFreeIdLit) {
        ParkFreeIdLit = parkFreeIdLit;
    }

    private List<ParkFreeId> ParkFreeIdLit;

    @Override
    public String toString() {
        return "GetParkFree{" +
                "ParkFreeIdLit=" + ParkFreeIdLit +
                '}';
    }
}
