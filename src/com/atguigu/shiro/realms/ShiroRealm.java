package com.atguigu.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[FirstRealm] doGetAuthenticationInfo");
		// 1.把AuthenticationToken转化为UsernamePassWordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2.从UsernamePasswordToken中获取userName
		String username = upToken.getUsername();

		// 3.调用数据库方法，从数据库中根据userName查询用户信息(这里目前只做显示)
		System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");

		// 4.若用户不存在，则抛出UnknownAccountException异常
		if ("unknow".equals(username)) {
			throw new UnknownAccountException("用户不存在");
		}

		// 5.根据用户信息，决定是否需要抛出其他AuthenticationException异常
		if ("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		// 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		// 以下信息是从数据库中获取的.
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = username;
		// 2). credentials: 密码.
		Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
		// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);

		return info;
	}

	/**
	 * 测试加密1024次后结果： 不加盐--fc1709d0a95a6be30bc5926fdb7f22f4
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = null;
		int hashIterations = 1024;

		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
