package com.example.hookdemo.hook;

import android.util.Log;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;

public class MyHookCommon {
	protected static final String TAG = "HookUtil";
	protected boolean printStack = true;

	protected XC_MethodHook commonHook(String tag) {
		final String info = tag + "->>>>";
		return new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				printParams(param, info);
				printStack(info);
				super.beforeHookedMethod(param);
			}
		};
	}

	protected void printStack(String type) {
		if (printStack) {
			Log.e(TAG, "[!] " + type + Log.getStackTraceString(new Throwable()));
		}
	}

	protected void printParams(MethodHookParam param, String type) {

		Log.e(TAG, "[!] " + type + " 参数数量 - " + param.args.length);
		for (int i = 0; i < param.args.length; i++) {
			Log.e(TAG, "[!] " + (param.args[i] == null ? "-null-" : param.args[i]));
		}
	}

}
