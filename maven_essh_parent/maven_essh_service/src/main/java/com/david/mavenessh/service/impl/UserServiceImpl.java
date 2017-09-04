package com.david.mavenessh.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.david.mavenessh.dao.BaseDao;
import com.david.mavenessh.domain.User;
import com.david.mavenessh.domain.UserInfo;
import com.david.mavenessh.service.UserService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.utils.UtilFuns;
import com.david.mavenessh.utils.common.CommonUtils;
/**
 * 系统管理中用户管理
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserServiceImpl implements UserService{
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass,id);
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.findPage(hql, page, entityClass, params);
	}
	/**
	 * 由于我们的ID需要我们自己去设置。
	 */
	@Override
	public void saveOrUpdate(User entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			//如果ID为空，则说明为空
			String id = CommonUtils.getUUIDRandomNum();
			entity.setId(id);//设置用户自己的ID
			entity.getUserInfo().setId(id);//设置详细信息的ID关联
			entity.getUserInfo().getManager().setId(entity.getUserInfo().getManager().getId());//设置直属领导的ID
//			entity.getUserInfo().getManager().setId(id);//设置直属领导的ID
		}
//		System.out.println(entity);
//		System.out.println(entity);
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);//删除一个对象
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(User.class, id);
		}
	}

}
