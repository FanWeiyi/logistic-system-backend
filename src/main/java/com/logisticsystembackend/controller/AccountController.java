package com.logisticsystembackend.controller;

//import cn.hutool.core.lang.Assert;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.logisticsystembackend.common.Result;
import com.logisticsystembackend.entity.User;
import com.logisticsystembackend.service.UserService;
import com.logisticsystembackend.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
/*
总控制器
 */
@RestController
@Slf4j
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    /**
     * 登录
     * @param user
     * @return R
     */

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody User user , HttpServletResponse httpServletResponse){
        log.info("用户登录信息{}", user.getName()+user.getPassword());
        String name= user.getName();
        //查看名字是否有对应用户
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,user.getName());
        User user1=userService.getOne(queryWrapper);

        if (user1!=null){
            //查询密码是否一致
            if (user.getPassword().equals(user1.getPassword())){
                //密码一致，登陆成功
                //根据id生成jwt
                String jwt = jwtUtils.generateToken(user1.getId());
                //将jwt放在header上
                httpServletResponse.setHeader("Authorization",jwt);
                httpServletResponse.setHeader("Access-Control-Expose-Headers","Authorization");
                return Result.success(user1);
            }else {
                return Result.fail("密码错误");
            }
        }else {
            //用户不存在
            return Result.fail("用户不存在");

        }

    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout(){
        log.info("用户登出");
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
