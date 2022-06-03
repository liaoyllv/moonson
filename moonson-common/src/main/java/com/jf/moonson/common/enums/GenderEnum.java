package com.jf.moonson.common.enums;

import com.jf.moonson.common.enums.code2enum.BaseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum implements BaseCodeEnum {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    private final int code;
    private final String desc;
}
