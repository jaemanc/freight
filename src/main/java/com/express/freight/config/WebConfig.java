package com.express.freight.config;

import com.express.freight.util.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public Interceptor interceptor;

    public WebConfig(Interceptor interceptor){
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/v1/user/registration"
                        ,"/api/v1/user/non-member-registration"
                        ,"/v2/api-docs/**"
                        ,"/swagger-resources/**"
                        ,"/swagger-ui/**"
                        ,"/webjars/**"
                        ,"/v3/api-docs"
                );

        WebMvcConfigurer.super.addInterceptors(registry);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .exposedHeaders("Authorization");
    }

}
