package com.firefly.userauths.verified;

import com.firefly.entity.power.UserAuths;
import com.firefly.entity.power.userauths.verified.IdentityTypeVerified;
import com.firefly.exception.UserNameDoesNotExistException;
import com.firefly.exception.WrongPasswordException;
import com.firefly.rmi.PowerMgrRemote;
import com.firefly.utils.TokenUtil;

import java.util.List;

/**
 * 密码验证方式
 */
public class IdentityTypeVerifiedPassword implements IdentityTypeVerified {
    @Override
    public UserAuths verified(Object sender,UserAuths userAuths) {
        UserAuths retVal=null;

        //如果果TokenUtil是sender的父类
        if(TokenUtil.class.isAssignableFrom(sender.getClass())){
            //拿到调用该方法的对象
            TokenUtil tokenUtil=(TokenUtil)sender;
            PowerMgrRemote powerMgrRemote=tokenUtil.getPowerMgrRemote();

            List<UserAuths> userAuthsList=powerMgrRemote.user_auths_get(
                    userAuths.getIdentityType(),
                    userAuths.getIdentityFier());//远程调用接口获取用户授权信息
            if(userAuthsList!=null && userAuthsList.size()>0) {
                UserAuths ua = userAuthsList.get(0);

                if(!ua.getCredential().equals(userAuths.getCredential())){
                    throw new WrongPasswordException();//密码错误
                }

                retVal=ua;
            }else{
                throw new UserNameDoesNotExistException();//用户不存在
            }
        }
        return retVal;
    }
}
