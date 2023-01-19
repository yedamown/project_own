package co.prjt.own.sns.service;

import lombok.Data;

@Data
public class SAccountVO {
	String snsNickname;
	String snsProfile;
	String snsWebsite;
	String snsAccountNo;
	String userId;
	String follower;
	String follow;
}
