package com.logisticsystembackend.mapper;

import com.logisticsystembackend.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
