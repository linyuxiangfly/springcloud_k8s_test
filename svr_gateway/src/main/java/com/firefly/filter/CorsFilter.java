package com.firefly.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.cookie.CookieHeaderNames.MAX_AGE;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description
 */
@Configuration
public class CorsFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (CorsUtils.isCorsRequest(request)) {
            HttpHeaders requestHeaders = request.getHeaders();
            ServerHttpResponse response = exchange.getResponse();
            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
            headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders
                    .getAccessControlRequestHeaders());
            if(requestMethod != null){
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
            }
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }

        }
        return chain.filter(exchange);

//        setHeader(exchange.getRequest(),exchange.getResponse());
        //后处理，打印完成信号的值
//        return chain.filter(exchange).doFinally(signal ->  System.out.println("signal="+signal.toString()));
        /* 其他几种
         * or 1: 建议尽量采用链式的fluent连贯写法
         *   Mono<Void>  completeMono = chain.filter(exchange);
         *   return completeMono.doFinally(signal -> System.out.println("signal="+signal.toString()));
         */
        //or 2: return chain.filter(exchange).thenEmpty(other);
        //or 3: return chain.filter(exchange).thenMany(other).map(..)....then();
    }

//    private void setHeader(ServerHttpRequest request, ServerHttpResponse response){
////        String origin = request.getHeaders().get("Origin").toString();
//        response.getHeaders().set("Access-Control-Allow-Origin", "*");
//        response.getHeaders().set("Access-Control-Allow-Methods", "GET, POST, HEAD, PUT, DELETE, OPTIONS");
//        response.getHeaders().set("Access-Control-Max-Age", "3600");
//        response.getHeaders().set("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type, Last-Modified,token");
//        response.getHeaders().set("Access-Control-Allow-Credentials", "true");
//        String method = request.getMethodValue();
//        if(method.equalsIgnoreCase("OPTIONS")){
////            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
//        }else{
////            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
}