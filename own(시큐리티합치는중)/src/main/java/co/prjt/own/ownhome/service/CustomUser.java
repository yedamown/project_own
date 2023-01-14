package co.prjt.own.ownhome.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User  {
	private static final long serialVersionUID = 1L;
	private OwnUserVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public CustomUser(OwnUserVO vo) {
		super(vo.getUserId(),vo.getUserPasswd(),Arrays.asList(new SimpleGrantedAuthority(vo.getUserPerm())));
	}
}
