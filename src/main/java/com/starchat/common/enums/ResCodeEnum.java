package com.starchat.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResCodeEnum {
    CODE_200(200, "请求成功"),
    CODE_404(404, "请求地址不存在"),
    CODE_500(500, "服务器错误，请联系系统管理员"),
    CODE_600(501, "请求参数错误"),
    CODE_601(502, "信息已经存在"),
    CODE_602(503, "文件不存在"),
    CODE_901(504, "登录超时"),
    CODE_902(505, "您不是对方的好友，请先发送好友申请"),
    CODE_903(506, "您已退出群聊，需要重新加入");

    private final int code;
    private final String message;
}
