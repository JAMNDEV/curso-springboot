package com.jamnd.cursospringboot.biblioteca.config;

import com.jamnd.cursospringboot.biblioteca.interceptor.AuditoriaInterceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ObjectProvider<AuditoriaInterceptor> auditoriaInterceptorProvider;

    public WebMvcConfig(ObjectProvider<AuditoriaInterceptor> auditoriaInterceptorProvider) {
        this.auditoriaInterceptorProvider = auditoriaInterceptorProvider;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Lamina 8-10: interceptor transversal para auditar peticiones REST.
        auditoriaInterceptorProvider.ifAvailable(interceptor ->
                registry.addInterceptor(interceptor)
                        .addPathPatterns("/api/**")
        );
    }
}
