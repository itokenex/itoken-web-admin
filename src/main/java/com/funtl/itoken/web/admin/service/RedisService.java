package com.funtl.itoken.web.admin.service;

import com.funtl.itoken.web.admin.hystrix.ServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-redis",fallback = ServiceHystrix.class)
public interface RedisService {

    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value,
                      @RequestParam(value = "seconds") long seconds);

    @RequestMapping(value = "putData",method = RequestMethod.GET)
    public String putData(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value,
                          @RequestParam(value = "seconds") long seconds);

    @RequestMapping(value = "getData",method = RequestMethod.GET)
    public String getData(@RequestParam(value = "key") String key);

    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello();
}
