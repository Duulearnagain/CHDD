package poly.store;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import poly.store.entity.Account;
import poly.store.service.AccountService;



@Configuration
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService accountService;// nguon dl
	@Autowired
	BCryptPasswordEncoder pe;// ma hoa dung cho Anthen
	//cung cap nguon dl dang nhap, Authen can dl nen phai co account
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//dua nguon dl vao
		auth.userDetailsService(username -> {//dua username vao phuong thuc detail
			try {
			Account user = accountService.findById(username);
			String password=pe.encode(user.getPassword());
			String [] roles= user.getAuthorities().stream()//lay vai tro ng dung de dua vao usedetail
					.map(er -> er.getRole().getId())
					.collect(Collectors.toList()).toArray(new String [0]);
			return User.withUsername(username).password(password).roles(roles).build();//tra ve usedetail
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username +"not found");//neu use k ton tai thi nem ngoai le ra ngoai
			}
		});
		
	}
	
	//Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()//moi url thi chi ra 1 chinh sach bao mat
			.antMatchers("/order/**").authenticated()//bắt buộc đăng nhập
			.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
			.antMatchers("/rest/authorities").hasRole("STAF")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")//dia chi form
			.loginProcessingUrl("/security/login")//đăng nhập xong chuyển địa chỉ về form submit toi
			.defaultSuccessUrl("/security/login/success",false)//dang nhap thanh cong thi chuyen qua dia chi xu ly tc
			.failureUrl("/security/login/error");//dang nhap sai thi dua ra thong bao
		
		http.rememberMe()//cau hinh remember
			.tokenValiditySeconds(86400);
		
		http.exceptionHandling()//dia chi k co quyen truy cap
		.accessDeniedPage("/security/unauthoried");
		
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/success");
	}
	
	// mã hóa mật khẩu
	@Bean 
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

