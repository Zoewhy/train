package com.wj.train.batch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.wj")
@MapperScan("com.wj.train.*.mapper")
@EnableFeignClients("com.wj.train.batch.feign")
public class BatchApplication {

    public static final Logger LOG = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        Environment environment = app.run(args).getEnvironment();
        LOG.info("启动成功！～");
        LOG.info("测试地址:\thttp://127.0.0.1:{}", environment.getProperty("server.port"));
    }


}
