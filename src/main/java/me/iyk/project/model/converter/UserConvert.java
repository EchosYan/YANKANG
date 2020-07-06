package me.iyk.project.model.converter;

import me.iyk.project.common.dto.basic.VoConverter;
import me.iyk.project.model.dto.response.UserVO;
import me.iyk.project.model.entity.User;
import org.mapstruct.Mapper;

/**
 * Created on 2020/7/5.
 *
 * @author Echos
 */
@Mapper(componentModel = "spring")
public interface UserConvert extends VoConverter<User, UserVO> {
}
