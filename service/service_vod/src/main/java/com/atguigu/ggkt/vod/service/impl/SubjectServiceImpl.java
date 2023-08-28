package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.atguigu.exceptionUtils.GgktException;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.listener.SubjectListener;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lishang
 * @since 2023-07-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private SubjectListener subjectListener;

    @Override
    public List<Subject> findChildSubject(Long id) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<Subject> subjectList = subjectMapper.selectList(queryWrapper);

        //向list集合每个Subject对象中设置hasChildren
        for (Subject subject : subjectList) {
            if (Boolean.TRUE.equals(isChildren(subject.getId()))){
                subject.setHasChildren(true);
            }
        }

        return subjectList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Subject> subjectList = subjectMapper.selectList(null);
            List<SubjectEeVo> list = new ArrayList<>();
            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject,subjectEeVo);
                list.add(subjectEeVo);
            }

            EasyExcelFactory.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcelFactory.read(file.getInputStream(),SubjectEeVo.class,subjectListener).doReadAll();
        }catch (Exception e){
            throw new GgktException(20001,"导入失败");
        }
    }

    public Boolean isChildren(Long id){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = subjectMapper.selectCount(queryWrapper);

        return count > 0;
    }
}
