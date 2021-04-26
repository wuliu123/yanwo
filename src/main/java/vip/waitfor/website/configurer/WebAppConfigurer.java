package vip.waitfor.website.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vip.waitfor.website.interceptor.LoginInterceptor;


import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 白名单
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/user/reg");
        excludePathPatterns.add("/user/login");
        excludePathPatterns.add("/web/admin-login.html");
        excludePathPatterns.add("/web/admin-reg.html");

        // 黑名单
        List<String> PathPatterns = new ArrayList<>();
        PathPatterns.add("/user/**");
        PathPatterns.add("/web/**");

        // 注册
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(PathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }

}
