package com.logisticsystembackend.controller;

//import cn.hutool.core.lang.Assert;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.logisticsystembackend.entity.User;
import com.logisticsystembackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
//    @Autowired
//    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    /**
     * 登录
     * @param user
     * @return R
     */

    @CrossOrigin
    @PostMapping("/login")
    public R<User> login(@Validated @RequestBody User user){
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
                return R.ok(user1);
            }else {
                return R.failed("密码错误");
            }
        }else {
            //用户不存在
            return R.failed("用户不存在");
        }

    }

//    @GetMapping("/logout")
//    @RequiresAuthentication
//    public Result logout(){
//        SecurityUtils.getSubject().logout();
//        return Result.success(null);
//    }
}
