package com.firefly.controller;

import com.firefly.config.TokenUtilConfig;
import com.firefly.entity.SimpleType;
import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.entity.power.UserAuths;
import com.firefly.exception.NoLoginException;
import com.firefly.utils.MenuItemUtil;
import com.firefly.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：
 * <p>
 *
 * @author: 林宇翔
 * @date: 2020/05/18
 */
@RestController
public class LoginController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(LoginController.class);

    private TokenUtil tokenUtil=null;
    private TokenUtilConfig tokenUtilConfig=null;

    public TokenUtilConfig getTokenUtilConfig() {
        return tokenUtilConfig;
    }

    @Autowired
    public void setTokenUtilConfig(TokenUtilConfig tokenUtilConfig) {
        this.tokenUtilConfig = tokenUtilConfig;
        tokenUtil=tokenUtilConfig.getTokenUtil();
    }

    @PostMapping("/login")
//    @CrossOrigin
    public SimpleType login(@RequestBody UserAuths userAuths) throws Exception {
        return SimpleType.convert(tokenUtil.login(userAuths));
    }

    @GetMapping("mySelf")
    public User mySelf(@RequestHeader(value="token", required=false) String token) {
        User retVal=null;
        if(token!=null){
            retVal=tokenUtil.getUserByToken(token);
        }else{
            throw new NoLoginException();
        }
        return retVal;
    }

    @RequestMapping("/getFunc")
    public List<Func> getFunc(
            @RequestHeader(value="token", required=false) String token
    ) {
        User user=tokenUtil.getUserByToken(token);
        return tokenUtil.getFuncByUser(user);
    }

    @RequestMapping("/getMenuTree")
    public List<MenuItemUtil.MenuItem> getMenuTree(
            @RequestHeader(value="token", required=false) String token
    ) {
        User user=tokenUtil.getUserByToken(token);
        return tokenUtil.getMenuTreeByUser(user);
    }
}
