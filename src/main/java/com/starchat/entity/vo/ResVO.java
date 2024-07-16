package com.starchat.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResVO implements Serializable {
    private static final int SUCCESS_CODE = 200;
    private static final int BAD_REQUEST_CODE = 400;
    private static final int UNAUTHORIZED_CODE = 401;
    private static final int FORBIDDEN_CODE = 403;
    private static final int NOT_FOUND_CODE = 404;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;
    private static final int SERVICE_UNAVAILABLE_CODE = 503;

    private static final String SUCCESS_MESSAGE = "请求成功";
    private static final String SUCCESS_USER_TIP = "一切 ok";
    private static final String SUCCESS_ERROR_CODE = "00000";



    private int code;
    private boolean status;
    private String userTip;
    private String errorCode;
    private String errorMessage;
    private Object data;

//    public ResVO success(Object data) {
//        return new ResVO(SUCCESS_CODE, true, SUCCESS_MESSAGE, SUCCESS_ERROR_CODE, SUCCESS_USER_TIP, data);
//    }
//
//    public ResVO success() {
//        return new ResVO(SUCCESS_CODE, true, SUCCESS_MESSAGE, SUCCESS_ERROR_CODE, SUCCESS_USER_TIP, Collections.emptyMap());
//    }
//
//    public ResVO error(int httpCode, String errorCode, String errorMessage, String userTip) {
//        return new ResVO(httpCode, false, userTip, errorCode, errorMessage, Collections.emptyMap());
//    }
//
//    public ResVO badRequest(String errorCode, String errorMessage, String userTip) {
//        return error(BAD_REQUEST_CODE, errorCode, errorMessage, userTip);
//    }
//
//    public ResVO unauthorized(String errorCode, String errorMessage, String userTip) {
//        return error(UNAUTHORIZED_CODE, errorCode, errorMessage, userTip);
//    }
//
//    public ResVO forbidden(String errorCode, String errorMessage, String userTip) {
//        return error(FORBIDDEN_CODE, errorCode, errorMessage, userTip);
//    }
//
//    public ResVO notFound(String errorCode, String errorMessage, String userTip) {
//        return error(NOT_FOUND_CODE, errorCode, errorMessage, userTip);
//    }
//
//    public ResVO internalServerError(String errorCode, String errorMessage, String userTip) {
//        return error(INTERNAL_SERVER_ERROR_CODE, errorCode, errorMessage, userTip);
//    }
//
//    public ResVO serviceUnavailable(String errorCode, String errorMessage, String userTip) {
//        return error(SERVICE_UNAVAILABLE_CODE, errorCode, errorMessage, userTip);
//    }
}
