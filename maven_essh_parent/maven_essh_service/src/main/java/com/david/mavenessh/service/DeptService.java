package com.david.mavenessh.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.david.mavenessh.domain.Dept;
import com.david.mavenessh.utils.Page;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface DeptService {
	//查询所有，带条件查询
	public  List<Dept> find(String hql, Class<Dept> entityClass, Object[] params);
	//获取一条记录
	public  Dept get(Class<Dept> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
	public  Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params);
	
	//新增和修改保存
	public  void saveOrUpdate(Dept entity);
	//批量新增和修改保存
	public  void saveOrUpdateAll(Collection<Dept> entitys);
	
	//单条删除，按id
	public  void deleteById(Class<Dept> entityClass, Serializable id);
	//批量删除
	public  void delete(Class<Dept> entityClass, Serializable[] ids);
}
