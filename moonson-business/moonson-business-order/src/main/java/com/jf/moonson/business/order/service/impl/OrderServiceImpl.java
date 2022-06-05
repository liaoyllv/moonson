package com.jf.moonson.business.order.service.impl;

import cn.hutool.json.JSONUtil;
import com.jf.moonson.business.order.client.AccountFeignClient;
import com.jf.moonson.business.order.repo.entity.Order;
import com.jf.moonson.business.order.repo.mapper.OrderMapper;
import com.jf.moonson.business.order.service.OrderService;
import com.jf.moonson.common.api.ApiErrorEnum;
import com.jf.moonson.common.api.ApiException;
import com.jf.moonson.common.api.R;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final AccountFeignClient accountFeignClient;

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void create(String userId, int money) {

        Order order = Order.builder().userId(userId).money(money).count(1).build();
        orderMapper.insertSelective(order);

        // 更新账户
        R<Void> r = accountFeignClient.decreaseMoney(userId, money);
        System.out.println(JSONUtil.toJsonPrettyStr(r));

        // 模拟失败
        throw new ApiException(ApiErrorEnum.DEFAULT);
    }
}
