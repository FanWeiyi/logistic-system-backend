package com.logisticsystembackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logisticsystembackend.common.Result;
import com.logisticsystembackend.entity.Order;
import com.logisticsystembackend.entity.Rout;
import com.logisticsystembackend.service.OrderService;
import com.logisticsystembackend.service.RoutService;
import com.logisticsystembackend.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 路线 前端控制器
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/rout")
@Slf4j
public class RoutController {
    @Autowired
    RoutService routService;
    @GetMapping("/routs")
    public Result customers(Integer currentPage) {
        log.info("正在查看路线信息");
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = routService.page(page, new QueryWrapper<Rout>());
        return Result.success(pageData);
    }
    @GetMapping("/rout/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        log.info("正在查看订单信息："+id);
        Rout rout = routService.getById(id);
        Assert.notNull(rout, "路线已删除");
        return Result.success(rout);
    }

    @RequiresAuthentication
    @PostMapping("/rout/edit")
    public Result edit(@Validated @RequestBody Rout rout) {
        log.info("正在编辑路线信息"+rout.getId()+","+rout.getPath());
        Rout temp = null;
        if(rout.getId() != null) {
            //已有信息编辑
            temp = routService.getById(rout.getId());
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
            Assert.isTrue(null != ShiroUtil.getProfile().getId(), "没有权限编辑，请先登录");
        } else {
            //新建
            temp = new Rout();
////            temp.setUserId(ShiroUtil.getProfile().getId());
////            temp.s(LocalDateTime.now());
//            temp.setCarplate(car.getCarplate());
//            temp.setLoad(car.getLoad());
//            temp.setWork(car.getWork());
//            temp.setLoad(car.getLoad());
        }
        BeanUtil.copyProperties(rout, temp, "id");
        routService.saveOrUpdate(temp);
        return Result.success(null);
    }
}
