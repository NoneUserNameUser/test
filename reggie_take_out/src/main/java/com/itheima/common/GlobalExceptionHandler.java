package com.itheima.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @version 1.0
 * @Author：陈伟捷
 */
//通知类 配置需要拦截的控制类（注释类）
@ControllerAdvice(annotations={RestController.class, Controller.class})
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
   //拦截的异常类
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        //如果为重复用户名提示对应信息
        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            return R.error("该名称" + split[2] + " 已存在");
        }
        return R.error("未知错误");
    }
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
        String message = ex.getMessage();
        log.info(message);
        return R.error(message);
    }
}
