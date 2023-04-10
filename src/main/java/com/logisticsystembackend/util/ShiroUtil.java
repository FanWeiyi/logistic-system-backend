package com.logisticsystembackend.util;


import com.logisticsystembackend.shrio.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @projectName: blog
 * @package: com.carolyn.util
 * @className: sherioUtil
 * @author: Carolyn
 * @description: sherioUtil
 * @date: 2023/3/2 13:13
 */

public class ShiroUtil {

        public static AccountProfile getProfile() {

            return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        }

}
