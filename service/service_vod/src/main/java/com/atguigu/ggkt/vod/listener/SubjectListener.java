package com.atguigu.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : lishang
 * @date : 2023/7/11 16:35
 */
@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {
    @Resource
    private SubjectMapper subjectMapper;

    /**
     * 一行一行读取数据
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     *            analysis context
     */
    @Override
    public void invoke(SubjectEeVo data, AnalysisContext context) {
        //调用方法向数据库添加数据
        Subject subject = new Subject();
        BeanUtils.copyProperties(data,subject);
        subjectMapper.insert(subject);
    }

    /**
     *
     * @param context c
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}

