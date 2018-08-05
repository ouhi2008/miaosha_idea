package com.imooc.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 *
 * 打WAR包需要：
 * 1.extends SpringBootServletInitializer
 * 2.实现方法 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
 * 3.命令行下执行 mvn clean package

 * 打JAR包需要：
 * 1.删除extends SpringBootServletInitializer
 * 2.注释方法 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
 * 3.命令行下执行 mvn clean package
 * 4.执行： java -jar target\miaosha_idea.jar
 */

@SpringBootApplication
public class MainApplication  {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }

    /**
     *
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApplication.class);
    }
    */
}
