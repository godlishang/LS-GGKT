package com.atguigu.ggkt.activity.controller;


import com.atguigu.ggkt.activity.service.CouponInfoService;
import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.atguigu.resultUtils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author lishang
 * @since 2023-09-08
 */
@RestController
@RequestMapping("/admin/activity/couponInfo")
public class CouponInfoController {

    @Resource
    private CouponInfoService couponInfoService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result<Object> index(@ApiParam(name = "page",value = "当前页码",required = true) @PathVariable Long page,
                                @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable Long limit){

        Page<CouponInfo> pageParam = new Page<>(page, limit);
        Page<CouponInfo> pageModel = couponInfoService.page(pageParam);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取优惠券")
    @GetMapping("get/{id}")
    public Result<Object> get(@PathVariable String id){
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.ok(couponInfo);
    }

    @ApiOperation(value = "新增优惠券")
    @PostMapping("save")
    public Result<Object> save(@RequestBody CouponInfo couponInfo){
        couponInfoService.save(couponInfo);
        return Result.ok();
    }

    @ApiOperation(value = "修改优惠券")
    @PutMapping("update")
    public Result<Object> updateById(@RequestBody CouponInfo couponInfo){
        couponInfoService.updateById(couponInfo);
        return Result.ok();
    }

    @ApiOperation(value = "删除优惠券")
    @DeleteMapping("remove/{id}")
    public Result<Object> remove(@PathVariable String id){
        couponInfoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除优惠券")
    @DeleteMapping("batchRemove")
    public Result<Object> batchRemove(@RequestBody List<String> idList){
        couponInfoService.removeByIds(idList);
        return Result.ok();
    }

    @ApiOperation(value = "查询已经使用过的优惠券列表")
    @GetMapping("couponUse/{page}/{limit}")
    public Result<Object> getUseIndex(@ApiParam(name = "page",value = "当前页码") @PathVariable Long page,
                                      @ApiParam(name = "limit",value = "每页记录数") @PathVariable Long limit,
                                      @ApiParam(name = "couponUseVo",value = "查询对象") CouponUseQueryVo couponUseQueryVo){

        Page<CouponUse> pageParam = new Page<>(page, limit);
        IPage<CouponUse> pageModel =  couponInfoService.selectCouponUsePage(pageParam, couponUseQueryVo);
        return Result.ok(pageModel);
    }

}

