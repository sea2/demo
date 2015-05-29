package com.smt.imageload.util;


public class FileManager {

	public static String getSaveFilePath() {
		if (CommonUtil.hasSDCard()) {
			return CommonUtil.getRootFilePath() + "cha4.net/files/";
		} else {
			return CommonUtil.getRootFilePath() + "cha4.net/files";
		}
	}
}
