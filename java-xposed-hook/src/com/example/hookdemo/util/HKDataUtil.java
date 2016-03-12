package com.example.hookdemo.util;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.example.hookdemo.model.HKOperateData;

public class HKDataUtil {
	public static List<HKOperateData> gHookInfo = new ArrayList<HKOperateData>();

	public static String filename = "/sdcard/1.txt";

	/**
	 * 取配置信息
	 * 
	 * @return
	 */
	public static void getHookInfo() {
		String info = FileUtil.readFromFile(filename);
		System.out.println("[!]" + info);
		if (TextUtils.isEmpty(info)) {
			gHookInfo.add(new HKOperateData("Activity", "1", "1"));
			gHookInfo.add(new HKOperateData("WebView", "1", "1"));
		} else {
			gHookInfo.clear();
			gHookInfo = convertStrToData(info);
		}
	}

	/**
	 * 保存配置信息
	 * 
	 * @param dataList
	 */
	public static void saveHookInfo() {
		String info = "";
		for (HKOperateData data : gHookInfo) {
			info += data.makeInfoWithModel();
			info += "---";
		}
		info = info.substring(0, info.lastIndexOf("---"));
		FileUtil.writeStrToFile(info, filename);
		System.out.println("[!]" + info);
	}

	private static List<HKOperateData> convertStrToData(String info) {
		String[] first = info.split("---");
		List<HKOperateData> data = new ArrayList<HKOperateData>();
		for (String str : first) {
			data.add(HKOperateData.makeModelWithInfo(str));
		}
		return data;
	}

}
