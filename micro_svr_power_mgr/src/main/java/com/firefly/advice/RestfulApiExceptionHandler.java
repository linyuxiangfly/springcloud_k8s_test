package com.firefly.advice;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 注解定义异常统一处理类
@ControllerAdvice
public class RestfulApiExceptionHandler {
    // 这里处理MissingServletRequestParameterException的异常
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    // 返回JSON数据
    @ResponseBody
    // 特别注意第二个参数为要处理的异常
    public Map<String, Object> requestExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        // 在这里可以定义返回的异常提示信息
        error.put("msg", "参数" + exception.getParameterName() + "错误");
        return error;
    }

    // 这里处理没有被上一个方法处理的异常，通常在最后的方法处理最大的Exception，保证兜底
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("msg", exception.getMessage());
        return error;
    }
}
