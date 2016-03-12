package com.example.hookdemo.hook;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MyHookWebview extends MyHookCommon {
	private static MyHookWebview instance;

	public static MyHookWebview getInstance() {
		if (instance == null) {
			instance = new MyHookWebview();
		}
		return instance;
	}

	public void hookWebView(LoadPackageParam lpparam, boolean print) {
		printStack = print;
		XposedHelpers.findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "loadUrl", String.class,
				commonHook("mHookWebViewLoadUrl"));
		XposedHelpers.findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "loadData", String.class, String.class,
				String.class, commonHook("mHookWebViewLoadData"));
		XposedHelpers.findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "loadDataWithBaseURL", String.class,
				String.class, String.class, String.class, String.class, commonHook("mHookWebViewLoadDataWithBaseURL"));
		XposedHelpers.findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "addJavascriptInterface", Object.class,
				String.class, commonHook("mHookWebViewAddJavascriptInterface"));
		// 优酷的方式
		// WebViewContentsClientAdapter
		// com.android.webview.chromium.WebViewContentsClientAdapter
		// android.webkit.WebViewClient
		XposedHelpers.findAndHookMethod("com.android.webview.chromium.WebViewContentsClientAdapter", lpparam.classLoader,
				"shouldOverrideUrlLoading", String.class, commonHook("mHookWebViewShouldOverrideUrlLoading"));

		// 纳米盒子的方式
		// at
		// com.android.webview.chromium.WebViewContentsClientAdapter.handleJsPrompt(WebViewContentsClientAdapter.java:658)
		XposedHelpers.findAndHookMethod("com.android.webview.chromium.WebViewContentsClientAdapter", lpparam.classLoader,
				"handleJsPrompt", String.class, String.class, String.class, Object.class, commonHook("handleJsPrompt"));
	}
}
