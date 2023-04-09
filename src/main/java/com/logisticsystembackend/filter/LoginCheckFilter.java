package com.logisticsystembackend.filter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.logisticsystembackend.common.BaseContext;
//import com.ytsky.reggie.common.BaseContext;
//import com.ytsky.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @ClassName:LoginCheckFilter
 * @Description:检查登录
 * @Author:liumengying
 * @Date: 2022/7/25 8:50
 * Version v1.0
 */
@Slf4j  //日志
@WebFilter(filterName = "LoginCheckFilter",urlPatterns="/*")   //java servlet提供的注解
public class LoginCheckFilter implements Filter {

    //定义路径匹配器，可以支持通配符的模式
    //将匹配器定义为一个全局的静态对象，可以被所有请求的线程共享，可以提高效率
    private static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)throws IOException, ServletException{


        //0.对传入的参数进行处理
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //1.获取本次请求
        final String requestURL=request.getRequestURI();
        log.info("拦截请求:{}",requestURL);

        //2.判断本次请求是否需要处理
        //定义不需要处理的请求有
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
        boolean match=false;
        for (String url:urls){
            //将获得的请求与urls中的每一个url模式进行匹配
             match = PATH_MATCHER.match(url,requestURL);
             if(match){
                 break;
             }
        }

        //3.如果不需要处理，则直接放行
        if(match){
            log.info("本次请求{}不需要处理",requestURL);
            //调用过滤器的doFilter方法，可以让过滤器继续执行
            log.info("requestURL:{}",requestURL);
            log.info("response:{}",String.valueOf(response));
            filterChain.doFilter(request,response);
            return;
        }

        //4.判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee")!= null){
            log.info("用户已登录，用户id为:{}",request.getSession().getAttribute("employee"));

            //获得当前登录用户ID
            Long empId=(Long) request.getSession().getAttribute("employee");
            //将线程绑定该ID
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);

            return;
        }

        if(request.getSession().getAttribute("user")!=null){
            log.info("用户已登录,用户id为:{}",request.getSession().getAttribute("user"));

            Long userId=(Long)request.getSession().getAttribute("user");

            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);

            return;
        }
        log.info("用户未登录");

        //如果未登录，则返回登录结果，通过输出流方式直接向客户端的页面响应结果
        response.getWriter().write(JSON.toJSONString(R.failed("NOTLOGIN")));
        return;
    }


}
