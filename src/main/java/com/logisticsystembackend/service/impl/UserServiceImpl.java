package com.logisticsystembackend.service.impl;

import com.logisticsystembackend.entity.User;
import com.logisticsystembackend.mapper.UserMapper;
import com.logisticsystembackend.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
