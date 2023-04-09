package com.logisticsystembackend.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: logistic-system-backend
 * @package: com.logisticsystembackend.config
 * @className: MybatisPlus
 * @author: Carolyn
 * @description: MybatisPlus
 * @date: 2023/3/29 17:41
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.logisticsystembackend.mapper")   //指定变成实现类的接口所在的包
public class MybatisPlusConfig {

    /**
     * 实现一个分页插件PaginationInterceptor
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}