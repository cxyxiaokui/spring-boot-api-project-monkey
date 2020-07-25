package com.company.project.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * DAO 层的基础方法封装
 *
 * @Author：zhuoqianmingyue
 * @Date： 2020/6/21
 * @Description：所有 MyBatis Dao(Mapper) 需要实现该接口
**/
public interface IMapper<T> {
    /**
     * 根据id 查找数据库数据
     * @param id
     * @return
     */
    T getById(@Param("id") Long id);

    /**
     * 根据id集合查询数据库数据
     * @param ids
     * @return
     */
    List<T> findByIds(@Param("ids") List<Long> ids);

    /**
     * 查询所有
     * @return
     */
    List<T> find();

    /**
     * 根据Map 条件查询
     * @param map
     * @return
     */
    List<T> findByMap(Map<String, Object> map);

    /**
     * 保存
     * @param t
     * @return
     */
    Long save(T t);

    /**
     * 批量保存
     * @param list
     * @return
     */
    int saveBatch(@Param("list") List<T> list);

    /**
     * 更新
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateBatch(@Param("list") List<T> list);

    /**
     * 根据Id 删除数据
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据id集合批量删除数据
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") List<Long> ids);
}
