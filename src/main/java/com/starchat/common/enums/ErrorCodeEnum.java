package com.starchat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    ERROR_00000("00000", "一切 ok", "正确执行后的返回"),
    ERROR_A0001("A0001", "用户端错误", "一级宏观错误码"),
    ERROR_A0100("A0100", "用户注册错误", "二级宏观错误码"),
    ERROR_A0101("A0101", "用户未同意隐私协议", "二级宏观错误码");

    private final String code;
    private final String description;
    private final String explanation;
}
