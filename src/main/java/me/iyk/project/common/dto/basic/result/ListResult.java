package me.iyk.project.common.dto.basic.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created on 2020/7/6.
 * 列表对象返回包装类
 *
 * @author Echos
 */
@NoArgsConstructor
@Data
public class ListResult<T> extends BasicResult {
    private List<T> data;

    public ListResult(List<T> data) {
        super();
        this.data = data;
    }
}
