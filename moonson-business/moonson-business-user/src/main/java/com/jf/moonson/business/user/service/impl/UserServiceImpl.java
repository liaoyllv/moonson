package com.jf.moonson.business.user.service.impl;

import com.jf.moonson.business.user.repo.entity.SysUser;
import com.jf.moonson.business.user.repo.mapper.SysUserMapper;
import com.jf.moonson.business.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser getById(Long Id) {
        return sysUserMapper.selectByPrimaryKey(Id);
    }
}
