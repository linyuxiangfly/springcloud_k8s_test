package com.firefly.exception;

/**
 * 描述：
 * <p>
 *
 * @author: 林宇翔
 * @date: 2018/4/11 10:24
 * 用户名不存在
 */
public class UserNameDoesNotExistException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserNameDoesNotExistException() {
        super("UserNameDoesNotExist");
    }
}
