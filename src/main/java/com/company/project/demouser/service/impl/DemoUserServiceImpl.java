package com.company.project.demouser.service.impl;

import com.company.project.demouser.mapper.DemoUserMapper;
import com.company.project.demouser.domain.DemoUser;
import com.company.project.demouser.service.DemoUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.company.project.core.IMapper;


/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/08/09.
 * @Description：
 **/
@Service
public class DemoUserServiceImpl extends AbstractService<DemoUser> implements DemoUserService {

    @Autowired
    private DemoUserMapper demoUserMapper;

    @Override
    public IMapper<DemoUser> getMapper() {
        return demoUserMapper;
    }

}
