package com.company.project.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @Author：lihengming
 * @Date：2017/6/23
 * @Description：
 *
 * @Editor：zhuoqianmingyue
 * @Date：2020/6/21
 * @Description：为方法添加注释并添加批量更新操作
 */
public interface Service<T> {
    /**
     * 保存
     * @param model
     * @return boolean
     */
    boolean save(T model);

    /**
     * 批量保存
     * @param models
     * @return boolean
     */
    boolean saveBatch(List<T> models);


    /**
     * 更新
     * @param model
     * @return boolean
     */
    boolean update(T model);

    /**
     * 批量更新
     * @param models
     * @return boolean
     */
    boolean updateBatch(List<T> models);

    /**
     * 通过ID查找
     * @param id
     * @return T
     */
    T getById(Long id);

    /**
     * 通过ID集合查找
     * @param ids
     * @return List<T>
     */
    List<T> findByIds(List<Long> ids);

    /**
     * 根据 Model 条件查询
     * @param t
     * @return
     */
    List<T> find(T t);
    /**
     * 添加查询
     * @param map
     * @return List<T>
     */
    List<T> findByMap(Map<String, Object> map);

    /**
     * 获取所有
     * @return List<T>
     */
    List<T> find();

    /**
     * 通过主键删除
     * @param id
     * @return boolean
     */
    boolean deleteById(Long id);
    /**
     * 批量刪除
     * @param ids
     * @return boolean
     */
    boolean deleteByIds(List<Long> ids);

}
