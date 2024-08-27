package com.starchat.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.starchat.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * User
 *
 * @author Jack
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserVO extends BaseEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 地区编号
     */
    private String regionCode;

    /**
     * 性别 (0: 女, 1: 男)
     */
    private Integer gender;

    /**
     * 加入类型 (0: 直接加入, 1: 同意后加好友)
     */
    private Integer joinType;

    /**
     * token
     */
    private String token;

    /**
     * admin
     */
    private Boolean admin;

    /**
     * contactStatus
     */
    private Integer contactStatus;
}