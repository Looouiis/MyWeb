package me.looouiiis.controller;

import me.looouiiis.pojo.JsonExceptionReport;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvice {
    @ExceptionHandler
    public JsonExceptionReport doException(Exception ex){
        return new JsonExceptionReport(ex);
    }
}
