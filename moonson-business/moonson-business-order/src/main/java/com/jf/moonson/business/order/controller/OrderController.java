package com.jf.moonson.business.order.controller;

import com.jf.moonson.business.order.service.OrderService;
import com.jf.moonson.common.api.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/create")
    public R<Void> create(@RequestParam String userId,@RequestParam int money) {
        orderService.create(userId, money);
        return R.ok();
    }

}
