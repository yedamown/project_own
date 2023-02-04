package co.prjt.own.sns.service;

import lombok.Data;

@Data
public class SFollowVO {
	String followNo;
	String snsFollowId;
	String snsFollowerId;
	int count;
	String mediaServerFile;
}
