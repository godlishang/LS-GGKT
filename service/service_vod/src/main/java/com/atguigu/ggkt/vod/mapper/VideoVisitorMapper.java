package com.atguigu.ggkt.vod.mapper;

import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.atguigu.ggkt.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ls
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    /**
     * 查询观看人数
     * @param courseId course id
     * @param stareDate starting date
     * @param endDate ending date
     * @return List<VideoVisitorCountVo>
     */
    List<VideoVisitorCountVo> findCount(@Param("courseId") Long courseId,
                                        @Param("stareDate") String stareDate,
                                        @Param("endDate") String endDate);
}
