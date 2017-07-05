package com.woshua.core.enums;

/**
 * Created by Acer on 2017/3/31.
 */
public enum UserType {

    /** 教师 */
    TEACHER_TYPE(0, "教师"),
    /** 学生 */
    STUDENT_TYPE(1, "学生"),
    /** 其他 */
    OTHERS_TYPE(2, "其他");

    private int value;
    private String desc;

    UserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static UserType get(int value) {
        for (UserType userType : UserType.values()) {
            if (userType.value == value) {
                return userType;
            }
        }
        throw new IllegalArgumentException("argument error: " + value);
    }
}
