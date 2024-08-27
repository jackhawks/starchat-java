package com.starchat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  JoinTypeEnum {
    JOIN(0, "直接加入"),
    APPLY(1, "需要审核");

    private final int type;
    private final String desc;
}
