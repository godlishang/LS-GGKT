package com.atguigu.resultUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : lishang
 * @date : 2023/3/13 18:20
 */
@Data
@ApiModel("全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;


    public Result() {
    }

    public static<T> Result<T> bulid(T body,Integer code,String massage){
        Result<T> result = new Result<>();
        if (body != null){
            result.setData(body);
        }

        result.setCode(code);
        result.setMessage(massage);
        return result;
    }

    public static <T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> ok(T data){
        return bulid(data,20000,"成功");
    }

    public static <T> Result<T> fail(){
        return Result.fail(null);
    }

    /**
     * 操作哦失败
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> fail(T data){
        return bulid(data,500,"失败");
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }



}

