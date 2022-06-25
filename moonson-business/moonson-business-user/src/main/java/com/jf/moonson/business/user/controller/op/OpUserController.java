package com.jf.moonson.business.user.controller.op;

import com.jf.moonson.business.user.client.AccountFeignClient;
import com.jf.moonson.business.user.repo.entity.SysUser;
import com.jf.moonson.business.user.service.UserService;
import com.jf.moonson.common.api.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/op/user")
@RequiredArgsConstructor
public class OpUserController {

    private final UserService userService;
    private final AccountFeignClient accountFeignClient;

    @GetMapping
    public R<String> getCurrentUserInfo() {
        return R.ok("moonson");
    }

    @GetMapping("/info/{id}")
    public R<SysUser> getInfoById(@PathVariable(value = "id") Long id) {
        log.info("id:{}", id);
        return R.ok(userService.getById(id));
    }

    @GetMapping("/recharge")
    public R<Void> recharge(@PathVariable(value = "id") Long id) {
        userService.getById(id);
        log.info("id:{}", id);
        accountFeignClient.decreaseMoney(String.valueOf(id), 10);
        return R.ok();
    }

}
