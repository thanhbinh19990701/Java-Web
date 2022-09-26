package com.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.ResponseObject;
import com.web.entities.User;
import com.web.repository.IUserRepository;
import com.web.services.IUserService;


@RestController
public class UserController {
	
	@Autowired
	IUserRepository userRepository;
	@Autowired
	IUserService iUserService;
	
	@PostMapping(value = "/login")
    public ResponseEntity<ResponseObject> getUser(final HttpServletRequest request){
//		Lớp xác thực của Springsecurity
//		getAuthorities(): trả về danh sách các quyền của người dùng
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Nếu người dùng chưa đăng nhập thì so sánh với getName
		if (!"anonymousUser".equalsIgnoreCase(authentication.getName())) {
//			SecurityContext lưu trữ tất cả các chi tiết liên quan đến bảo mật trong ứng dụng.
//			Xác ddingj thông tin bảo mật được liên kết trong quá trình thực thi
			SecurityContext context = SecurityContextHolder.createEmptyContext();
//			Set Authen vào context
			context.setAuthentication(authentication);
//			Lấy session (Phiên làm việc) hiện tại, nếu k  tồn tại sẽ tạo mới
			HttpSession session = request.getSession(true);
//			Set thuộc tính sesion với thuộc tính "SPRING_SECURITY_CONTEXT"  
			session.setAttribute("SPRING_SECURITY_CONTEXT", context);
//			Tìm thông tin username trong lớp User 
			User optional = userRepository.findByUsername(authentication.getName());
//			Lấy session Id set vào cookie
			optional.setCookie(session.getId());
//			getAuthorities lấy danh sách quyền của người dùng
			authentication.getAuthorities().forEach(author -> {
				optional.getRole();
			});
//			Trả về success nếu thành công, ngược lại trả về not success
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "sucess", optional));
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject("Failed", "not sucess",""));
    }


}
