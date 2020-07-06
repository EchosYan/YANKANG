package me.iyk.project.common.exception;

import lombok.Getter;

/**
 * Created on 2020/6/29.
 *
 * @author Echos
 */
@Getter
public enum ErrorCode {

    // 成功
    SUCCESS(0, "success"),
    PARAM_ERROR(10001, "参数错误"),
    AUTH_ERROR(10002, "身份认证错误"),
    USER_NOT_EXISTS(10003, "用户不存在"),
    EXCEPTION(99999, "未知错误"),
    ;

    private final int code;
    private final String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
