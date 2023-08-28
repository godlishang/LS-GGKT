package com.atguigu.exceptionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : lishang
 * @date : 2023/4/17 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GgktException extends RuntimeException{

    private Integer code;
    private String msg;
}

