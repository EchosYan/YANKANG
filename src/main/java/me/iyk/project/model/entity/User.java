package me.iyk.project.model.entity;

import lombok.Data;
import me.iyk.project.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created on 2020/7/5.
 *
 * @author Echos
 */
@Table(name = "user")
@Entity
@Data
public class User extends BaseEntity {
    private String username;
    private String password;
}
