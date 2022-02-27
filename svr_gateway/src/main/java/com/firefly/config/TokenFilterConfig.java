package com.firefly.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/19
 * @Description
 */
@Component
@ConfigurationProperties(prefix = "token-filter")
public class TokenFilterConfig {
    /**
     * 记录URL和method
     */
    public static class UrlMethod{
        private String url;
        private String method;

        public UrlMethod(String url, String method) {
            this.url = url;
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    private boolean enabled;
    private List<String> exceptUrls;

    public List<UrlMethod> getExceptUrlMethods() {
        return exceptUrlMethods;
    }

    public void setExceptUrlMethods(List<UrlMethod> exceptUrlMethods) {
        this.exceptUrlMethods = exceptUrlMethods;
    }

    private List<UrlMethod> exceptUrlMethods=new ArrayList<>();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;

        for(String url:exceptUrls){
            String[] s=url.split(":");
            if(s!=null){
                if(s.length==2){
                    exceptUrlMethods.add(new UrlMethod(s[0],s[1]));
                }else if(s.length==1){
                    //默认的所有method
                    exceptUrlMethods.add(new UrlMethod(s[0],"^"));
                }
            }
        }
    }
}
