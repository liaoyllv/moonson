package com.jf.moonson.common.enums;

import com.jf.moonson.common.enums.code2enum.BaseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BooleanEnum implements BaseCodeEnum {

    NO(0, "否"),
    YES(1, "是"),
    ;
    private final int code;
    private final String desc;
}
