package me.iyk.project.common.dto.basic.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.iyk.project.common.exception.ErrorCode;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@NoArgsConstructor
@Data
public class Result<T> extends BasicResult {

    protected T data;

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getDesc());
    }

    public Result(ErrorCode errorCode, String msg) {
        super(errorCode.getCode(), msg);
    }

    public Result(ErrorCode errorCode, T data) {
        this(errorCode);
        this.data = data;
    }
}
