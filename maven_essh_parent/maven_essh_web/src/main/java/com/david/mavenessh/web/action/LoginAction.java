package com.david.mavenessh.web.action;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.david.mavenessh.domain.User;
import com.david.mavenessh.utils.SysConstant;
import com.david.mavenessh.utils.UtilFuns;
import com.david.mavenessh.web.action.base.BaseAction;

/**
 * 登陆和退出的action
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	// SSH传统登录方式  cgx 123456
	public String login() throws Exception {

		// if(true){
		// String msg = "登录错误，请重新填写用户名密码!";
		// this.addActionError(msg);
		// throw new Exception(msg);
		// }
		// User user = new User(username, password);
		// User login = userService.login(user);
		// if (login != null) {
		// ActionContext.getContext().getValueStack().push(user);
		// session.put(SysConstant.CURRENT_USER_INFO, login); //记录session
		// return SUCCESS;
		// }
		// return "login";

		/**
		 * 这里使用shiro的方式进行登陆控制。
		 */
		if (UtilFuns.isEmpty(username)) {
			return LOGIN;
		}

		try {
			// 1,得到Subject
			Subject subject = SecurityUtils.getSubject();
			// 调用登陆的方法
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			System.out.println(username+password);
			subject.login(token);// 当这一行代码执行时，就会自动跳入到AuthRealm中的认证方法
			// 登陆成功时，就从Shiro中取出用户的登录信息
			User user = (User) subject.getPrincipal();
			// 将用户放入到session域中
			session.put(SysConstant.CURRENT_USER_INFO, user);
		} catch (Exception e) {
			request.put("errorInfo", "对不起，用户名或密码错误");
			return LOGIN;
		}
		return SUCCESS;
	}

	// 退出
	public String logout() {
		session.remove(SysConstant.CURRENT_USER_INFO); // 删除session

		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
