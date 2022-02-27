package com.firefly.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description
 */
@Configuration
public class PostProcessingFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //后处理，打印完成信号的值
        return chain.filter(exchange).doFinally(signal ->  System.out.println("signal="+signal.toString()));
        /* 其他几种
         * or 1: 建议尽量采用链式的fluent连贯写法
         *   Mono<Void>  completeMono = chain.filter(exchange);
         *   return completeMono.doFinally(signal -> System.out.println("signal="+signal.toString()));
         */
        //or 2: return chain.filter(exchange).thenEmpty(other);
        //or 3: return chain.filter(exchange).thenMany(other).map(..)....then();
    }
}