package com.jf.moonson.common.enums.code2enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * code与enum之间互转：前端到web端，db到repo。将code类型的字段定义为枚举类型
 *
 * @author LiuGong
 * @version $
 * @since 2019年11月29日 18:59
 */
@JsonFormat(shape = Shape.OBJECT)
@JsonDeserialize(using = CodeEnumDeserializer.class)
public interface BaseCodeEnum {
    int getCode();
}
