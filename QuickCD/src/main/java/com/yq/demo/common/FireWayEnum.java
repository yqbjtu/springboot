package com.yq.demo.common;

public enum FireWayEnum {
    // 状态，1， manual， 2，schedule， 3，code changes trigger， 4， triggerd by other
    // project
    MANUAL("Manual", 1), SCHEDULE("Schedule", 2), CHANGES("Changes", 3), PROJECT(
            "Project", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private FireWayEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getNameByIndex(int index) {
        for (FireWayEnum c : FireWayEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static FireWayEnum getByIndex(int index) {
        for (FireWayEnum c : FireWayEnum.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
