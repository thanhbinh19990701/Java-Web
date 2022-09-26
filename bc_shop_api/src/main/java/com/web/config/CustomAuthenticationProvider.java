package com.web.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.entities.User;
import com.web.repository.IUserRepository;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private IUserRepository userRepository;
	@Autowired 
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userRepository.findByUsername(username);
		if (user != null) {
			String comparePassword = user.getPassword();
//			Xác minh mật khẩu được mã hóa thu được từ bộ nhớ khớp với mật khẩu thô đã gửi sau khi mật khẩu cũng được mã hóa. 
//			Trả về true nếu mật khẩu khớp, false nếu chúng không khớp.
			if (passwordEncoder.matches(password, comparePassword)) {
				return new UsernamePasswordAuthenticationToken(username, password,
						Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
