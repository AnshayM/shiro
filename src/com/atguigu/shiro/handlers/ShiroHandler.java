package com.atguigu.shiro.handlers;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.shiro.services.ShiroService;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	@Autowired
	private ShiroService shiroService;

	@RequestMapping("/login")
	public String login(String username, String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			// 把用户名和密码封装为UsernamePasswordToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// 设置记住密码
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

	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value12345");
		shiroService.testMethod();
		return "redirect:/list.jsp";
	}

}
