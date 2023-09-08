package com.atguigu.ggkt.order.service;

import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author lishang
 * @since 2023-09-06
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 分页查询订单数据
     * @param pageParam 分页参数
     * @param orderInfoQueryVo 查询参数
     * @return Map<String, Object>
     */
    Map<String, Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);

}
