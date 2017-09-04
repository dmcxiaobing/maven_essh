package com.david.mavenessh.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.david.mavenessh.dao.BaseDao;
import com.david.mavenessh.domain.Module;
import com.david.mavenessh.service.ModuleService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.utils.UtilFuns;


/**
 * 模块service
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class ModuleServiceImpl implements ModuleService {
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Module get(Class<Module> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Module entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增

		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Module> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Module> entityClass, Serializable id) {

		baseDao.deleteById(entityClass, id);// 删除一个对象
	}

	public void delete(Class<Module> entityClass, Serializable[] ids) {

		for (Serializable id : ids) {
			this.deleteById(Module.class, id);
		}
	}
}
