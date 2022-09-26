package com.web.controllers;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.models.CartItem;
import com.web.models.Customer;
import com.web.models.Order;
import com.web.models.Orderdetail;
import com.web.models.Product;
import com.web.models.UserAcount;
import com.web.services.IOderService;
import com.web.services.IOrderdetailSerivce;
import com.web.services.IProductService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

	@Autowired
	IProductService iProductService;
	@Autowired
	IOderService iOderService;
	@Autowired
	IOrderdetailSerivce iOrderdetailSerivce;
	@GetMapping()
	public String listCart() {
		return "web-client/view-cart";
	}
	/**
	 * đặt hàng. thêm các sản phẩm đã có trong giỏ hàng vào đơn đặt hàng
	 * @param mm
	 * @param session
	 * @return
	 */
	@GetMapping("/checkout")
	public String checkOut(ModelMap mm, HttpSession session) {
		//lấy thông tin của sản phẩm trong giở hàng được lưu ở session
		HashMap<Integer, CartItem> cartItems = (HashMap<Integer, CartItem>) session.getAttribute("myCartItems");
		//lấy ngày hiện tại
		Date date = new Timestamp(new Date().getTime());
		//tạo đối tượng order
		Order oder = new Order();
		//lấy thông tin đăng nhập của khách hàng
		UserAcount acc=  (UserAcount) session.getAttribute("account");
		//nếu khách hàng chưa đăng nhập thì quay về trang login
		if(acc==null) {
			return "redirect:/login";
		}else {
			// set các thông tin của đơn hàng vào oder
			oder.setcusId(acc.getId());
			oder.setOrdStastus(1);
			oder.setOrdDeleted((byte)0);
			oder.setOrdCreateAt(date);
			oder.setOrdUpdateAt(date);
			// thêm oder vào cơ sở dữ liệu
			iOderService.createOrder(oder,acc.getCookie());
			// lấy lại thông tin của order mới thêm vào.
			oder = iOderService.getNewOrder(acc.getCookie()).getData();
			//chạy hết tất cả các sản phẩm có trong cart item
			for (Map.Entry<Integer, CartItem> list : cartItems.entrySet()) {
				//tạo đối tượng orderdetail 
				Orderdetail orderdetail = new Orderdetail();
				// set thông tin cho orderdetail
				orderdetail.setOrder(oder);
				orderdetail.setProduct(list.getValue().getProduct());
				orderdetail.setOdtQuatity(list.getValue().getQuatity());
				orderdetail.setOdtPrice(list.getValue().getProduct().getProPrice());
				orderdetail.setOdtSize(list.getValue().getSize());
				orderdetail.setOdtTotal(list.getValue().getProduct().getProPrice() * list.getValue().getQuatity());
				orderdetail.setOdtDeleted((byte)0);
				orderdetail.setOdtCreateAt(date);
				orderdetail.setOdtUpdateAt(date);
				//tạo order detail
				iOrderdetailSerivce.createOrderdetail(orderdetail,acc.getCookie());
				
			}
			// xóa hết sản phẩm xong mycartItems
			session.removeAttribute("myCartItems");
			session.removeAttribute("myCartTotal");
			session.removeAttribute("myCartNum");
			//quay về giỏ hàng
			return "redirect:/cart";
		} 
	}
	/**
	 * Thêm sản phẩm vào giỏ hàng
	 * @param mm
	 * @param session
	 * @param proId
	 * @param newCartitem
	 * @return
	 */
	@PostMapping("/add/{proId}")
    public String viewAdd( HttpSession session, @PathVariable("proId") Integer proId, @ModelAttribute CartItem newCartitem) {
        // lấy các sản phẩm có trong session mycartItem
		HashMap<Integer, CartItem> cartItems = (HashMap<Integer, CartItem>) session.getAttribute("myCartItems");
		//nếu chưa có sản phẩm nào thì tạo 1 hashmap mới
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        // lấy thông tin của sản phẩm cần thêm vào giỏ hàng theo Id
        Product product = iProductService.getProductById(proId).getData();
        //check sản phẩm có không
        if (product != null) {
        	//kiểm tra sản phẩm đã có trong giỏ hàng chưa nếu có thì thêm cộng thêm số lượng vào sản phẩm đó
            if (cartItems.containsKey(proId)) {
            	CartItem item = cartItems.get(proId);
                item.setProduct(product);
                item.setQuatity(item.getQuatity() + newCartitem.getQuatity());
                item.setSize(newCartitem.getSize());
                cartItems.put(proId, item);
            } else {
            	//nếu không thì thêm thông tin sản phẩm vào giỏ hàng
            	newCartitem.setProduct(product);
                cartItems.put(proId, newCartitem); 
                
            }
        }
        // lưu lại cartItem vào session
        session.setAttribute("myCartItems", cartItems);
        // lưu tổng giá
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        // lưu số lượng sản phẩm
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/cart";
    }
	/**
	 * Cập nhật thông tin của giỏ hàng
	 * @param mm
	 * @param session
	 * @param proId
	 * @param item
	 * @return
	 */
	@PostMapping("/update/{proId}")
    public String viewUpdate(ModelMap mm, HttpSession session, @PathVariable("proId") Integer proId , @ModelAttribute CartItem item) {
		// lấy các sản phẩm có trong session mycartItem
		HashMap<Integer, CartItem> cartItems = (HashMap<Integer, CartItem>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }else {
        	//  set size và quatity 
        	cartItems.get(proId).setQuatity(item.getQuatity());
        	cartItems.get(proId).setSize(item.getSize());
        }
        // lưu lại vào session
        session.setAttribute("myCartItems", cartItems);
        return "redirect:/cart";
    }
	@GetMapping("remove/{proId}")
    public String viewRemove(ModelMap mm, HttpSession session, @PathVariable("proId") Integer proId) {
		// lấy các sản phẩm có trong session mycartItem
		HashMap<Integer, CartItem> cartItems = (HashMap<Integer, CartItem>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(proId)) {
        	//xóa sản sản phẩm trong giỏ hàng theo key là id sản phẩm
            cartItems.remove(proId);
        }
        //lưu lại giỏ hàng mới cập nhật
        session.setAttribute("myCartItems", cartItems);
        // lưu lại giá mới
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        // lưu lại số lượng sản phẩm
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/cart";
    }
	/**
	 * tỉnh tổng giá của các sản phẩm trong gio
	 * @param cartItems
	 * @return
	 */
	public double totalPrice(HashMap<Integer, CartItem> cartItems) {
        int count = 0;
        //duyệt qua các sản phẩm trong giỏ hàng
        for (Map.Entry<Integer, CartItem> list : cartItems.entrySet()) {
        	// cộng lại giá sản phẩm
            count += list.getValue().getProduct().getProPrice() * list.getValue().getQuatity();
        }
        return count;
    }
	
}
