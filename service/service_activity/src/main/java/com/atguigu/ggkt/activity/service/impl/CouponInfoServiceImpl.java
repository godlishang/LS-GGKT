package com.atguigu.ggkt.activity.service.impl;

import com.atguigu.ggkt.activity.mapper.CouponInfoMapper;
import com.atguigu.ggkt.activity.service.CouponInfoService;
import com.atguigu.ggkt.activity.service.CouponUseService;
import com.atguigu.ggkt.client.UserInfoFeignClient;
import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.model.user.UserInfo;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author lishang
 * @since 2023-09-08
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Resource
    private CouponUseService couponUseService;
    @Resource
    private UserInfoFeignClient userInfoFeignClient;

    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
        QueryWrapper<CouponUse> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(couponUseQueryVo.getCouponId())){
            queryWrapper.eq("coupon_id",couponUseQueryVo.getCouponId());
        }
        if (!StringUtils.isEmpty(couponUseQueryVo.getCouponStatus())){
            queryWrapper.eq("coupon_status",couponUseQueryVo.getCouponStatus());
        }
        if (!StringUtils.isEmpty(couponUseQueryVo.getGetTimeBegin())){
            queryWrapper.ge("get_time",couponUseQueryVo.getGetTimeBegin());
        }
        if (!StringUtils.isEmpty(couponUseQueryVo.getGetTimeEnd())){
            queryWrapper.le("get_time",couponUseQueryVo.getGetTimeEnd());
        }

        IPage<CouponUse> page = couponUseService.page(pageParam, queryWrapper);
        List<CouponUse> records = page.getRecords();

        records.forEach(this::getUserInfoByCouponUse);

        return page;
    }

    public void getUserInfoByCouponUse(CouponUse couponUse){
        Long userId = couponUse.getUserId();
        if (!StringUtils.isEmpty(userId)){
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            if (userInfo != null){
                couponUse.getParam().put("nickName",userInfo.getNickName());
                couponUse.getParam().put("phone",userInfo.getPhone());
            }
        }
    }
}
