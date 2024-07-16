package com.starchat.exception;

import com.starchat.common.enums.ErrorCodeEnum;
import com.starchat.common.enums.ResCodeEnum;
import com.starchat.controller.BaseController;
import com.starchat.entity.vo.ResVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler(Exception.class)
    public ResVO handleGlobalException(Exception e) {
        return error();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResVO handleResourceNotFoundException(NoResourceFoundException e) {
        return error(ResCodeEnum.CODE_404.getMessage(), ErrorCodeEnum.ERROR_A0001.getCode(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResVO handleResourceNotFoundException(BusinessException e) {
        return error(e.getMessage(), ErrorCodeEnum.ERROR_A0001.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
