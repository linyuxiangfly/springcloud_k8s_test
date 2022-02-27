package com.firefly.entity.power.userauths.verified;

import com.firefly.entity.power.UserAuths;

public interface IdentityTypeVerified {
    //验证成功返回用户授权信息
    UserAuths verified(Object sender,UserAuths userAuths);
}
