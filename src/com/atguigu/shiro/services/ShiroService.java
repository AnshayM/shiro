package com.atguigu.shiro.services;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

public class ShiroService {

	@RequiresRoles({ "admin" })
	public void testMethod() {
		System.out.println("testMethod, time: " + new Date());
		// 不依靠底层驱动就可以获取到session，即在开发时在service就可以访问到session
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");

		System.out.println("Service SessionVal: " + val);
	}

}
