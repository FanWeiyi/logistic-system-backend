package com.logisticsystembackend.service.impl;

import com.logisticsystembackend.entity.Order;
import com.logisticsystembackend.mapper.OrderMapper;
import com.logisticsystembackend.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
