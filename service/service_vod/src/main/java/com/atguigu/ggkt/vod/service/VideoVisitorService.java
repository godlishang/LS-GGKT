package com.atguigu.ggkt.vod.service;

import java.util.Map;

/**
 * @author ls
 */
public interface VideoVisitorService {
    /**
     * 课程观看人数统计
     * @param courseId courseId
     * @param startDate start date
     * @param endDate end date
     * @return Map<String, Object>
     */
    Map<String, Object> findCount(Long courseId, String startDate, String endDate);

}
