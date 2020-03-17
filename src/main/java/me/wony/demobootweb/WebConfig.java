package me.wony.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new PersonFormatter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingInterceptor());
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi")
                .order(-1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //static은 default
        registry.addResourceHandler("/mob/**")
                .addResourceLocations("classpath:/mob/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));//캐슁을 10분동안 할것이다.
    }

    //추가하게 되면 기본 converter을 사용못함
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//    }// 추가만 하고 싶다면 extendMessageConverters 사용하는게 좋다. 근데 이것조차 쓸일 거의 없다.
    // 의존성만 추가하면 기본 컨버터에 붙는다.

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}
