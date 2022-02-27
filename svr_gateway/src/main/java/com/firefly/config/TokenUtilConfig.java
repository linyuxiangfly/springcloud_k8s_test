package com.firefly.config;

import com.firefly.entity.power.userauths.verified.IdentityTypeVerified;
import com.firefly.rmi.PowerMgrRemote;
import com.firefly.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @Author fly
 * @Date 2019/6/19
 * @Description
 */
@Component
@ConfigurationProperties(prefix = "token-util")
public class TokenUtilConfig {
    private String signingKey;//密钥
    private int validTime;//token有效时长,(分钟)

//    @Value("#{${token-util.user-auths.identity-type-verified}}")
    private Map<String, Class<IdentityTypeVerified>> identityTypeVerified=null;

    private PowerMgrRemote powerMgrRemote;

    private TokenUtil tokenUtil=new TokenUtil();

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
        tokenUtil.setSigningKey(signingKey);
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
        tokenUtil.setValidTime(validTime);
    }

    public Map<String, Class<IdentityTypeVerified>> getIdentityTypeVerified() {
        return identityTypeVerified;
    }

    public void setIdentityTypeVerified(Map<String, Class<IdentityTypeVerified>> identityTypeVerified) {
        this.identityTypeVerified = identityTypeVerified;

        //将配置值设置进TokenUtil
        tokenUtil.setIdentityTypeVerifiedMap(identityTypeVerified);
    }

    public PowerMgrRemote getPowerMgrRemote() {
        return powerMgrRemote;
    }

    @Autowired
    public void setPowerMgrRemote(PowerMgrRemote powerMgrRemote) {
        this.powerMgrRemote = powerMgrRemote;
        tokenUtil.setPowerMgrRemote(powerMgrRemote);
    }

    public TokenUtil getTokenUtil() {
        return tokenUtil;
    }

    public void setTokenUtil(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }
}
