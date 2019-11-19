package com.funtl.itoken.web.admin.hystrix;

import com.funtl.itoken.web.admin.service.RedisService;
import org.springframework.stereotype.Component;

@Component
public class ServiceHystrix implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String putData(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String getData(String key) {
        return null;
    }

    @Override
    public String hello() {
        return null;
    }
}
