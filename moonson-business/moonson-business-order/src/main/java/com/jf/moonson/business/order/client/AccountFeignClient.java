package com.jf.moonson.business.order.client;

import com.jf.moonson.common.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("moonson-business-account")
public interface AccountFeignClient {

    @GetMapping("/decreaseMoney")
    public R<Void> decreaseMoney(@RequestParam("userId") String userId, @RequestParam("money") int money);


}
