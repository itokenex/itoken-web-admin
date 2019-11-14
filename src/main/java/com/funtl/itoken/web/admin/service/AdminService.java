package com.funtl.itoken.web.admin.service;

import com.funtl.itoken.web.admin.hystrix.WebAdminHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-admin",fallback = WebAdminHystrix.class)
public interface AdminService {

    @RequestMapping(value = "userlogin",method = RequestMethod.GET)
    public String login(@RequestParam(value = "accountId") String accountId,
                        @RequestParam(value = "name") String name );
}
