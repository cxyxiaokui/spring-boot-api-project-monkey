package com.company.project.demoUser.service.impl;

import com.company.project.demoUser.mapper.DemoUserMapper;
import com.company.project.demoUser.domain.DemoUser;
import com.company.project.demoUser.service.DemoUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import com.company.project.core.IMapper;


/**
 * @Author： CodeGenerator
 * @Date： 2020/08/08.
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
