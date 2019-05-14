package com.tenet.iot.enums;

public enum ChargeLogEnum {
    DIRECT("直流", 1),
    EXCHANGE("交流", 2),

    CHARGE_FULL("充满", 0),
    CHARGE_MONEY("金额控制", 2),
    CHARGE_ELECTRIC("电量控制", 3);
    private String index;
    private int value;

    ChargeLogEnum(String name, int value) {
        this.index = index;
        this.value = value;
    }

    public String getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

}
