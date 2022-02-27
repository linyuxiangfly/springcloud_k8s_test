package com.firefly.gateway;

import com.firefly.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import reactor.core.publisher.Mono;

@SpringBootApplication
@ComponentScan(basePackages = {"com.firefly.controller","com.firefly.rmi","com.firefly.filter","com.firefly.config","com.firefly.userauths.verified"})
@EnableDiscoveryClient //启用服务注册与发现
@EnableFeignClients(basePackages = "com.firefly.rmi")
//@ServletComponentScan(basePackages = "com.firefly.filter")
//@EnableHystrixDashboard
@EnableCircuitBreaker
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


	/**
	 * 使用代码的方式进行路由选择
	 * @param builder
	 * @return
	 */
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/hello")
//						.filters(f -> f.addRequestHeader("Hello", "World"))
//						.uri("lb://spring-cloud-producer"))
//				.build();
//	}

	/**
	 * 根据user参数限流
	 * @return
	 */
//	@Bean
//	KeyResolver userKeyResolver() {
//		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//	}

	/**
	 * 根据远程地址限流
	 * @return
	 */
	@Bean
	public KeyResolver ipKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}

//	@Bean
//	@ConditionalOnBean({RateLimiter.class, KeyResolver.class})
//	public RequestRateLimiterGatewayFilterFactory requestRateLimiter(RateLimiter rateLimiter,PrincipalNameKeyResolver resolver) {
//		return new RequestRateLimiterGatewayFilterFactory(rateLimiter, resolver);
//	}

	/**
	 * 加上TOKEN拦截器
	 * @return
	 */
	@Bean
	public TokenFilter tokenFilter(){
		return new TokenFilter();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
