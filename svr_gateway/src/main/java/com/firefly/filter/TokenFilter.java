package com.firefly.filter;

import com.firefly.config.TokenFilterConfig;
import com.firefly.config.TokenUtilConfig;
import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.exception.NoLoginException;
import com.firefly.exception.NoPowerException;
import com.firefly.exception.TokenException;
import com.firefly.utils.TokenUtil;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * @Author fly
 * @Date 2019/6/4
 * @Description
 */
public class TokenFilter implements GlobalFilter, Ordered {
    Logger logger= LoggerFactory.getLogger( TokenFilter.class );

    private TokenFilterConfig tokenFilterConfig;

    private TokenUtilConfig tokenUtilConfig;

    private TokenUtil tokenUtil;

    public TokenFilterConfig getTokenFilterConfig() {
        return tokenFilterConfig;
    }

    @Autowired
    public void setTokenFilterConfig(TokenFilterConfig tokenFilterConfig) {
        this.tokenFilterConfig = tokenFilterConfig;
    }

    public TokenUtilConfig getTokenUtilConfig() {
        return tokenUtilConfig;
    }

    @Autowired
    public void setTokenUtilConfig(TokenUtilConfig tokenUtilConfig) {
        this.tokenUtilConfig = tokenUtilConfig;
        this.tokenUtil=tokenUtilConfig.getTokenUtil();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(tokenFilterConfig.isEnabled()){
            String url=exchange.getRequest().getPath().toString();
            String method=exchange.getRequest().getMethodValue();

            String token = exchange.getRequest().getHeaders().getFirst("token");

            if(token==null || token.isEmpty()){
                token = exchange.getRequest().getQueryParams().getFirst("token");
            }

            if(token!=null && !token.isEmpty()){
                //??????token
                User user=authenticationToken(token);
                if (user == null) {
                    //?????????
                    throw new NoLoginException();
//                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().setComplete();
                }

                //?????????????????????
                if(isHasPower(user,url,method)){
                    //???headers?????????user??????
                    return chain.filter(builderUser(exchange,user));
                }else{
                    //???????????????
                    throw new NoPowerException();
//                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
//                return exchange.getResponse().writeWith(getPublisher(exchange,"noPower"));
                }
            }else{
                //?????????????????????URL
                boolean excep = isExceptUrl(url,method);
                if(!excep){
                    throw new NoLoginException();
                }
            }
        }

        return chain.filter(exchange);
    }

    private ServerWebExchange builderUser(ServerWebExchange exchange,User user){
        if(user==null){
            return exchange;
        }else{
            //???headers?????????????????????build
            ServerHttpRequest.Builder builder=exchange.getRequest().mutate();
            builderHeader(builder,"user.id",user.getId());
            builderHeader(builder,"user.name",user.getName());
            builderHeader(builder,"user.gender",user.isGender()+"");
            builderHeader(builder,"user.tel",user.getTel());
            builderHeader(builder,"user.type",user.getType());

            ServerHttpRequest host = builder.build();
            //????????????request ?????? change??????
            return exchange.mutate().request(host).build();
        }
    }

    /**
     * ??????builder???????????????
     * @param builder
     * @param key
     * @param val
     */
    private void builderHeader(ServerHttpRequest.Builder builder,String key,String val){
        if(val!=null){
            builder.header(key,val);
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private Publisher<? extends DataBuffer> getPublisher(ServerWebExchange exchange,String data){
        byte[] bytes = (data).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(bytes);
        return Flux.just(wrap);
    }

    /**
     * ??????TOKEN
     * @param token
     */
    private User authenticationToken(String token){
        User retVal=null;

        if (token == null || token.isEmpty()) {
            throw new TokenException("TokenIsNull");
        }
        // parse the token.
        long start = System.currentTimeMillis();
        retVal = tokenUtil.getUserByToken(token);
        long end = System.currentTimeMillis();
        logger.info("????????????: {}", (end - start) + " ??????");
        return retVal;
    }

    /**
     * ?????????URL????????????
     * @param url
     * @return
     */
    public boolean isExceptUrl(String url,String method){
        boolean retVal=false;
        if(tokenFilterConfig.getExceptUrlMethods()!=null){
            for(TokenFilterConfig.UrlMethod urlMethod:tokenFilterConfig.getExceptUrlMethods()){
                //????????????????????????
                boolean findUrl = Pattern.matches(urlMethod.getUrl(),url);

                boolean findMethod = Pattern.matches(urlMethod.getMethod(),method);

                //??????????????????????????????
                if(findUrl && findMethod){
                    retVal=true;
                    break;
                }
            }
        }
        return retVal;
    }

    /**
     * ????????????????????????
     * @param method
     * @param url
     * @return
     */
    private boolean isHasPower(User user, String url, String method) {
        boolean retVal = false;

        //?????????????????????
        if(user.isSuperUser()){
            retVal = true;
        }else{
            Func func = findFunc(user, url, method);

            if (func != null) {
                retVal = true;
            }
        }

        return retVal;
    }

    /**
     * ????????????
     * @param url
     * @param method
     * @return
     */
    public Func findFunc(User user, String url, String method){
        return tokenUtil.findFunc(user,url,method);
    }
}
