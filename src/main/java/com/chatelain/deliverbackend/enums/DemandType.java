package com.chatelain.deliverbackend.enums;

public enum DemandType {

    PULL("取外卖"),
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
