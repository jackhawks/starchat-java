package com.starchat.controller;

import com.starchat.entity.vo.ResVO;

import java.util.Collections;
import java.util.Map;

/**
 * Base Controller
 *
 * @author Jack
 */
public class BaseController {

    protected <T> ResVO<T> success(T data) {
        return new ResVO<>(200, true, "请求成功", "00000", "一切 ok", data);
    }

    protected ResVO<Map<String, Object>> success() {
        return new ResVO<>(200, true, "请求成功", "00000", "一切 ok", Collections.emptyMap());
    }

    protected ResVO<Map<String, Object>> error(int httpCode, String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(httpCode, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }

    // 400 Bad Request
    protected ResVO<Map<String, Object>> badRequest(String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(400, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }

    // 401 Unauthorized
    protected ResVO<Map<String, Object>> unauthorized(String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(401, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }

    // 403 Forbidden
    protected ResVO<Map<String, Object>> forbidden(String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(403, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }

    // 404 Not Found
    protected ResVO<Map<String, Object>> notFound(String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(404, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }

    // 500 Internal Server Error
    protected ResVO<Map<String, Object>> internalServerError(String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(500, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }

    // 503 Service Unavailable
    protected ResVO<Map<String, Object>> serviceUnavailable(String errorCode, String errorMessage, String userTip) {
        return new ResVO<>(503, false, userTip, errorCode, errorMessage, Collections.emptyMap());
    }
}
