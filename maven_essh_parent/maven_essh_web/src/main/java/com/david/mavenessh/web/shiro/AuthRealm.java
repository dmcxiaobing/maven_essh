package com.david.mavenessh.web.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.david.mavenessh.domain.Module;
import com.david.mavenessh.domain.Role;
import com.david.mavenessh.domain.User;
import com.david.mavenessh.service.UserService;

/**
 * shiro自定义Realm，认证以及授权
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class AuthRealm extends AuthorizingRealm {

	/**
	 * 用户业务层
	 */
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 认证 token，代表用户在界面输入的用户名和密码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		System.out.println("认证");
		// 向下转型
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
		// 调用业务的方法，进行查询，用户名是否存在
		String hql = "from User where userName=?";
		List<User> users = userService.find(hql, User.class, new String[] { upToken.getUsername() });
		if (users != null && users.size() > 0) {
			User dbUser = users.get(0);
			AuthenticationInfo info = new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), this.getName());
			return info;// 此处如果返回，则就会立即进入到密码比较器
		}
		return null;// 出现异常如果返回null
	}

	/**
	 * 授权，当jsp页面出现shiro标签时，就会执行授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("授权");
		User user = (User) pc.fromRealm(this.getName()).iterator().next();// 根据realm的名字去找对应的realm
		Set<Role> roles = user.getRoles();
		// 对象导航
		List<String> permissions = new ArrayList<String>();
		for (Role role : roles) {
			// 遍历每个角色 ，得到每个角色下的模块列表
			Set<Module> modules = role.getModules();
			for (Module module : modules) {
				permissions.add(module.getName());
			}
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);// 添加用户的模块的权限。可以查看用户模块的内容

		return info;
	}

}
