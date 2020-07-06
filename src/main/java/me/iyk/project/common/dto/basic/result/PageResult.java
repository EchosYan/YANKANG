package me.iyk.project.common.dto.basic.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.iyk.project.common.dto.basic.page.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@NoArgsConstructor
@Data
public class PageResult<T> extends BasicResult {
    private PageResponse page;
    private List<T> data;

    public PageResult(Page<T> data) {
        super();
        this.data = data.getContent();
        this.page = new PageResponse(data);
    }

}
