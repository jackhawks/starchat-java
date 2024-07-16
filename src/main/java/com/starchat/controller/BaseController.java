package com.starchat.controller;

import com.starchat.common.constants.Constants;
import com.starchat.common.enums.ErrorCodeEnum;
import com.starchat.common.enums.ResCodeEnum;
import com.starchat.entity.vo.ResVO;

import java.util.Collections;

/**
 * Base Controller
 *
 * @author Jack
 */
public class BaseController {

    protected ResVO success() {
        return success(Collections.emptyMap());
    }

    protected ResVO success(Object data) {
        return new ResVO()
                .setCode(ResCodeEnum.CODE_200.getCode())
                .setStatus(Constants.Status.TRUE)
                .setUserTip(ResCodeEnum.CODE_200.getMessage())
                .setErrorCode(ErrorCodeEnum.ERROR_00000.getCode())
                .setErrorMessage(ErrorCodeEnum.ERROR_00000.getDescription())
                .setData(data);
    }

    protected ResVO error() {
        return new ResVO()
                .setCode(ResCodeEnum.CODE_500.getCode())
                .setStatus(Constants.Status.FALSE)
                .setUserTip(ResCodeEnum.CODE_500.getMessage())
                .setErrorCode(ErrorCodeEnum.ERROR_A0001.getCode())
                .setErrorMessage(ErrorCodeEnum.ERROR_A0001.getDescription())
                .setData(Collections.emptyMap());
    }

    protected ResVO error(String userTip, String errorCode, String errorMessage) {
        return new ResVO()
                .setCode(ResCodeEnum.CODE_200.getCode())
                .setStatus(Constants.Status.FALSE)
                .setUserTip(userTip)
                .setErrorCode(errorCode)
                .setErrorMessage(errorMessage)
                .setData(Collections.emptyMap());
    }
}
