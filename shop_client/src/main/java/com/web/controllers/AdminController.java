package com.web.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.web.models.Order;
import com.web.models.Orderdetail;
import com.web.models.Product;
import com.web.models.UserAcount;
import com.web.services.IOderService;
import com.web.services.IOrderdetailSerivce;
import com.web.services.IProductService;
import com.web.services.iAccountService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IOderService iOderService;
	@Autowired
	IOrderdetailSerivce iOrderdetailSerivce;
	@Autowired
	iAccountService iAccountService;
	@Autowired
	IProductService iProductService;
	
	
	/**
	 * đi đến trang admin hoặc về trang đăn nhập
	 * @param session
	 * @param model
	 * @return web-admin/index or redirect:/login
	 */
	@GetMapping
	public String homeAdmin(HttpSession session, Model model) {
		// lấy thông tin account ở session
		UserAcount account = (UserAcount) session.getAttribute("account");
		// lấy quyền account
		String role = account.getRole();
		//check thông tin account và quền để cho vào trang admin hay về trang login
		if(account != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			
			List<Order> listOrder = iOderService.getAllOrder(account.getCookie()).getData();
			
			model.addAttribute("listOrder", listOrder);
			return "web-admin/index";
		}else
			return "redirect:/login";
	}
	
	
	@GetMapping("/chart")
	public String chartAdmin() {
		return "web-admin/chart";
	}
	
	/**
	 * Lấy chi tiết đơn hàng
	 * @param session
	 * @param model
	 * @param ordId
	 * @param id
	 * @return web-admin/orderdetail or redirect:/login
	 */
	@GetMapping("/detail/{ordId}/{id}")
	public String getOrderDetail(HttpSession session, Model model,@PathVariable("ordId") Integer ordId, @PathVariable("id") Integer id) {
		UserAcount account = (UserAcount) session.getAttribute("account");
		String role = account.getRole();
		if(account != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			//lấy list  chi tiết đơn hàng qua id đơn và cookie đã đăng nhập
			List<Orderdetail> listOrderdetail = iOrderdetailSerivce.getOrderdetail(ordId, account.getCookie()).getData();
			//lấy thông tin của user đã đặt hàng và gủi về trang orderdetail
			UserAcount user = iAccountService.findUserByID(id, account.getCookie()).getData();
			model.addAttribute("listOrderdetail", listOrderdetail);
			model.addAttribute("user", user);
			return "web-admin/orderdetail";
		}else
			return "redirect:/login";
	}
	
	/**
	 * Cập nhật trạng thái của đơn đặt hàng
	 * @param session
	 * @param orders
	 * @param ordId
	 * @return
	 */
	@PostMapping("/{ordId}")
	public String updateOrder(HttpSession session,@ModelAttribute Order orders,@PathVariable("ordId") Integer ordId) {
		UserAcount account = (UserAcount) session.getAttribute("account");
		String role = account.getRole();
		if(account != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			Date date = new Timestamp(new Date().getTime());
			orders.setOrdCreateAt(date);
			orders.setOrdUpdateAt(date);
			iOderService.update(orders, ordId, account.getCookie());
			return "redirect:/admin";
		}else
			return "redirect:/login";
	}
	
	/**
	 * xóa đơn đặt hàng
	 * @param ordId
	 * @param session
	 * @return
	 */
	@GetMapping("delete/{ordId}")
	public String deleteOrder(@PathVariable Integer ordId, HttpSession session) {
		UserAcount account = (UserAcount) session.getAttribute("account");
		String role = account.getRole();
		if(account != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			iOderService.deleteOrder(ordId, account.getCookie());
			return "redirect:/admin";
		}else {
			return "redirect:/login";
		}
	}
	
	/**
	 * Lấy tất cả sản phẩm và gửi thông tin về cho trang product ở admin
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/product")
	public String getAllProduct(HttpSession session,Model model) {
		UserAcount account = (UserAcount) session.getAttribute("account");
		String role = account.getRole();
		if(account != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			List<Product> listProducts = iProductService.getAllProduct().getData();
			model.addAttribute("listProduct", listProducts);
			return "web-admin/product";
		}else {
			return "redirect:/login";
		}
	}
	
	/**
	 * xóa sản phẩm
	 * @param proId
	 * @param session
	 * @return
	 */
	@GetMapping("deleteProduct/{proId}")
	public String deleteProduct(@PathVariable("proId") Integer proId, HttpSession session) {
		UserAcount account = (UserAcount) session.getAttribute("account");
		String role = account.getRole();
		if(account != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			iProductService.deleteProduct(proId, account.getCookie());
			return "redirect:/admin/product";
		}else {
			return "redirect:/login";
		}
	}
}
