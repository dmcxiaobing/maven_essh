package com.david.mavenessh.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.david.mavenessh.dao.BaseDao;
import com.david.mavenessh.domain.Role;
import com.david.mavenessh.service.RoleService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.utils.UtilFuns;
import com.david.mavenessh.utils.common.CommonUtils;
/**
 * 角色的业务
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class RoleServiceImpl implements RoleService{
		private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Role get(Class<Role> entityClass, Serializable id) {
		return baseDao.get(entityClass,id);
	}

	@Override
	public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.findPage(hql, page, entityClass, params);
	}
	/**
	 * 由于我们的ID需要我们自己去设置。
	 */
	@Override
	public void saveOrUpdate(Role entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			//如果ID为空，则说明为空
		}
//		System.out.println(entity);
//		System.out.println(entity);
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Role> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Role> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);//删除一个对象
	}

	@Override
	public void delete(Class<Role> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Role.class, id);
		}
	}
}
