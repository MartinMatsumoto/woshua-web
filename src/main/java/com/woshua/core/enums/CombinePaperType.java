package com.woshua.core.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/4/19.
 */
public enum CombinePaperType {

    /**
     * 手工
     */
    HANDMADE(0,"手工组卷"),

    /**
     * 自动
     */
    AUTOMATIC(1,"智能组卷");

    private int value;
    private String desc;

    CombinePaperType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static CombinePaperType get(int value) {
        for (CombinePaperType combinePaperType : CombinePaperType.values()) {
            if (combinePaperType.value == value) {
                return combinePaperType;
            }
        }
        throw new IllegalArgumentException("argument error: " + value);
    }

    public static List<CombinePaperType> getTypes(){
        List<CombinePaperType> result = new ArrayList<>();
        for (CombinePaperType combinePaperType : CombinePaperType.values()) {
            result.add(CombinePaperType.get(combinePaperType.value));
        }

        return result;
    }
}
