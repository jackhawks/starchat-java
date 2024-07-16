package com.starchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * UserVanityNumber
 *
 * @author Jack
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_vanity_number")
public class UserVanityNumber extends BaseEntity {

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
     * 用户邮箱
     */
    private String email;

    /**
     * 用户状态 (0: 未使用, 1: 已使用)
     */
    private Integer userStatus;
}