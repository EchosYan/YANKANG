package me.iyk.project.common.dto.basic.page;

import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@Data
public class PageResponse {
    private Integer number;
    private Integer size;
    private Integer totalPages;
    private Boolean isFirst;
    private Boolean isLast;
    private Boolean hasNext;
    private Boolean hasPrevious;

    public PageResponse(Page page) {

        this.number = page.getNumber() + 1;
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }
}
