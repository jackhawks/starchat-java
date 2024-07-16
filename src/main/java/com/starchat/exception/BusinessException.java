package com.starchat.exception;

import com.starchat.common.enums.ErrorCodeEnum;
import lombok.*;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    public BusinessException(ErrorCodeEnum codeEnum) {
        super(codeEnum.getDescription());
    }
}
