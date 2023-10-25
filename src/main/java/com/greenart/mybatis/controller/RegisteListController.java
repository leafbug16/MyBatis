package com.greenart.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenart.mybatis.dao.UserDAO;
import com.greenart.mybatis.model.User;

@Controller
public class RegisteListController {
	@Autowired
	UserDAO userDao;
	
	@RequestMapping("/registeList")
	public String showUserList(Model m) {
		List<User> users = new ArrayList<>();
		users = userDao.selectUserAll();
		m.addAttribute(users);
		System.out.println(m);
		return "registeList";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest req) {
		String id = req.getParameter("id");
		userDao.deleteUser(id);
		return "redirect:/registeList";
	}
}
