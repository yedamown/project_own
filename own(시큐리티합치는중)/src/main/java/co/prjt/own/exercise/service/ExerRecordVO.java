package co.prjt.own.exercise.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExerRecordVO {
	String userId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date exerecDate;
	int exerecWeight;
	String exerecCondition;
	int exerecTime;
	int exerecMeter;
	String exerecRoutin;
	String exerecFeedback;
	String exerecNo;
	String exersubNo;
	String eno;
}
