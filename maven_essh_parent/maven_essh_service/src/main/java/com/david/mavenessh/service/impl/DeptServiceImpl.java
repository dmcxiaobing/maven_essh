package com.david.mavenessh.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.david.mavenessh.dao.BaseDao;
import com.david.mavenessh.domain.Dept;
import com.david.mavenessh.service.DeptService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.utils.UtilFuns;

/**
 * 部门管理的业务类
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DeptServiceImpl implements DeptService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Dept get(Class<Dept> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			entity.setState(1);// 1启用，0停用，默认启用
		}
		
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Dept> entitys) {
		baseDao.saveOrUpdate(entitys);

	}
	/**
	 * 如果删除父部门，则会同时删除下面的子部门
	 */
	@Override
	public void deleteById(Class<Dept> entityClass, Serializable id) {
		// 先先查询出有哪些子部门
		String hql = "from Dept where parent.id=?";
		// 查询出当前父部门下的子部门列表
		List<Dept> list = baseDao.find(hql, Dept.class, new Object[]{id});
		if (list != null && list.size() > 0) {
			for (Dept dept : list) {
				// 递归调用，删除子部门
//				System.out.println("这里走了一次："+dept.getId());
				deleteById(Dept.class, dept.getId());
			}
		}
		// 如果没有字部门了，则删除父部门
		//System.out.println(id);
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Dept.class, id);
		}
	}

}
