package com.logisticsystembackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logisticsystembackend.common.Result;
import com.logisticsystembackend.entity.Car;
import com.logisticsystembackend.service.CarService;
import com.logisticsystembackend.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {
    @Autowired
    CarService carService;
    @GetMapping("/cars")
    public Result blogs(Integer currentPage) {
        log.info("正在搜索车辆信息");
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = carService.page(page, new QueryWrapper<Car>());
        return Result.success(pageData);
    }
    @GetMapping("/car/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        log.info("正在查看车辆信息："+id);
        Car car = carService.getById(id);
        Assert.notNull(car, "车辆已删除");
        return Result.success(car);
    }

    @RequiresAuthentication
    @PostMapping("/car/edit")
    public Result edit(@Validated @RequestBody Car car) {
        log.info("正在编辑车辆信息"+car.getId()+","+car.getCarplate());
        Car temp = null;
        if(car.getId() != null) {
            //已有信息编辑
            temp = carService.getById(car.getId());
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
            Assert.isTrue(null != ShiroUtil.getProfile().getId(), "没有权限编辑，请先登录");
        } else {
            //新建
            temp = new Car();
////            temp.setUserId(ShiroUtil.getProfile().getId());
////            temp.s(LocalDateTime.now());
//            temp.setCarplate(car.getCarplate());
//            temp.setLoad(car.getLoad());
//            temp.setWork(car.getWork());
//            temp.setLoad(car.getLoad());
        }
        BeanUtil.copyProperties(car, temp, "id");
        carService.saveOrUpdate(temp);
        return Result.success(null);
    }

}
