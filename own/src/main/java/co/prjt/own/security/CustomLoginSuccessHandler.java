package co.prjt.own.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import groovy.util.logging.Log4j;

@Log4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	OwnhomeMapper ownService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("login success");
		String id = auth.getName();
		

		HttpSession session = request.getSession();
		List<String> roleNames = new ArrayList<>();
		
		auth.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});
		if(roleNames.contains("ROLE_ADMIN")) {
			session.setAttribute("loginUser", ownService.login(auth.getName()));
			response.sendRedirect("/");
			return ;
		}
		else if(roleNames.contains("ROLE_USER")) {
			session.setAttribute("loginUser", ownService.login(auth.getName()));
			response.sendRedirect("/");
			return ;
		}
		else {
			session.setAttribute("loginUser", ownService.login(auth.getName()));
			response.sendRedirect("/");
			return ;
		}		
	
	}
	
}
