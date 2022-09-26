package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		Trang trong antMatcher yêu cầu phải login với vai trò ADMIN, USER
		http.csrf().disable().authorizeRequests()
//				antMatchers khai báo đường dẩn của request 
				.antMatchers("/category/**").hasRole("ADMIN")
//				permitAll cho phep tất cả các user truy cập
				.antMatchers("/product/{id}","/comment/get","/product/findCategory/{catId}","/product/search/{key}").permitAll()
//				hasRole(ADMIN, USER): chỉ cho phép các user có GrantedAuthority là ROLE_ADMIN, USER mới được phép truy cập
				.antMatchers("/login/**","/order/create","/orderdetail/add").hasAnyRole("USER", "ADMIN")
//				cho phép người dùng xác thực bằng formLogin
//				loginPage(): đường dẫn tới trang chứa form đăng nhập
				.anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/admin/list")
//				usernameParameter() và passwordParameter(): gía trị của thuộc tính name của 2 input nhập username và password
				.usernameParameter("username").passwordParameter("password")
//				defaultSuccessUrl(): đường dẫn tới trang đăng nhập thành công
//				failureUrl(): đường dẫn tới trang đăng nhập thất bại
				.defaultSuccessUrl("/customer/list").failureUrl("/login?error=failed")
//				Khi người dùng không đủ quyền để truy cập vào một trang, ta sẽ redirect người dùng về một trang 403
				.and().exceptionHandling().accessDeniedPage("/403").and()
				.httpBasic();
	}

	/**mã hóa mật khẩu sẽ do interface PasswordEncoder đảm nhận.
	 *  PasswordEncoder có implementation là BCryptPasswordEncoder 
	 *  sẽ giúp chúng ta mã hóa mật khẩu bằng thuật toán BCrypt. 
	 *  Nhưng để sử dụng được PasswordEncoder phải cấu hình để PasswordEncoder trở thành một Bean.
	 * @return
	 */	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl r = new RoleHierarchyImpl();
		r.setHierarchy("ROLE_ADMIN >  ROLE_USER");
		return r;
	}

	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy());
		return expressionHandler;
	}


		
}
