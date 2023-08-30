package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 嵌套获取章节数据列表
     * @param courseId courseId
     * @return List<ChapterVo>
     */
    List<ChapterVo> getNestedTreeList(Long courseId);

    /**
     * 根据课程id删除章节
     * @param id courseId
     */
    void removeChapterByCourseId(Long id);
}
