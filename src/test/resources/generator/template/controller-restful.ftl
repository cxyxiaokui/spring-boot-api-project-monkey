package ${basePackage}.${packageName}.web;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.${packageName}.domain.${modelNameUpperCamel};
import ${basePackage}.${packageName}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import ${basePackage}.core.ControllerSupport;
import ${basePackage}.core.Page;
/**
 * @Author： ${author}
 * @Date： ${date}.
 * @Description：
 **/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller extends ControllerSupport<${modelNameUpperCamel}>{

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation(value = "新增", notes = "新增")
    @ApiOperationSupport(author = "zhuoqianmingyue")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody @Validated ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return sucess();
    }

    @ApiOperation(value = "批量新增", notes = "批量新增")
    @PostMapping("/batch")
    public Result addList(@RequestBody List<${modelNameUpperCamel}> ${modelNameLowerCamel}List) {
        ${modelNameLowerCamel}Service.saveBatch(${modelNameLowerCamel}List);
        return sucess();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return sucess();
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping
    public Result deleteBatch(@RequestBody() List<Long> ids) {
        ${modelNameLowerCamel}Service.deleteByIds(ids);
        return sucess();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return sucess();
    }

    @ApiOperation(value = "批量修改", notes = "批量修改")
    @PutMapping("/batch")
    public Result updateList(@RequestBody List<${modelNameUpperCamel}> ${modelNameLowerCamel}List) {
        ${modelNameLowerCamel}Service.updateBatch(${modelNameLowerCamel}List);
        return sucess();
    }

    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id){
        ${modelNameUpperCamel} ${modelNameLowerCamel} =  ${modelNameLowerCamel}Service.getById(id);
        return sucess(${modelNameLowerCamel});
    }

    @ApiOperation(value = "分页查询",notes = "分页查询",responseContainer = "List",response = PageInfo.class)
    @GetMapping
    public Result queryist(${modelNameUpperCamel} ${modelNameLowerCamel}, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<${modelNameUpperCamel}> ${modelNameLowerCamel}List = ${modelNameLowerCamel}Service.find(${modelNameLowerCamel});
        return pageSucess(${modelNameLowerCamel}List);
    }
}