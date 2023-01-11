package co.prjt.own.common.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaVO {
	String mediaNo;
	String mediaRealFile;
	String mediaServerFile;
	String mediaFilePath;
	String identifyId;
	String mediaCategory;
	String mediaTurn;
	String ino;
}
