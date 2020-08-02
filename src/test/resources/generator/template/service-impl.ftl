package ${basePackage}.${packageName}.service.impl;

import ${basePackage}.${packageName}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.${packageName}.domain.${modelNameUpperCamel};
import ${basePackage}.${packageName}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import ${basePackage}.core.IMapper;


/**
 * @Author： ${author}
 * @Date： ${date}.
 * @Description：
 **/
@Service
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public IMapper<${modelNameUpperCamel}> getMapper() {
        return ${modelNameLowerCamel}Mapper;
    }

}
