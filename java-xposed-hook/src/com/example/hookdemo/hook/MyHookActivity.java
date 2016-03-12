package com.example.hookdemo.hook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MyHookActivity extends MyHookCommon {
	private static MyHookActivity instance;

	public static MyHookActivity getInstance() {
		if (instance == null) {
			instance = new MyHookActivity();
		}
		return instance;
	}

	public void hookActivity(LoadPackageParam lpparam, boolean print) {
		printStack = print;
		XC_MethodHook mHookActivity = new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				String info = "mHookActivity->>>>";
				Intent intent = (Intent) param.args[0];
				Log.e(TAG, "[!] " + info + "跳转到：" + intent.getComponent().getClassName());
				printParams(param, info);
				printStack(info);
				super.beforeHookedMethod(param);
			}
		};
		XposedHelpers.findAndHookMethod("android.app.Activity", lpparam.classLoader, "startActivityForResult", Intent.class,
				int.class, Bundle.class, mHookActivity);
	}
}
