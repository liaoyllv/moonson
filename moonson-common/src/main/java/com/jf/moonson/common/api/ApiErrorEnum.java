package com.jf.moonson.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum ApiErrorEnum {

    /**
     * 【强制】全部正常，但不得不填充错误码时返回五个零:00000。
     * 【强制】错误码为字符串类型，共5位，分成两个部分:错误产生来源+四位数字编号。
     * 说明:错误产生来源分为 A/B/C，
     * A 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题;
     * B 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付 超时等问题;
     * C 表示错误来源 于第三方服务，比如 CDN 服务出错，消息投递超时等问题;四位数字编号从 0001 到 9999，大类之间的 步长间距预留 100
     * A0001～A0099 A0100~A0199
     */
    //@formatter:off
    DEFAULT("00000", "服务器繁忙，请稍后重试！"),

    // 系统级别
    SYS_OPERATION_LIMIT("A0001", "操作过快，请稍后再试~"),
    SYS_NO_AUTHORITY_SEE("A0002", "无查看权限"),
    SYS_SMS_CODE_ERROR("A0200", "验证码错误"),

    // 用户
    USER_NOT_FOUND("B0001", "用户不存在"),
    USER_REGISTERED("B0002", "用户已注册"),
    USER_MOBILE_EXISTED("B0003", "手机号已注册"),
    USER_REGISTER_FAILED("B0004", "用户注册失败"),
    USER_LOGIN_FAILED_WITH_PASSWORD("B0005", "用户名或者密码错误"),
    USER_LOGIN_EXPIRED_FAILED("B0006", "登录状态已过期"),
    USER_LOGIN_FAILED("B0007", "登录失败"),

    ;
    //@formatter:on

    private final String code;
    private final String desc;

}
