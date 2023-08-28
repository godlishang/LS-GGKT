package com.atguigu.exceptionUtils;

import com.atguigu.resultUtils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : lishang
 * @date : 2023/4/11 18:11
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 定义全局异常处理
     * @param e 异常类型
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();

        return Result.fail(null).message("执行了全局异常处理");
    }

    /**
     * 定义特定异常处理
     * @param e 特定异常类型
     * @return Result
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();

        return Result.fail(null).message("执行了特定异常处理");
    }

    /**
     * 自定义异常处理
     * @param e 自定义异常类型
     * @return Result
     */
    @ExceptionHandler(GgktException.class)
    @ResponseBody
    public Result error(GgktException e){
        e.printStackTrace();

        return Result.fail(e.getCode()).message(e.getMsg());
    }
}

