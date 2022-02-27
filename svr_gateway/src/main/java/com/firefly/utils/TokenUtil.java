package com.firefly.utils;

import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.entity.power.UserAuths;
import com.firefly.entity.power.userauths.verified.IdentityTypeVerified;
import com.firefly.exception.TokenException;
import com.firefly.rmi.PowerMgrRemote;

import java.util.List;
import java.util.Map;

/**
 * @Author fly
 * @Date 2019/6/19
 * @Description
 */
public class TokenUtil {
    private String signingKey;//密钥
    private int validTime;//token有效时长,(分钟)

    private PowerMgrRemote powerMgrRemote;

    private Map<String,Class<IdentityTypeVerified>> identityTypeVerifiedMap;

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }

    public Map<String, Class<IdentityTypeVerified>> getIdentityTypeVerifiedMap() {
        return identityTypeVerifiedMap;
    }

    public void setIdentityTypeVerifiedMap(Map<String, Class<IdentityTypeVerified>> identityTypeVerifiedMap) {
        this.identityTypeVerifiedMap = identityTypeVerifiedMap;
    }

    public PowerMgrRemote getPowerMgrRemote() {
        return powerMgrRemote;
    }

    public void setPowerMgrRemote(PowerMgrRemote powerMgrRemote) {
        this.powerMgrRemote = powerMgrRemote;
    }

    /**
     * 登录
     * @param userAuths
     * @return
     * @throws Exception
     */
    public String login(UserAuths userAuths) throws Exception {
        User u=getUserByUserAuths(userAuths);//查找用户
        if(u!=null){
            return JwtsUtil.builderToken(signingKey,u.getId(),validTime);
        }
        return "";
    }

    /**
     * 通过token获取用户
     * @param token
     * @return
     */
    public User getUserByToken(String token){
        User retVal=null;
        if(token!=null){
            String subject= JwtsUtil.authenticationToken(signingKey,token);
            if (subject != null) {
                retVal=powerMgrRemote.user_getById(subject);//远程调用接口获取用户信息
            }
        }else{
            throw new TokenException("null");
        }
        return retVal;
    }

    /**
     * 通过用户授权查找用户
     * @param userAuths
     * @return
     */
    public User getUserByUserAuths(UserAuths userAuths){
        User retVal=null;
        //获取验证方式类
        IdentityTypeVerified itv = getUserAuthsIdentityTypeVerified(userAuths);
        //验证授权
        if(itv!=null){
            UserAuths ua=itv.verified(this,userAuths);
            if(ua!=null){
                retVal=ua.getUser();
            }
        }
        return retVal;
    }

    /**
     * 由授权用户信息获取授权验证类
     * @param userAuths
     * @return
     */
    private IdentityTypeVerified getUserAuthsIdentityTypeVerified(UserAuths userAuths){
        if(identityTypeVerifiedMap!=null){
            Class<IdentityTypeVerified> cls = identityTypeVerifiedMap.get(userAuths.getIdentityType());
            if(cls!=null){
                try {
                    return cls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取用户的所有接口权限
     * @param user
     * @return
     */
    public List<Func> getFuncByUser(User user){
        return powerMgrRemote.power_getFuncByUser(user.getId());
    }

    /**
     * 查找用户接口权限
     * @param user
     * @param url
     * @param method
     * @return
     */
    public Func findFunc(User user, String url, String method){
        List<Func> funcList=getFuncByUser(user);
        if(funcList!=null){
            for(Func func:funcList){
                if(func.getUrl().equals(url) && func.getMethod().equals(method)){
                    return func;
                }
            }
        }
        return null;
    }

    /**
     * 获取用户的菜单树
     * @param user
     * @return
     */
    public List<MenuItemUtil.MenuItem> getMenuTreeByUser(User user) {
        return powerMgrRemote.power_getMenuTreeByUser(user.getId());
    }
}
