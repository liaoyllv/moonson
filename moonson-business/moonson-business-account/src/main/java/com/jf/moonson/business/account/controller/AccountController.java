package com.jf.moonson.business.account.controller;

import com.jf.moonson.business.account.service.AccountService;
import com.jf.moonson.common.api.R;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/decreaseMoney")
    public R<Void> decreaseMoney(@RequestParam String userId, @RequestParam int money) {
        accountService.decreaseMoney(userId, money);
        return R.ok();
    }

}
