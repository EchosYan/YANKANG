package me.iyk.project.service;

/**
 * Created on 2020/7/5.
 *
 * @author Echos
 */

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import me.iyk.project.common.exception.BizException;
import me.iyk.project.common.exception.ErrorCode;
import me.iyk.project.model.converter.UserConvert;
import me.iyk.project.model.dto.request.UserRequest;
import me.iyk.project.model.dto.response.UserVO;
import me.iyk.project.model.entity.QUser;
import me.iyk.project.model.entity.User;
import me.iyk.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConvert userConvert;

    public UserVO get(Long id) {
        return userConvert.to(userRepository.getOne(id));
    }

    public UserVO login(UserRequest userRequest) {

        QUser qUser = QUser.user;
        BooleanExpression expression = qUser.isNotNull().or(qUser.isNull());
        expression.and(qUser.username.eq(userRequest.getUsername()));

        Optional<User> one = userRepository.findOne(expression);
        if (!one.isPresent()) {
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }

        User user = one.get();
        if (!user.getPassword().equals(userRequest.getPassword())) {
            throw new BizException(ErrorCode.USER_NOT_EXISTS);
        }

        // todo:其他检查项

        return userConvert.to(user);
    }
}
