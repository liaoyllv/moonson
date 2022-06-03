package com.jf.moonson.common.api;

import lombok.Data;

/**
 * 返回数据
 */
@Data
public class R<T> {
    private static final long serialVersionUID = 1L;

    protected static final String DEFAULT_SUCCESS_CODE = "0";
    private static final String DEFAULT_FAIL_CODE = "-1";

    /**
     * 返回码
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    private R() {
        code = DEFAULT_SUCCESS_CODE;
        msg = "";
    }

    private R(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static R error() {
        return error(DEFAULT_FAIL_CODE, ApiErrorEnum.DEFAULT.getCode());
    }

    public static R error(ApiErrorEnum errorEnum) {
        return error(DEFAULT_FAIL_CODE, errorEnum.getDesc());
    }

    public static R error(String code, String msg) {
        return new R(code, msg);
    }

    public static R<Void> ok() {
        return new R();
    }

    public static <T> R<T> ok(T o) {
        R<T> r = new R();
        r.setData(o);
        return r;
    }

}
