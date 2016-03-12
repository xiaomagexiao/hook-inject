package com.example.hookdemo.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.hookdemo.hook.MyHookActivity;
import com.example.hookdemo.hook.MyHookWebview;
import com.example.hookdemo.model.HKOperateData;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HookUtil implements IXposedHookLoadPackage {
	protected static final String TAG = "HookUtil";
	public static String checkString = "com.youku.phone";
	private Context mContext;

	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

		Log.e(TAG, "[!]packagename = " + lpparam.packageName);
		if (!lpparam.packageName.equals(checkString))
			// return;
			Log.e(TAG, "[!] 找到指定的 packageName");
		// hookAndGetContext("android.app.Activity", lpparam, "onCreate");

		List<HKOperateData> list = new ArrayList<HKOperateData>();
		list.add(new HKOperateData("Activity", "1", "1"));
		list.add(new HKOperateData("WebView", "1", "0"));
		HKDataUtil.getHookInfo();
		for (HKOperateData data : HKDataUtil.gHookInfo) {
			hookData(lpparam, data);
		}

	}

	private void hookData(LoadPackageParam lpparam, HKOperateData data) {
		if ("Activity".equals(data.team) && "1".equals(data.hook)) {
			MyHookActivity.getInstance().hookActivity(lpparam, "1".equals(data.stack));
		} else if ("WebView".equals(data.team) && "1".equals(data.hook)) {
			MyHookWebview.getInstance().hookWebView(lpparam, "1".equals(data.stack));
		}
	}

	private void hookAndGetContext(String className, final LoadPackageParam lpparam, String methodName) {
		XposedHelpers.findAndHookMethod(className, lpparam.classLoader, methodName, Bundle.class, new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				// Hook函数之前执行的代码
				if (mContext == null) {
					mContext = (Context) param.thisObject;
					XposedBridge.log("获取到了Context");
				}

				Log.e(TAG, "[!] 获取到了Context");
			}

			@Override
			protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
				Log.e(TAG, "[!] afterHookedMethod");
			}
		});
	}

}
