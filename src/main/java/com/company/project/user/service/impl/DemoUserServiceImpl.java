package com.company.project.user.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.IMapper;
import com.company.project.user.domain.DemoUser;
import com.company.project.user.mapper.DemoUserMapper;
import com.company.project.user.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/6/21 7:22 下午
 * @Description：
 **/
@Service
public class DemoUserServiceImpl extends AbstractService<DemoUser> implements DemoUserService {

    @Autowired
    private DemoUserMapper userMapper;

    @Override
    public IMapper<DemoUser> getMapper() {
        return userMapper;
    }

}
