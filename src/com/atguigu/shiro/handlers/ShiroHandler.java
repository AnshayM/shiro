package com.atguigu.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

	@RequestMapping("/login")
	public String login(String username, String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			// 把用户名和密码封装为UsernamePasswordToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// rememberMe
			token.setRememberMe(true);
			try {
				// 执行登陆
				currentUser.login(token);
			} catch (AuthenticationException ae) {
				System.out.println("登陆失败:" + ae.getMessage());
			}
		}
		return "redirect:/list.jsp";
	}

	/**
	 * 登出（还没有做清除缓存操作）
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {

		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			// 把用户名和密码封装为UsernamePasswordToken对象
			// UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				// 执行登陆
				currentUser.logout();
			} catch (AuthenticationException ae) {
				System.out.println("登出失败:" + ae.getMessage());
			}
		}
		return "redirect:/login.jsp";
	}

}
