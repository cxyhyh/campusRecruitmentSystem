package com.zbdx.xyzp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.zbdx.xyzp.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class XyzpApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyzpApplication.class, args);
    }

}
