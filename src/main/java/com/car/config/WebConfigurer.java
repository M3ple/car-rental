package com.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * web configure
 *
 * @author Gandalf
 * @since 2022/6/13
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/js/**", "/css/**", "/images/**", "/login", "/register");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addReturnValueHandlers(
            List<HandlerMethodReturnValueHandler> handlers) {
        // TODO Auto-generated method stub

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // TODO Auto-generated method stub

    }

    @Override
    public void configureHandlerExceptionResolvers(
            List<HandlerExceptionResolver> resolvers) {
        // TODO Auto-generated method stub

    }

    @Override
    public void extendHandlerExceptionResolvers(
            List<HandlerExceptionResolver> resolvers) {
        // TODO Auto-generated method stub

    }

    @Override
    public Validator getValidator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        // TODO Auto-generated method stub
        return null;
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        // 中文乱码解决
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
