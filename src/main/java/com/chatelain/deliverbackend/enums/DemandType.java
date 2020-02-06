package com.chatelain.deliverbackend.enums;

public enum DemandType {

    TAKEAWAY("取外卖"),
    TRACKING("领快递"),
    TRASH("倒垃圾"),
    BUY("买东西"),
    OTHERS("其它");

    private String name;

    DemandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
