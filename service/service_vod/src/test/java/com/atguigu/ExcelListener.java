package com.atguigu;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : lishang
 * @date : 2023/7/10 15:46
 */
public class ExcelListener extends AnalysisEventListener<Student> {

    //创建list集合封装最终的数据
    List<Student> list = new ArrayList<>();

    //一行一行去读取excel内容
    @Override
    public void invoke(Student data, AnalysisContext context) {
        System.out.println("***" + data);
        list.add(data);
    }

    //读取excel表头信息
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        System.out.println("表头信息" + headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}

