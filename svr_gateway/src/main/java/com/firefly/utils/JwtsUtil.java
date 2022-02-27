package com.firefly.utils;

import com.firefly.exception.TokenException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author fly
 * @Date 2019/6/12
 * @Description
 */
public class JwtsUtil {
    private static Logger logger= LoggerFactory.getLogger( JwtsUtil.class );

    /**
     * 创建token
     * @param signingKey 密钥
     * @param subject 主题
     * @param validTime 有效时间，(分钟)
     * @return
     * @throws Exception
     */
    public static String builderToken(String signingKey,String subject,int validTime)throws Exception{
        // builder the token
        String token = "";
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        // 设置签发时间
        calendar.setTime(new Date());
        // 设置过期时间
        calendar.add(Calendar.MINUTE, validTime);// 有效时间,分钟
        Date time = calendar.getTime();
        token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)//签发时间
                .setExpiration(time)//过期时间
                .signWith(SignatureAlgorithm.HS512, signingKey) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
        // 登录成功后，返回token
        return token;
    }

    public static String authenticationToken(String signingKey,String token) throws TokenException{
        String subject = null;
        try {
            subject = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token.replace("", ""))
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            logger.error("Token已过期: {} " + e);
            throw new TokenException("Token已过期");
        } catch (UnsupportedJwtException e) {
            logger.error("Token格式错误: {} " + e);
            throw new TokenException("Token格式错误");
        } catch (MalformedJwtException e) {
            logger.error("Token没有被正确构造: {} " + e);
            throw new TokenException("Token没有被正确构造");
        } catch (SignatureException e) {
            logger.error("签名失败: {} " + e);
            throw new TokenException("签名失败");
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常: {} " + e);
            throw new TokenException("非法参数异常");
        }
        return subject;
    }
}
