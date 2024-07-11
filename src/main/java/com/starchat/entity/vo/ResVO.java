package com.starchat.entity.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResVO<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;
}