package com.atguigu.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lishang
 * @since 2023-07-05
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 查询下一层课程名称,根据parent_id
     * @param id id
     * @return List<Subject>
     */
    List<Subject> findChildSubject(Long id);

    /**
     * 课程分类导出
     * @param response res
     */
    void exportData(HttpServletResponse response);

    /**
     * 课程分类导入
     * @param file file
     */
    void importData(MultipartFile file);
}
