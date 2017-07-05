package com.woshua.core.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/4/19.
 */
public enum PaperType {
    /**
     * 随堂练习
     */
    PRACTICE_ON_THE_CLASS(0, 0, "随堂练习"),

    /**
     * 单元测试
     */
    UNIT_TESTING(1, 0, "单元测试"),

    /**
     * 正规考试
     */
    MAIN_TESTING(2, 1, "正规考试");

    private int value;
    private int type;
    private String desc;

    PaperType(int value, int type, String desc) {
        this.value = value;
        this.desc = desc;
        this.type = type;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getType() {
        return type;
    }

    public static PaperType get(int value) {
        for (PaperType PaperType : PaperType.values()) {
            if (PaperType.value == value) {
                return PaperType;
            }
        }
        throw new IllegalArgumentException("argument error: " + value);
    }

    public static List<PaperType> getTypes() {
        List<PaperType> result = new ArrayList<>();
        for (PaperType PaperType : PaperType.values()) {
            result.add(PaperType.get(PaperType.value));
        }

        return result;
    }
}
