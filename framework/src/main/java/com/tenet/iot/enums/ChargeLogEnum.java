package com.tenet.iot.enums;

public enum ChargeLogEnum {
    DIRECT("直流", 1),
    EXCHANGE("交流", 2),

    CHARGE_RUNNING("充电中", 1),
    CHARGE_FULL("充电完成", 2);
    private String name;
    private int value;

    ChargeLogEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
