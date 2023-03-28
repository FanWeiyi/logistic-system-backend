package com.logisticsystembackend.service.impl;

import com.logisticsystembackend.entity.Car;
import com.logisticsystembackend.mapper.CarMapper;
import com.logisticsystembackend.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
