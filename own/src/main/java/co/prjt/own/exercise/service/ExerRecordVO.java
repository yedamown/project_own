package co.prjt.own.exercise.service;

import java.util.Date;

import lombok.Data;

@Data
public class ExerRecordVO {
	String userId;
	Date exerecDate;
	int exerecWeight;
	String exerecCondition;
	int exerecTime;
	int exerecMeter;
	String exerecRoution;
	String exerecFeedback;
	String exerecNo;
	String exersubNo;
}
