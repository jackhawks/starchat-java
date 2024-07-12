package com.starchat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessagingAppFeatureEnum {
    FRIEND(1, 'F', "好友"),
    GROUP(2, 'G', "群组"),
    CHANNEL(3, 'C', "频道"),
    BOT(4, 'B', "机器人");

    private final int type;
    private final char prefix;
    private final String description;
}
