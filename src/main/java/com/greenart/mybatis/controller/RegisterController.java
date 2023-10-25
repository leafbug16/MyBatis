package com.greenart.mybatis.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenart.mybatis.dao.UserDAO;
import com.greenart.mybatis.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	UserDAO userDao;
	
	@GetMapping("form") 
	public String form() {
		return "registerForm"; 
	}
	
	@PostMapping("action")
	public String action(User user, Model m) throws UnsupportedEncodingException {
		if(!isValid(user)) {
			String msg = URLEncoder.encode("¹º°¡ Àß¸øµÆ½À´Ï´Ù", "UTF-8");
			m.addAttribute("msg", msg);
			return "registerForm";
		}
		return "redirect:/login/form";
	}
	
	private boolean isValid(User user) {
		int res = userDao.insertUser(user);
		if(res == -1) {
			return false;
		}
		return true;
	}
}
