package me.iyk.project.controller;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.iyk.project.common.anotation.RateLimit;
import me.iyk.project.common.dto.basic.page.Paging;
import me.iyk.project.common.dto.basic.result.ListResult;
import me.iyk.project.common.dto.basic.result.PageResult;
import me.iyk.project.common.dto.basic.result.R;
import me.iyk.project.common.dto.basic.result.Result;
import me.iyk.project.model.converter.UserConvert;
import me.iyk.project.model.dto.request.UserRequest;
import me.iyk.project.model.dto.response.UserVO;
import me.iyk.project.model.entity.QUser;
import me.iyk.project.model.entity.User;
import me.iyk.project.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2020/7/5.
 *
 * @author Echos
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserConvert userConvert;
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @RateLimit(key = "userGetRateLimit", period = 100, count = 10)
    @GetMapping("/{id}")
    public Result<UserVO> get(@PathVariable("id") Long id) {
        return R.ok(userConvert.to(userRepository.getById(id)));
    }

    @GetMapping("list")
    public ListResult<UserVO> list() {
        List<UserVO> all = userConvert.to(userRepository.findAll());
        return R.ok(all);
    }

    @GetMapping("page")
    public PageResult<UserVO> page(@QuerydslPredicate(root = User.class) Predicate predicate, @Validated UserRequest userRequest, Paging page) {
        /*QUser user = QUser.user;
        QUser user2 = QUser.user;
        BooleanExpression where = user.username.containsIgnoreCase(userRequest.getUsername());

        List<Tuple> results = jpaQueryFactory
                .select(user)
                .leftJoin(user2).on(user2.id.eq(user.id))
                .fetchResults().getResults();
        results.get(1).get()*/

        // Projections.bean()

        QUser user = QUser.user;
        Page<UserVO> all = userRepository.findAll(
                predicate,
                Paging.toJpaPageRequest(page)
        ).map(userConvert::to);
        return R.ok(all);
    }
}
