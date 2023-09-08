package com.atguigu.ggkt.order.service.impl;

import com.atguigu.ggkt.model.order.OrderDetail;
import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.order.mapper.OrderInfoMapper;
import com.atguigu.ggkt.order.service.OrderDetailService;
import com.atguigu.ggkt.order.service.OrderInfoService;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author lishang
 * @since 2023-09-06
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderDetailService orderDetailService;
    @Override
    public Map<String, Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo) {


        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(orderInfoQueryVo.getUserId())){
            queryWrapper.eq("user_id",orderInfoQueryVo.getUserId());
        }
        if (!StringUtils.isEmpty(orderInfoQueryVo.getOrderStatus())){
            queryWrapper.eq("order_status",orderInfoQueryVo.getUserId());
        }
        if (!StringUtils.isEmpty(orderInfoQueryVo.getOutTradeNo())){
            queryWrapper.eq("out_trade_no",orderInfoQueryVo.getOutTradeNo());
        }
        if (!StringUtils.isEmpty(orderInfoQueryVo.getPhone())){
            queryWrapper.eq("phone",orderInfoQueryVo.getPhone());
        }
        if (!StringUtils.isEmpty(orderInfoQueryVo.getCreateTimeBegin())){
            queryWrapper.ge("create_time",orderInfoQueryVo.getCreateTimeBegin());
        }
        if (!StringUtils.isEmpty(orderInfoQueryVo.getCreateTimeEnd())){
            queryWrapper.le("create_time",orderInfoQueryVo.getCreateTimeBegin());
        }

        Page<OrderInfo> pages = baseMapper.selectPage(pageParam, queryWrapper);
        long totalCount = pages.getTotal();
        long pagesCount = pages.getPages();
        List<OrderInfo> records = pages.getRecords();
        //封装订单详情数据
        records.forEach(this::getOrderInfoDetail);

        //所有需要的数据封装到map中，最终返回
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("total",totalCount);
        map.put("pageCount",pagesCount);
        map.put("records",records);

        return map;
    }

    public void getOrderInfoDetail(OrderInfo orderInfo){
        OrderDetail orderDetail = orderDetailService.getById(orderInfo.getId());
        if (orderDetail != null){
            String courseName = orderDetail.getCourseName();
            orderInfo.getParam().put("courseName",courseName);
        }
    }
}
