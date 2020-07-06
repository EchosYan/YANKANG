package me.iyk.project.model.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created on 2020/7/5.
 *
 * @author Echos
 */
@Data
public class UserVO {
    protected Long id;
    protected String username;
    protected String password;
    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;
}
