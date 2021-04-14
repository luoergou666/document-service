package com.ergou.document.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    NETWORK_EXCEPTION(-4, "网络异常"),
    OPERATION_EXCEPTION(-3, "用户操作异常"),
    DATA_EXCEPTION(-2, "数据异常"),
    PDF_EXCEPTION(-3, "PDF打包异常"),
    SYSTEM_EXCEPTION(-1, "未捕捉系统异常"),
    LOGIN_ERROR(401, "登录失败"),
    AUTH_ERROR(403, "认证失败"),
    NOT_REAL_NAME(601, "未实名认证"),
    NOT_POLICY_CUSTOMER(602, "非保单客户认证"),
    SUCCESS(0, "操作成功");
    private Integer code;
    private String message;
}
