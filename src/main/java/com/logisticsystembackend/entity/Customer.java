package com.logisticsystembackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "customer_id", type = IdType.AUTO)
    private Long customerId;

    private String name;

    private Integer state;

    private String sex;

    private String email;

    private Integer phone;

    private Double longitude;

    private Double latitude;

    private String address;


}
