package com.lynch.cms.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lynch.cms.core.dao.BaseDao;

/**
 * 泛型Dao 数据库操作工具集 Impl
 * 
 * @param <T> 实体对象
 * @param <PK> 主键类型
 * @author Lynch
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport
		implements BaseDao<T, PK> {

	/** 
	 * 实体class对象 
	 */
	public Class<T> entityClass;
	
	/**
	 * 根据泛型化类反射初始化实体的class对象 
	 */
	public BaseDaoImpl() {
		
		entityClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * 配置注入
	 * 
	 * @param sessionFactory hibernate的sessionFactory
	 */
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 得到实体class对象
	 * 
	 * @return Class<T> 实体的class对象
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 设置实体的class对象
	 * 
	 * @param entityClass 实体的class对象
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity 实体对象
	 */
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity 实体对象
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * 保存或者更新实体对象
	 * 
	 * @param entity 实体对象
	 */
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 保存或者更新实体对象集合
	 * 
	 * @param entities 实体对象集合
	 */
	public void saveOrUpdateAll(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 合并方式更新实体对象(对比，只更新修改过的字段)
	 * 
	 * @param entity 实体对象
	 */
	public void merge(T entity) {
		this.getHibernateTemplate().merge(entity);
	}

	/**
	 * load方式查询实体对象(返回代理对象，用到对象的时候才发SQL查询，既延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	public T load(PK entityId) {
		T load = (T) this.getHibernateTemplate().load(getEntityClass(),entityId);
		return load;
	}

	/**
	 * load方式查询全部对象(延迟加载)
	 * 
	 * @return List<T> 实体对象的一个List列表
	 */
	public List<T> loadAll() {
		return (List<T>) this.getHibernateTemplate().loadAll(getEntityClass());
	}

	/**
	 * get方式查询对象(返回真实对象，会马上发SQL查询对象，既非延迟加载)
	 * 
	 * @param entityId 实体对象id
	 * @return T 实体对象
	 */
	public T get(PK entityId) {
		T get = (T) this.getHibernateTemplate().get(getEntityClass(), entityId);
		return get;
	}

	/**
	 * 删除对象(按照实体)
	 * 
	 * @param entity 实体对象
	 */
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	/**
	 * 删除对象(按照id)
	 * 
	 * @param entityId 实体对象id
	 */
	public void delete(PK entityId) {
		T entity = get(entityId);
		this.getHibernateTemplate().delete(entity);
	}

	/**
	 * 删除全部
	 * 
	 * @param entities 实体对象集合
	 */
	public void deleteAll(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 清除缓存(强制清除session里的缓存)
	 */
	public void clear() {
		this.getHibernateTemplate().clear();
	}

	/**
	 * flush(强制内存和数据库同步)
	 */
	public void flush() {
		this.getHibernateTemplate().flush();
	}
	
	/**
	 * 通过HQL查询实体对象列表
	 * 
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 * @return List<T> 实体对象的List列表
	 */
	public List<T> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}
	
	/**
	 * 通过HQL查询唯一对象
	 * 
	 * @param hql hql查询语句
	 * @param values 数量可变的参数
	 * @return Object 唯一对象
	 */
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}
	
	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 * 
	 * @param hql hql查询语句
	 * @param values 数量可变的参数
	 * @return Query hibernate的Query对象
	 */
	public Query createQuery(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	/**
	 * hql分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @param first 当前页数据的第一条数据，在数据库中的位置
	 * @param max 一个页面要显示的内容条数
	 * @param param 查询条件的Map集合
	 * @return List<T> 每页需要显示的list
	 */
	public List<T> pageList(final String hql, final int first, final int max,
			final Map<String, Object> param) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (param != null && param.size() > 0) {
					for (String s : param.keySet()) {
						query.setParameter(s, param.get(s));
					}
				}
				query.setFirstResult(first);
				query.setMaxResults(max);
				List<T> list = query.list();
				return list;
			}
		});
	}

	/**
	 * 分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @param param 查询条件的Map集合
	 * @return int 总页数
	 */
	public int pageRows(String hql, Map<String, Object> param) {

		Query query = this.getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (String s : param.keySet()) {
				query.setParameter(s, param.get(s));
			}
		}
		List<T> list = query.list();
		return list != null ? list.size() : 0;
	}

	/**
	 * 分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @param first 当前页数据的第一条数据，在数据库中的位置
	 * @param max 一个页面要显示的内容条数
	 * @return List<T> 每页需要显示的list
	 */
	public List<T> pageList(final String hql, final int first, final int max) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(first);
				query.setMaxResults(max);
				List<T> list = query.list();
				return list;
			}
		});
	}

	/**
	 * 分页查询(对所需要的数据进行分页操作)
	 * 
	 * @param hql 查询分页信息的hql语句
	 * @return int 总页数
	 */
	public int pageRows(String hql) {

		List<T> list = null;
		Query query = this.getSession().createQuery(hql);
		list = query.list();
		return list != null ? list.size() : 0;
	}
	
}
