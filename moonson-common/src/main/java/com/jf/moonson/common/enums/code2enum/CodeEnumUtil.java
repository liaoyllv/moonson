package com.jf.moonson.common.enums.code2enum;

/**
 * @author LiuGong
 * @version $
 * @since 2019年11月29日 19:21
 */
public class CodeEnumUtil {

    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<?> & BaseCodeEnum> E ofName(Class<E> enumClass, String name) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
