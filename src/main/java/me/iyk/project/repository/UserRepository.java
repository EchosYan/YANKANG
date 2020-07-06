package me.iyk.project.repository;

import com.querydsl.core.types.dsl.StringExpression;
import me.iyk.project.model.entity.QUser;
import me.iyk.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020/6/29.
 *
 * @author Echos
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
    @Override
    default void customize(QuerydslBindings bindings, QUser qUser) {
        bindings.bind(qUser.username).first(StringExpression::contains);
    }
}
