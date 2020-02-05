package com.chatelain.deliverbackend.enums;

public enum DemandStatus {
    PENDING("待领取"),
    ONGOING("进行中"),
    COMPLETE("已完成");


    private String name;

    DemandStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isBehind(DemandStatus anotherStatus) {
        if (anotherStatus == null || this == PENDING) {
            return false;
        } else if (this == ONGOING) {
            return anotherStatus == PENDING;
        } else {
            return anotherStatus == ONGOING;
        }
    }
}
