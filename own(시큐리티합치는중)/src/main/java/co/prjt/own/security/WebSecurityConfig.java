package co.prjt.own.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	@Autowired
	CustomLoginSuccessHandler custom;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
//				.antMatchers("/","/own/home","/css/**","/login","/own/sendmail").permitAll() // 홈은 모든사람 접근가능
//				.antMatchers("/admin").hasRole("ADMIN") // 특정권한만 가진사람만
//				.anyRequest().authenticated() //이외에는 허가된사람만..
				.anyRequest().permitAll()
			);
		
		http.formLogin()
			.loginPage("/own/login")
			.loginProcessingUrl("/login")
			.successHandler(new AuthenticationSuccessHandler() {
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
						custom.onAuthenticationSuccess(request, response, authentication);
				}
			
			})
			.failureHandler(new AuthenticationFailureHandler() {

				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
						System.out.println("=================실패");
				}
			});

//			.formLogin((form) -> form //<securit:form-loging>
//				.loginPage("/login")
//				.passwordParameter("pw")
//				.usernameParameter("userid")
//				.loginProcessingUrl("/signin")
//				.permitAll()//모든사람이 /loginpage에 접근가능하도록 허용..
//			)
		
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/own/logout"))
	        .logoutSuccessUrl("/")
	        .invalidateHttpSession(true)
			
			//.passwordManagement();
			;
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.builder()
//				.username("user")
//				.password("{bcrypt}$2a$10$Jupwp8C2uG6whSCDmwI9i.ZQ2d9kqAAV58SFoYqFhqz14SfmQbcsW")//암호화 안된거
//				.roles("USER")
//				.build();
//		
//		UserDetails admin =
//				 User.withDefaultPasswordEncoder()
//					.username("admin")
//					.password("{bcrypt}$2a$10$Jupwp8C2uG6whSCDmwI9i.ZQ2d9kqAAV58SFoYqFhqz14SfmQbcsW")
//					.roles("ADMIN")
//					.build();
//		return new InMemoryUserDetailsManager(user,admin);
//		return ownhomeService();
//	}
}