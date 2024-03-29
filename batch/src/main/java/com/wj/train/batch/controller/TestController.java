package com.wj.train.batch.controller;

import com.wj.train.batch.feign.BusinessFeign;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    @Resource
    BusinessFeign businessFeign;
    @GetMapping("/hello")
    public String sayHello(){
        String businessHello = businessFeign.hello1();
        LOG.info(businessHello);
        return "Hello world!batch";
    }
}
