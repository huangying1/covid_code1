package com.tyut.covid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@MapperScan("com.tyut.covid.dao")
@SpringBootApplication
@Configuration
public class CovidApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(CovidApplication.class, args);

//        DataCrawler dataCrawler = new DataCrawler();
//        dataCrawler.crawling();

    }

    @Override
    protected  void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/")
                .addResourceLocations("classpath:/templates")
                .addResourceLocations("classpath:/static/web")
                .addResourceLocations("classpath:/web");
        super.addResourceHandlers(registry);
    }

}
