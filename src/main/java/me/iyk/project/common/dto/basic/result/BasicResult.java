package me.iyk.project.common.dto.basic.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.iyk.project.common.exception.ErrorCode;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@AllArgsConstructor
@Data
public class BasicResult {
    protected int code;
    protected String msg;

    public BasicResult() {
        this.code = ErrorCode.SUCCESS.getCode();
        this.msg = ErrorCode.SUCCESS.getDesc();
    }
}
