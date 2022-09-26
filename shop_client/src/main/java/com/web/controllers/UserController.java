package com.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.models.ResponseObject;
import com.web.models.UserAcount;
import com.web.services.AuthService;


@Controller
public class UserController {
	@Autowired
	AuthService authService;

	/**
	 * check xem đã có đăng nhập chưa nếu có rồi thì redirect đến home k thì về trang đăng nhập
	 * @param request
	 * @return
	 */
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		// lấy cookie và check xem nó tồn tại không nếu không thì trả về chuỗi rỗng
		String cookie = authService.checkCookie("API_JSESSIONID", request.getCookies());
		if (cookie != "") {
			// nếu đã đăng nhập rồi thì set cookie và authentication và xác thực thông tin của user bên api
			if (authService.setCookie(cookie)) {
				//nếu đúng rồi thì nó rediredct qua trang chủ
				String referer = request.getHeader("Referer");
				return referer != null ? "redirect:" + referer : "redirect:/";
			}
		}
		// về trang đăng nhập
		return "web-client/login";
	}


	/**
	 * Đăng nhập
	 * @param account
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public String login(@ModelAttribute UserAcount account, Model model, HttpServletRequest request,HttpSession session) {
		model.addAttribute("status", "Login");
		
		String cookie = authService.checkCookie("API_JSESSIONID", request.getCookies());
		if (cookie == "") {
			ResponseObject<UserAcount> responseObject = authService.login(account);

			if ("OK".equals(responseObject.getStatus())) {
				authService.setCookie(responseObject.getData().getCookie());
				session.setAttribute("account", responseObject.getData());
				if (responseObject.getData().getRole().contains("ROLE_USER")) {
					return "redirect:/home";
				}
				if (responseObject.getData().getRole().contains("ROLE_ADMIN")) {
					return "redirect:/admin";
				}

			} else if ("FALIED".equals(responseObject.getStatus())) {
				model.addAttribute("message", "ERROR: " + responseObject.getMessage());
			} else {
				model.addAttribute("message", "ERROR: Sai mat khau");
				return "redirect:/login"; 
				
			}
		}
		return "redirect:/home";

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/home";
	}
	
}
	