package com.jf.moonson.business.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jf.moonson.common.api.R;

/**
 * @author 季风
 * @version $
 * @since 2022/6/22 17:25
 */
@FeignClient("moonson-business-account")
public interface AccountFeignClient {

    @GetMapping("/decreaseMoney")
    R<Void> decreaseMoney(@RequestParam("userId") String userId, @RequestParam("money") int money);


}
