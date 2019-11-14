package com.funtl.itoken.web.admin.hystrix;

import com.funtl.itoken.commom.BaseResult;
import com.funtl.itoken.commom.constants.HttpStatusConstants;
import com.funtl.itoken.commom.utils.MapperUtils;
import com.funtl.itoken.web.admin.service.AdminService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * 熔断配置
 */
@Component
public class WebAdminHystrix implements AdminService {

    @Override
    public String login(String accountId, String name) {
        BaseResult baseResult = BaseResult.notOk(
                Lists.newArrayList(
                        new BaseResult.Error(String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
                                HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
