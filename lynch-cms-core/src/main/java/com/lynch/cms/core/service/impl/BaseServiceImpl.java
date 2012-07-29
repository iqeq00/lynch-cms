package com.lynch.cms.core.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lynch.cms.core.dao.BaseDao;
import com.lynch.cms.core.model.vo.PageBean;
import com.lynch.cms.core.model.vo.Pager;
import com.lynch.cms.core.service.BaseService;

/**
 * 泛型Service 服务操作工具集 Impl
 * 
 * @param <T> 实体对象
 * @param <PK> 主键类型
 * @author Lynch
 * @version 1.0
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	/**
	 * 泛型Dao对象 
	 */
	public BaseDao<T, PK> baseDao;

	/**
	 * 得到泛型Dao对象 
	 */
	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	/**
	 * 设置泛型Dao对象 
	 */
	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 保存实体对象
	 * 
	 * @param entity 实体对象
	 */
	public void save(T entity) {
		baseDao.save(entity);
	}
	
	/**
	 * 更新实体对象
	 * 
	 * @param entity 实体对象
	 */
	public void update(T entity) {
		baseDao.update(entity);
	}
	
	/**
	 * 保存或者更新实体对象
	 * 
	 * @param entity 实体对象
	 */
	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
	}
	
	/**
	 * 保存或者更新实体对象集合
	 * 
	 * @param entities 实体对象集合
	 */
	public void saveOrUpdateAll(Collection<T> entities) {
		baseDao.saveOrUpdateAll(entities);
	}
	
	/**
	 * 合并方式更新实体对象(对比，只更新修改过的字段)
	 * 
	 * @param entity 实体对象
	 */
	public void merge(T entity) {
		baseDao.merge(entity);
	}
	
	/**
	 * load方式查询实体对象(返回代理对象，用到对象的时候才发SQL查询，既延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	public T load(PK entityId) {
		T load = baseDao.load(entityId);
		return load;
	}
	
	/**
	 * load方式查询全部实体对象(延迟加载) 
	 * 
	 * @return List<T> 实体对象的List列表
	 */
	public List<T> loadAll() {
		return baseDao.loadAll();
	}
	
	/**
	 * get方式查询实体对象(返回真实对象，会马上发SQL查询对象，既非延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	public T get(PK entityId) {
		T get = baseDao.get(entityId);
		return get;
	}
	
	/**
	 * 删除实体对象(按照实体)
	 * 
	 * @param entity 实体对象
	 */
	public void delete(T entity) {
		baseDao.delete(entity);
	}
	
	/**
	 * 删除实体对象(按照id)
	 * 
	 *  @param entityId 实体对象id
	 */
	public void delete(PK entityId) {
		baseDao.delete(entityId);
	}
	
	/**
	 * 删除全部实体对象 (按照实体对象集合)
	 * 
	 * @param entities 实体对象集合
	 */
	public void deleteAllByTs(Collection<T> entities) {
		baseDao.deleteAll(entities);
	}
	
	/**
	 * 删除全部实体对象(按照实体对象Id集合)
	 * 
	 * @param pks 实体对象ID集合
	 */
	public void deleteAllByPKs(Collection<PK> pks) {
		
		Iterator<PK> Iterator = pks.iterator();
		while (Iterator.hasNext()) {
			PK pk = Iterator.next();
			baseDao.delete(pk);
		}
	}
	
	/**
	 * 清除缓存(强制清除session里的缓存)
	 */
	public void clear() {
		baseDao.clear();
	}
	
	/**
	 * flush(强制内存和数据库同步)
	 */
	public void flush() {
		baseDao.flush();
	}	
	
	/**
	 * 通过HQL查询实体对象列表
	 * 
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 * @return List<T> 实体对象的List列表
	 */
	public List<T> find(String hql, Object... values) {
		return baseDao.find(hql, values);
	}
	
	/**
	 * 通过HQL查询唯一对象
	 * 
	 * @param hql hql查询语句
	 * @param values 数量可变的参数
	 * @return Object 唯一对象
	 */
	public Object findUnique(String hql, Object... values) {
		return baseDao.findUnique(hql, values);
	}
	
	/**
	 * 分页查询(服务器分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @param param 查询条件的Map集合
	 * @return PageBean 分页对象
	 */
	public PageBean<T> pageBeanList(int pageSize, int page, String hql, Map<String,Object> param) {
		
		PageBean<T> pageBean = new PageBean<T>() ;
		int allRow = baseDao.pageRows(hql, param) ;
		int totalPage = pageBean.countTotalPage(pageSize, allRow) ;
		int currentPage = pageBean.countCurrentPage(page) ;
		int first = pageBean.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max, param) ;
		pageBean.setCurrentPage(currentPage) ;
		pageBean.setStartPage() ;
		pageBean.setEndPage() ;
		pageBean.setList(list) ;
		pageBean.setTotalPage(totalPage) ;
		pageBean.setAllRow(allRow) ;
		return pageBean;
	} 
	
	/**
	 * 分页查询(服务器分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @return PageBean 分页对象
	 */
	public PageBean<T> pageBeanList(int pageSize, int page ,String hql) {
		
		PageBean<T> pageBean = new PageBean<T>() ;
		int allRow = baseDao.pageRows(hql) ;
		int totalPage = pageBean.countTotalPage(pageSize, allRow) ;
		int currentPage = pageBean.countCurrentPage(page) ;
		int first = pageBean.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max) ;
		pageBean.setCurrentPage(currentPage) ;
		pageBean.setStartPage() ;
		pageBean.setEndPage() ;
		pageBean.setList(list) ;
		pageBean.setTotalPage(totalPage) ;
		pageBean.setAllRow(allRow) ;
		return pageBean;
	}
	
	/**
	 * 分页查询(服务器分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @return PageBean 分页对象
	 */
	public PageBean<T> pageBeanList(int pageSize, int page) {
		
		PageBean<T> pageBean = new PageBean<T>() ;
		String hql = "FROM " + baseDao.getEntityClass().getName();
		int allRow = baseDao.pageRows(hql) ;
		int totalPage = pageBean.countTotalPage(pageSize, allRow) ;
		int currentPage = pageBean.countCurrentPage(page) ;
		int first = pageBean.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max) ;
		pageBean.setCurrentPage(currentPage) ;
		pageBean.setStartPage() ;
		pageBean.setEndPage() ;
		pageBean.setList(list) ;
		pageBean.setTotalPage(totalPage) ;
		pageBean.setAllRow(allRow) ;
		return pageBean;
	}
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @param param 查询条件的Map集合
	 * @return Pager 分页对象
	 */
	public Pager<T> pagerList(int pageSize, int page, String hql, Map<String,Object> param) {
		
		Pager<T> pager = new Pager<T>() ;
		int allRow = baseDao.pageRows(hql, param) ;
		int totalPage = pager.countTotalPage(pageSize, allRow) ;
		int currentPage = pager.countCurrentPage(page) ;
		int first = pager.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max, param) ;
		pager.setCurrentPage(currentPage) ;
		pager.setPageSize(pageSize);
		pager.setList(list) ;
		pager.setTotalPage(totalPage) ;
		pager.setAllRow(allRow) ;
		return pager;
	} 
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @param hql 查询分页信息的hql语句
	 * @return Pager 分页对象
	 */
	public Pager<T> pagerList(int pageSize, int page, String hql) {
		
		Pager<T> pager = new Pager<T>() ;
		int allRow = baseDao.pageRows(hql) ;
		int totalPage = pager.countTotalPage(pageSize, allRow) ;
		int currentPage = pager.countCurrentPage(page) ;
		int first = pager.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max) ;
		pager.setCurrentPage(currentPage) ;
		pager.setPageSize(pageSize);
		pager.setList(list) ;
		pager.setTotalPage(totalPage) ;
		pager.setAllRow(allRow) ;
		return pager;
	} 
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @return Pager 分页对象
	 */
	public Pager<T> pagerList(int pageSize, int page) {
		
		Pager<T> pager = new Pager<T>() ;
		String hql = "FROM " + baseDao.getEntityClass().getName();
		int allRow = baseDao.pageRows(hql) ;
		int totalPage = pager.countTotalPage(pageSize, allRow) ;
		int currentPage = pager.countCurrentPage(page) ;
		int first = pager.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max) ;
		pager.setCurrentPage(currentPage) ;
		pager.setPageSize(pageSize);
		pager.setList(list) ;
		pager.setTotalPage(totalPage) ;
		pager.setAllRow(allRow) ;
		return pager;
	} 
	
	/**
	 * 分页查询(客户端分页)
	 * 
	 * @param pageSize 一个页面要显示的内容条数
	 * @param page 当前页	
	 * @return Pager 分页对象
	 */
	public Pager<T> PaginationList(int pageSize, int page) {
		
		Pager<T> pager = new Pager<T>() ;
		String hql = "FROM " + baseDao.getEntityClass().getName();
		int allRow = baseDao.pageRows(hql) ;
		int totalPage = pager.countTotalPage(pageSize, allRow) ;
		int currentPage = pager.countCurrentPage(page) ;
		int first = pager.countFirst(pageSize, currentPage) ;
		int max = pageSize ;
		List<T> list = baseDao.pageList(hql, first, max) ;
		pager.setCurrentPage(currentPage) ;
		pager.setPageSize(pageSize);
		pager.setList(list) ;
		pager.setTotalPage(totalPage) ;
		pager.setAllRow(allRow) ;
		return pager;
	}
	
}
