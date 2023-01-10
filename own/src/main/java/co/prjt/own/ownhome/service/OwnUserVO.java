package co.prjt.own.ownhome.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OwnUserVO {
	public String userId;
	public String userPasswd;
	public String userName;
	public String userEmail;
	public String userPhone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date userBirthday;
	public String userGender;
	public String userPerm;
	public String userStatus;
	public String userHobby;
	public String snsAccountNo;
	public String snsNickname;
}
