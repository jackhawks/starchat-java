package com.starchat.controller;

import com.starchat.entity.vo.ResVO;

/**
 * Base Controller
 *
 * @author Jack
 */
public class BaseController {

    protected <T> ResVO<T> success() {
        return new ResVO<>(200, true, "操作成功", null);
    }

    protected <T> ResVO<T> success(T data) {
        return new ResVO<>(200, true, "操作成功", data);
    }

    protected <T> ResVO<T> success(String message, T data) {
        return new ResVO<>(200, true, message, data);
    }

    protected <T> ResVO<T> error(int code, String message) {
        return new ResVO<>(code, false, message, null);
    }

    protected <T> ResVO<T> error(int code, String message, T data) {
        return new ResVO<>(code, false, message, data);
    }

    protected <T> ResVO<T> error(String message) {
        return new ResVO<>(500, false, message, null);
    }
}
