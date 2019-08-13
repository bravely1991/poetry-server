package com.poetry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import redis.clients.jedis.Jedis;

@SpringBootApplication
@MapperScan("com.poetry.dao")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {


        Jedis jedis = new Jedis("localhost");
        System.out.println("jedis");
        System.out.println(jedis.ping());



        return application.sources(Application.class);
    }
}
