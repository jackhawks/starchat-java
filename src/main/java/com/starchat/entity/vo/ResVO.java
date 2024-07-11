package com.starchat.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResVO<T> implements Serializable {
    private int code;
    private boolean status;
    private String userTip;
    private String errorCode;
    private String errorMessage;
    private T data;
}