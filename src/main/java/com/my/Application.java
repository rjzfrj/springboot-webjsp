package com.my;

/**
 * @Author: liuzf
 * @Description:
 * @Date: Create in 2018/1/10 11:05
 * @Modified By:
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class Application {
//public class Application extends WebMvcConfigurerAdapter{


//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//		FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
//		FastJsonConfig fastJsonConfig=new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//		fastConverter.setFastJsonConfig(fastJsonConfig);
//		converters.add(fastConverter);
//
//	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}