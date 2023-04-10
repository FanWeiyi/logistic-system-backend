package com.logisticsystembackend.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 路线
 * </p>
 *
 * @author Carolyn
 * @since 2023-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Rout implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rout_id", type = IdType.AUTO)
    private Long routId;

    private BigDecimal length;

    private String path;


}
