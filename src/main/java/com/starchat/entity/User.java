package com.starchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class User extends BaseEntity {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户密码
     */
    private String password;

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
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 最后离线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastOfflineTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}