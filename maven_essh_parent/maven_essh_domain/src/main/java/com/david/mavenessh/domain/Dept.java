package com.david.mavenessh.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门表
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class Dept implements Serializable{
	private String id;
	private Set<User> users = new HashSet<User>(0);//部门与用户 一对多
	
	
	private String deptName;//部门名称
	private Dept parent;//父部门 自关联。 子部门与父部门 多对一
	private Integer state;//状态1，代表启用，0代表停用
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

}
