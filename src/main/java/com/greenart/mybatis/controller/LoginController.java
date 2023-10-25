package com.greenart.mybatis.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenart.mybatis.dao.UserDAO;
import com.greenart.mybatis.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserDAO userDao;
	
	@GetMapping("/form")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/action")
	public String loginAction(String toURL, String id, String password, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(!loginCheck(id, password)) {
			String msg = URLEncoder.encode("아이디 또는 비밀번호가 일치하지 않습니다", "utf-8");
			return "redirect:/login/form?msg="+msg+"&toURL="+toURL;
		}
		
		if (rememberId) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60*60*24);
			/* cookie.setPath("/firstSpring"); */
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("id", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		if (toURL==null || "".equals(toURL)) {
			toURL="/";
		}
		return "redirect:"+toURL;
	}
	private boolean loginCheck(String id, String password) {
		User user = userDao.selectUser(id);
		if(user == null) return false;
		return user.getId().equals(id) && user.getPwd().equals(password);
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
}
