package com.logisticsystembackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logisticsystembackend.common.Result;
import com.logisticsystembackend.entity.Car;
import com.logisticsystembackend.entity.Customer;
import com.logisticsystembackend.service.CarService;
import com.logisticsystembackend.service.CustomerService;
import com.logisticsystembackend.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/customers")
    public Result customers(Integer currentPage) {
        log.info("正在搜索顾客信息");
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = customerService.page(page, new QueryWrapper<Customer>());
        return Result.success(pageData);
    }
    @GetMapping("/customer/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        log.info("正在查看顾客信息："+id);
        Customer customer= customerService.getById(id);
        Assert.notNull(customer, "顾客已删除");
        return Result.success(customer);
    }

    @RequiresAuthentication
    @PostMapping("/customer/edit")
    public Result edit(@Validated @RequestBody Customer customer) {
        log.info("正在编辑顾客信息"+customer.getId()+","+customer.getName());
        Customer temp = null;
        if(customer.getId() != null) {
            //已有信息编辑
            temp = customerService.getById(customer.getId());
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
            Assert.isTrue(null != ShiroUtil.getProfile().getId(), "没有权限编辑，请先登录");
        } else {
            //新建
            temp = new Customer();
////            temp.setUserId(ShiroUtil.getProfile().getId());
////            temp.s(LocalDateTime.now());
//            temp.setCarplate(car.getCarplate());
//            temp.setLoad(car.getLoad());
//            temp.setWork(car.getWork());
//            temp.setLoad(car.getLoad());
        }
        BeanUtil.copyProperties(customer, temp, "id");
        customerService.saveOrUpdate(temp);
        return Result.success(null);
    }

}
