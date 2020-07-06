package me.iyk.project.config;

import lombok.extern.slf4j.Slf4j;
import me.iyk.project.common.dto.basic.result.R;
import me.iyk.project.common.dto.basic.result.Result;
import me.iyk.project.common.exception.BizException;
import me.iyk.project.common.exception.ErrorCode;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created on 2020/6/29.
 *
 * @author Echos
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {BizException.class})
    public Result bizException(BizException ex) {
        log.error("", ex);
        return R.error(ex.getErrorCode(), ex.getData());
    }

    /**
     * 参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public Result bindException(BindException ex) {
        log.error("", ex);
        return R.error(ErrorCode.PARAM_ERROR, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public Result exception(Exception ex) {
        log.error("", ex);
        return R.error(ErrorCode.EXCEPTION, "unknown exception");
    }
}
