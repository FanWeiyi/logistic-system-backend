package com.logisticsystembackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logisticsystembackend.common.Result;
import com.logisticsystembackend.entity.Customer;
import com.logisticsystembackend.entity.Order;
import com.logisticsystembackend.service.CustomerService;
import com.logisticsystembackend.service.OrderService;
import com.logisticsystembackend.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    public Result customers(Integer currentPage) {
        log.info("正在订单信息");
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = orderService.page(page, new QueryWrapper<Order>());
        return Result.success(pageData);
    }
    @GetMapping("/order/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        log.info("正在查看订单信息："+id);
        Order order= orderService.getById(id);
        Assert.notNull(order, "订单已删除");
        return Result.success(order);
    }

    @RequiresAuthentication
    @PostMapping("/order/edit")
    public Result edit(@Validated @RequestBody Order order) {
        log.info("正在编辑订单信息"+order.getId()+","+order.getCustomerId());
        Order temp = null;
        if(order.getId() != null) {
            //已有信息编辑
            temp = orderService.getById(order.getId());
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
            Assert.isTrue(null != ShiroUtil.getProfile().getId(), "没有权限编辑，请先登录");
        } else {
            //新建
            temp = new Order();
////            temp.setUserId(ShiroUtil.getProfile().getId());
////            temp.s(LocalDateTime.now());
//            temp.setCarplate(car.getCarplate());
//            temp.setLoad(car.getLoad());
//            temp.setWork(car.getWork());
//            temp.setLoad(car.getLoad());
        }
        BeanUtil.copyProperties(order, temp, "id");
        orderService.saveOrUpdate(temp);
        return Result.success(null);
    }
}
