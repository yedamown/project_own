package co.prjt.own.band.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BandCalendarDetailVO {
	public String bandCalendarNo;
	public String bandMemberNo; //닉네임을 받음
	public String bandAttend; 
	public String bandUserId; //유저아이디
}
