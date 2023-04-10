package com.logisticsystembackend.shrio;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: blog
 * @package: com.carolyn.shrio
 * @className: AccountProfile
 * @author: Carolyn
 * @description: AccountProfile
 * @date: 2023/3/2 9:57
 */

@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
//    private String avatar;
}
