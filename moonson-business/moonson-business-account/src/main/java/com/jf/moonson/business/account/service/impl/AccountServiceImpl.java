package com.jf.moonson.business.account.service.impl;

import com.jf.moonson.business.account.repo.entity.Account;
import com.jf.moonson.business.account.repo.entity.AccountExample;
import com.jf.moonson.business.account.repo.mapper.AccountMapper;
import com.jf.moonson.business.account.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void decreaseMoney(String userId, int money) {

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUserIdEqualTo(userId);

        Account account = Account.builder().money(money).build();
        accountMapper.updateByExampleSelective(account, accountExample);
    }
}
