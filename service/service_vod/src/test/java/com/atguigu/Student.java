package com.atguigu;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : lishang
 * @date : 2023/7/10 14:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    //设置表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
}

