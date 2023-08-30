package com.atguigu.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

    /**
     * 根据课程id删除课程描述
     * @param id courseId
     */
    void removeCourseDescriptionByCourseId(Long id);

}
