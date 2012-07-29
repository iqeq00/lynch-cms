package com.lynch.cms.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.lynch.cms.core.model.vo.PageBean;
import com.lynch.cms.core.model.vo.Pager;



/**
 * 泛型Service 服务操作工具集 Inf
 * 
 * @param <T> 实体对象
 * @param <PK> 主键类型
 * @author Lynch
 * @version 1.0
 */
public interface BaseService<T, PK extends Serializable> {
	
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
	 * load方式查询全部实体对象(延迟加载) 
	 * 
	 * @return List<T> 实体对象的List列表
	 */
	List<T> loadAll();
	
	/**
	 * get方式查询实体对象(返回真实对象，会马上发SQL查询对象，既非延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	T get(PK entityId);
	
	/**
	 * 删除实体对象(按照实体)
	 * 
	 * @param entity 实体对象
	 */
	void delete(T entity);
	
	/**
	 * 删除实体对象(按照id)
	 * 
	 *  @param entityId 实体对象id
	 */
	void delete(PK entityId);
	
	/**
	 * 删除全部实体对象 (按照实体对象集合)
	 * 
	 * @param entities 实体对象集合
	 */
	void deleteAllByTs(Collection<T> entities);
	
	/**
	 * 删除全部实体对象(按照实体对象Id集合)
	 * 
	 * @param pks 实体对象ID集合
	 */
	void deleteAllByPKs(Collection<PK> pks);
	
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
	 * 分页查询(服务器分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @param param 查询条件的Map集合
	 * @return PageBean 分页对象
	 */
	PageBean<T> pageBeanList(int pageSize, int page, String hql, Map<String,Object> param);
	
	/**
	 * 分页查询(服务器分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @return PageBean 分页对象
	 */
	PageBean<T> pageBeanList(int pageSize, int page ,String hql);
	
	/**
	 * 分页查询(服务器分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @return PageBean 分页对象
	 */
	PageBean<T> pageBeanList(int pageSize, int page);
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @param param 查询条件的Map集合
	 * @return Pager 分页对象
	 */
	Pager<T> pagerList(int pageSize, int page, String hql, Map<String,Object> param);
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @return Pager 分页对象
	 */
	Pager<T> pagerList(int pageSize, int page, String hql);
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @return Pager 分页对象
	 */
	Pager<T> pagerList(int pageSize, int page);
	
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @return Pager 分页对象
	 */
	Pager<T> PaginationList(int pageSize, int page);

}
