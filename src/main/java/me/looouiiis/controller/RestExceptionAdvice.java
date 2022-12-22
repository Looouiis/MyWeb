package me.looouiiis.controller;

import me.looouiiis.exception.BusinessException;
import me.looouiiis.exception.SystemException;
import me.looouiiis.pojo.JsonExceptionReport;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public JsonExceptionReport doSystemException(SystemException ex){
        return new JsonExceptionReport(ex);
    }
    @ExceptionHandler(BusinessException.class)
    public JsonExceptionReport doBusinessException(BusinessException ex){
        return new JsonExceptionReport(ex);
    }
    @ExceptionHandler(Exception.class)
    public JsonExceptionReport doException(Exception ex){
        ex.printStackTrace();
        return new JsonExceptionReport(ex);
    }
}
