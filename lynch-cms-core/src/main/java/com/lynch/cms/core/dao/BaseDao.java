package com.lynch.cms.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

/**
 * 泛型Dao 数据库操作工具集 Inf
 * 
 * @param <T> 实体对象
 * @param <PK> 主键类型
 * @author Lynch
 * @version 1.0
 */
public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 得到实体class对象
	 * 
	 * @return Class<T> 实体的class对象
	 */
	Class<T> getEntityClass();
	
	/**
	 * 设置实体的class对象
	 * 
	 * @param entityClass 实体的class对象
	 */
	void setEntityClass(Class<T> entityClass);
	
	/**
	 * 保存实体对象
	 * 
	 * @param entity 实体对象
	 */
	void save(T entity);
	
	/**
	 * 更新实体对象
	 * 
	 * @param entity 实体对象
	 */
	void update(T entity);
	
	/**
	 * 保存或者更新实体对象
	 * 
	 * @param entity 实体对象
	 */
	void saveOrUpdate(T entity);
	
	/**
	 * 保存或者更新实体对象集合
	 * 
	 * @param entities 实体对象集合
	 */
	void saveOrUpdateAll(Collection<T> entities);
	
	/**
	 * 合并方式更新实体对象(对比，只更新修改过的字段)
	 * 
	 * @param entity 实体对象
	 */
	void merge(T entity);
	
	/**
	 * load方式查询实体对象(返回代理对象，用到对象的时候才发SQL查询，既延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	T load(PK entityId);
	
	/**
	 * load方式查询全部对象(延迟加载)
	 * 
	 * @return List<T> 实体对象的一个List列表
	 */
	List<T> loadAll();
	
	/**
	 * get方式查询对象(返回真实对象，会马上发SQL查询对象，既非延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	T get(PK entityId);
	
	/**
	 * 删除对象(按照实体)
	 * 
	 * @param entity 实体对象
	 */
	void delete(T entity);
	
	/**
	 * 删除对象(按照id)
	 * 
	 * @param entityId 实体对象id
	 */
	void delete(PK entityId);
	
	/**
	 * 删除全部
	 * 
	 * @param entities 实体对象集合
	 */
	void deleteAll(Collection<T> entities);
	
	/**
	 * 清除缓存(强制清除session里的缓存)
	 */
	void clear();
	
	/**
	 * flush(强制内存和数据库同步)
	 */
	void flush();
	
	/**
	 * 通过HQL查询实体对象列表
	 * 
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 * @return List<T> 实体对象的List列表
	 */
	List<T> find(String hql, Object... values);
	
	/**
	 * 通过HQL查询唯一对象
	 * 
	 * @param hql hql查询语句
	 * @param values 数量可变的参数
	 * @return Object 唯一对象
	 */
	Object findUnique(String hql, Object... values);
	
	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 * 
	 * @param hql hql查询语句
	 * @param values 数量可变的参数
	 * @return Query hibernate的Query对象
	 */
	Query createQuery(String hql, Object... values);
	
	/**
	 * hql分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @param first 当前页数据的第一条数据，在数据库中的位置
	 * @param max 一个页面要显示的内容条数
	 * @param param 查询条件的Map集合
	 * @return List<T> 每页需要显示的list
	 */
	List<T> pageList(final String hql, final int first, final int max,
			final Map<String,Object> param);
	
	/**
	 * 分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @param param 查询条件的Map集合
	 * @return int 总页数
	 */
	int pageRows(String hql, Map<String,Object> param);
	
	/**
	 * 分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @param first 当前页数据的第一条数据，在数据库中的位置
	 * @param max 一个页面要显示的内容条数
	 * @return List<T> 每页需要显示的list
	 */
	List<T> pageList(final String hql, final int first, final int max);
	
	/**
	 * 分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @return int 总页数
	 */
	int pageRows(String hql);
	
}
