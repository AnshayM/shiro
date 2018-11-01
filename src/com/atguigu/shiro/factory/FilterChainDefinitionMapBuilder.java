package com.atguigu.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		/* 配置哪些页面需要受保护. 以及访问这些页面需要的权限. 
		 * 在实际开发中，可以通过数据库来获取
		 * 1). anon 可以被匿名访问 
		 * 2). authc 必须认证(即登录)后才可能访问的页面.
		 * 3). logout 登出. 
		 * 4). roles 角色过滤器
		 */
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp", "user");
		
		map.put("/**", "authc");
		
		return map;
	}
	
}
