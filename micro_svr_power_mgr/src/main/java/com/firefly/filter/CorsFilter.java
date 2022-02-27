package com.firefly.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description
 */
@Component
@Order(1)
@WebFilter(filterName = "ResultFilter",urlPatterns = "/*")
public class CorsFilter implements Filter{
    @Value("${cors-filter.enabled}")
    private boolean enabled=false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(enabled){
            System.out.println("doFilter");
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String origin=request.getHeader("Origin");
            String method=request.getMethod();
            Enumeration<String> headerNamesEnum=request.getHeaderNames();
            StringBuffer headerNames=new StringBuffer();
            while(headerNamesEnum.hasMoreElements()){
                if(headerNames.length()==0){
                    headerNames.append(headerNamesEnum.nextElement());
                }else{
                    headerNames.append(","+headerNamesEnum.nextElement());
                }
            }
            String headerNamesStr=headerNames.toString();

            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);

            //Accept, Origin, X-Requested-With, Content-Type, Last-Modified,token
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, headerNamesStr);
            if(method != null){
                response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, method);
            }

            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
            if (method == HttpMethod.OPTIONS.name()) {
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().write("");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }


//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String origin = request.getHeader("Origin");
//        if (StringUtils.isNotBlank(origin)) {
//            //设置响应头，允许跨域访问
//            //带cookie请求时，必须为全匹配，不能使用*
//            /**
//             * 表示允许 origin 发起跨域请求。
//             */
//            response.addHeader("Access-Control-Allow-Origin", origin);
//        }
//
//        /**
//         * GET,POST,OPTIONS，PUT,DELETE 表示允许跨域请求的方法
//         */
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
//
//        /**
//         * 表示在86400秒内不需要再发送预校验请求
//         */
//        response.addHeader("Access-Control-Max-Age", "86400");
//
//        //支持所有自定义头
//        String headers = request.getHeader("Access-Control-Request-Headers");
//        if (StringUtils.isNotBlank(headers)) {
//            //允许JSON请求，并进行预检命令缓存
//            response.addHeader("Access-Control-Allow-Headers", headers);
//        }
//
//        response.addHeader("Access-Control-Max-Age", "3600");
//
//        //允许cookie
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        filterChain.doFilter(servletRequest, response);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
