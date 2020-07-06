package me.iyk.project.model.dto.request;

import lombok.Data;
import me.iyk.project.model.dto.response.UserVO;
import org.hibernate.validator.constraints.Length;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@Data
public class UserRequest extends UserVO {

    @Length(max = 10)
    private String username;
}
