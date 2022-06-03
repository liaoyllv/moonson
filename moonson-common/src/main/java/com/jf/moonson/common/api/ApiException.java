package com.jf.moonson.common.api;

import org.apache.commons.lang3.StringUtils;

/**
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errCode;
    private String message;
    private Object[] args;

    public ApiException(ApiErrorEnum errorEnum, Object... args) {
        this.errCode = errorEnum.getCode();
        this.message = errorEnum.getDesc();
        this.args = args;
        this.resolveMessage();
    }

    private void resolveMessage() {
        if (StringUtils.isBlank(this.message) || this.args == null || this.args.length < 1) {
            return;
        }
        this.message = String.format(this.message, this.args);
    }
}
