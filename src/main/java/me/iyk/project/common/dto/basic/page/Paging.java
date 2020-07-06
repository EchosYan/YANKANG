package me.iyk.project.common.dto.basic.page;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@Data
public class Paging extends Sorting {
    /**
     * 页大小
     */
    private Integer size = 10;

    /**
     * 页数
     */
    private Integer number = 1;

    /**
     * 排序（多字段）
     */
    private List<Sorting> sorts;

    public static PageRequest toJpaPageRequest(Paging paging) {
        // 默认根据ID倒序排列
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        if (null == paging) {
            return PageRequest.of(0, 10, sort);
        }

        if (null != paging.property) {
            paging.sorts = Arrays.asList(new Sorting(paging.direction, paging.property));
        }

        if (null != paging.sorts && !paging.sorts.isEmpty()) {
            List<Sort.Order> orders = new ArrayList<>();
            for (Sorting sorting : paging.sorts) {
                orders.add(new Sort.Order(Sort.Direction.fromString(sorting.getDirection()), sorting.getProperty()));
            }
            sort = Sort.by(orders);
        }

        return PageRequest.of(paging.getNumber() - 1, paging.getSize(), sort);
    }

}
