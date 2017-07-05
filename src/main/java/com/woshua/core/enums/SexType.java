package com.woshua.core.enums;

/**
 * Created by Acer on 2017/3/31.
 */
public enum SexType {
    /** 男 */
    MALE(0, "男"),
    /** 女 */
    FEMALE(1, "女"),
    /** 女 */
    SECRETE(2, "保密");

    private int value;
    private String desc;

    SexType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static SexType get(int value) {
        for (SexType sexType : SexType.values()) {
            if (sexType.value == value) {
                return sexType;
            }
        }
        throw new IllegalArgumentException("argument error: " + value);
    }

}
