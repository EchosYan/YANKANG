package me.iyk.project.common.dto.basic.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sorting {

    /**
     * desc/asc
     */
    protected String direction = "desc";

    /**
     * 排序字段
     */
    protected String property;
}
