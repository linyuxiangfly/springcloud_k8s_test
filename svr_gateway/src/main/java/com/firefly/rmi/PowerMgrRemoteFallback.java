package com.firefly.rmi;

import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.entity.power.UserAuths;
import com.firefly.exception.UserRemoteException;
import com.firefly.utils.MenuItemUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/14
 * @Description
 */
@Component
public class PowerMgrRemoteFallback implements FallbackFactory<PowerMgrRemote> {
    @Override
    public PowerMgrRemote create(Throwable throwable) {
        String msg = throwable == null ? "" : throwable.getMessage();
        if (!StringUtils.isEmpty(msg)) {
        }
        return new PowerMgrRemote() {
            @Override
            public List<UserAuths> user_auths_get(String identityType,String identityFier){
                throw new UserRemoteException(msg);
            }

            @Override
            public List<User> user_get(String id) {
                throw new UserRemoteException(msg);
            }

            @Override
            public User user_getById(String id) {
                throw new UserRemoteException(msg);
            }

            @Override
            public List<Func> power_getFuncByUser(String userId) {
                throw new UserRemoteException(msg);
            }

            @Override
            public List<MenuItemUtil.MenuItem> power_getMenuTreeByUser(String userId) {
                throw new UserRemoteException(msg);
            }
        };
    }
}
