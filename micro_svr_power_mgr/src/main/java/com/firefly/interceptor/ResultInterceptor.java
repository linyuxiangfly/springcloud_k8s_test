package com.firefly.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description
 */
public class ResultInterceptor implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;

    public ResultInterceptor(HandlerMethodReturnValueHandler delegate){
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(@Nullable Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        if(o!=null && simpleType(o)){
            //返回简单类型的数据时将值封装到类里
            SimpleType val = new SimpleType();
            val.setRet(o);

            delegate.handleReturnValue(val, methodParameter, modelAndViewContainer, nativeWebRequest);
        }else{
            delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }

    private boolean simpleType(Object o){
        if(o!=null){
            return isSingleVal(o.getClass());
        }
        return true;
    }

    private boolean isSingleVal(Class<?> cls) {
        boolean retVal = false;
        if (cls == String.class || cls == Integer.class || cls == Boolean.class || cls == Long.class || cls == Double.class || cls == Byte.class || cls == Float.class || cls == Short.class) {
            retVal = true;
        }

        return retVal;
    }

    public static class SimpleType{
        private Object ret;

        public SimpleType(){

        }

        public SimpleType(Object ret) {
            this.ret = ret;
        }

        public Object getRet() {
            return ret;
        }

        public void setRet(Object ret) {
            this.ret = ret;
        }
    }
}
