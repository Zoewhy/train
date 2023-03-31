package com.wujun.train;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String sayHello(){
        return "hello world!WWERWEB123123";
    }
}
