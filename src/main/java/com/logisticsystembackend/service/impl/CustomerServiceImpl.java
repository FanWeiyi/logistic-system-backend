package com.logisticsystembackend.service.impl;

import com.logisticsystembackend.entity.Customer;
import com.logisticsystembackend.mapper.CustomerMapper;
import com.logisticsystembackend.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
