package com.david.mavenessh.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.david.mavenessh.utils.Encrypt;

/**
 * shiro密码比较器
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	/**
	 * 密码比较的方法，token代表用户在界面输入的用户名和密码，info代表从数据库中得到的加密数据
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// 向下转型
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 将用户在界面输入的原始密码加密.由于返回的是字节数组，所以这里转换为String用new String
		// 根据用户名做盐进行加密
		Object EncryptFormPassword = Encrypt.md5(new String(upToken.getPassword()), upToken.getUsername());
		// 取出数据库中加密的密码
		Object dbPassword = info.getPrincipals();
		// 用户输入的密码和数据库中的进行对比
		return this.equals(EncryptFormPassword, dbPassword);
	}

}
