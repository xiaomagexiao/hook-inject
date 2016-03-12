package com.example.hookdemo.model;

public class HKOperateData {
	public String team;
	public String hook;
	public String stack;
	private static String split = ";";

	HKOperateData() {

	}

	public HKOperateData(String team, String hook, String stack) {
		this.team = team;
		this.hook = hook;
		this.stack = stack;
	}

	public static HKOperateData makeModelWithInfo(String str) {
		String[] arr = str.split(split);
		HKOperateData data = new HKOperateData();
		data.team = arr[0];
		data.hook = arr[1];
		data.stack = arr[2];
		return data;
	}

	public String makeInfoWithModel() {
		return team + split + hook + split + stack;
	}
}
