package com.wildcreek.videotalk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类，增加自定义拦截器和解析器
 * @author ScienJus
 * @date 2015/7/30.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    private AuthorizationInterceptor authorizationInterceptor;
//
//    @Autowired
//    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor);
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(currentUserMethodArgumentResolver);
//    }
}
