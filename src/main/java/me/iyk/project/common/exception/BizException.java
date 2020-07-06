package me.iyk.project.common.exception;

import lombok.Getter;

/**
 * Created on 2020/6/29.
 *
 * @author Echos
 */
@Getter
public class BizException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Object data;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.data = null;
    }

    public BizException(ErrorCode errorCode, Object data) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.data = data;
    }


}
