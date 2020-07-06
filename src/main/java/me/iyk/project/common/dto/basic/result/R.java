package me.iyk.project.common.dto.basic.result;

import lombok.Data;
import me.iyk.project.common.exception.ErrorCode;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created on 2020/6/29.
 * 单数据返回包装类
 *
 * @author Echos
 */
@Data
public class R<T> extends BasicResult {

    /**
     * 无数据返回
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result<>(null);
    }

    /**
     * 返回单数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    /**
     * 返回列表数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ListResult<T> ok(List<T> data) {
        return new ListResult<>(data);
    }

    /**
     * 返回分页数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> ok(Page<T> data) {
        return new PageResult<>(data);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    public static <T> Result<T> error(ErrorCode errorCode, String msg) {
        return new Result<>(errorCode, msg);
    }

    public static <T> Result<T> error(ErrorCode errorCode, T data) {
        return new Result<>(errorCode, data);
    }
}
