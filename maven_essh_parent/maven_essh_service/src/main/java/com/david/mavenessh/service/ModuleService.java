package com.david.mavenessh.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.david.mavenessh.domain.Module;
import com.david.mavenessh.utils.Page;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface ModuleService {
	//查询所有，带条件查询
	public  List<Module> find(String hql, Class<Module> entityClass, Object[] params);
	//获取一条记录
	public  Module get(Class<Module> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
	public  Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params);
	
	//新增和修改保存
	public  void saveOrUpdate(Module entity);
	//批量新增和修改保存
	public  void saveOrUpdateAll(Collection<Module> entitys);
	
	//单条删除，按id
	public  void deleteById(Class<Module> entityClass, Serializable id);
	//批量删除
	public  void delete(Class<Module> entityClass, Serializable[] ids);
}
