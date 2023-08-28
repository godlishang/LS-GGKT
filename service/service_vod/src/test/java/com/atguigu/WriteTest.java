package com.atguigu;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : lishang
 * @date : 2023/7/10 14:48
 */
public class WriteTest {

    public static void main(String[] args) {

        //写入路径
        String fileName = "D:\\11.xlsx";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Student.class).sheet("写入方法").doWrite(data());
    }


    public static List<Student> data(){
        List<Student> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setSno(i);
            student.setName("张三" + i);

            list.add(student);
        }

        return list;
    }
}

