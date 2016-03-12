package com.example.hookdemo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class BaseAdapterHelper {
	private View convertView;
	private int position;
	private final SparseArray<View> views;

	private BaseAdapterHelper(Context context, ViewGroup parent, int layoutId, int position) {
		super();
		this.position = position;
		this.views = new SparseArray<View>();
		this.convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.convertView.setTag(this);
	}

	public static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId) {
		return BaseAdapterHelper.get(context, convertView, parent, layoutId, -1);
	}

	static BaseAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
		BaseAdapterHelper helper = convertView == null ? new BaseAdapterHelper(context, parent, layoutId, position)
				: (BaseAdapterHelper) convertView.getTag();
		return helper;
	}

	public int getPosition() {
		if (this.position == -1) {
			throw new IllegalStateException(
					"Use BaseAdapterHelper constructor with position if you need to retrieve the position.");
		}

		return this.position;
	}

	public View getView() {
		return this.convertView;
	}

	public View getView(int viewId) {
		return this.retrieveView(viewId);
	}

	public BaseAdapterHelper linkify(int viewId) {
		Linkify.addLinks((Spannable) this.retrieveView(viewId), 15);
		return this;
	}

	private View retrieveView(int viewId) {
		View view = this.views.get(viewId);
		if (view == null) {
			view = this.convertView.findViewById(viewId);
			this.views.put(viewId, view);
		}
		return view;
	}

	public BaseAdapterHelper setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView view = (ImageView) this.retrieveView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	public BaseAdapterHelper setImageDrawable(int viewId, Drawable drawable) {
		ImageView view = (ImageView) this.retrieveView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}

	public BaseAdapterHelper setImageOnClickListener(int viewId, View.OnClickListener listener) {
		this.retrieveView(viewId).setOnClickListener(listener);
		return this;
	}

	public BaseAdapterHelper setImageResource(int viewId, int imageResId) {
		ImageView view = (ImageView) this.retrieveView(viewId);
		if (view != null) {
			view.setImageResource(imageResId);
		}
		return this;
	}

	public BaseAdapterHelper setLayoutOnClickListener(int viewId, View.OnClickListener listener) {
		this.retrieveView(viewId).setOnClickListener(listener);
		return this;
	}

	public BaseAdapterHelper setMax(int viewId, int max) {
		((RatingBar) this.retrieveView(viewId)).setMax(max);
		return this;
	}

	public BaseAdapterHelper setProgress(int viewId, int progress) {
		((ProgressBar) this.retrieveView(viewId)).setProgress(progress);
		return this;
	}

	public BaseAdapterHelper setProgress(int viewId, int progress, int max) {
		ProgressBar progressBar = (ProgressBar) this.retrieveView(viewId);
		progressBar.setProgress(progress);
		progressBar.setMax(max);
		return this;
	}

	public BaseAdapterHelper setRating(int viewId, float rating) {
		((RatingBar) this.retrieveView(viewId)).setRating(rating);
		return this;
	}

	public BaseAdapterHelper setRating(int viewId, float rating, int max) {
		RatingBar ratingBar = (RatingBar) this.retrieveView(viewId);
		ratingBar.setRating(rating);
		ratingBar.setMax(max);
		return this;
	}

	public BaseAdapterHelper setText(int viewId, String value) {
		TextView view = (TextView) this.retrieveView(viewId);
		view.setText((CharSequence) value);
		return this;
	}

	public BaseAdapterHelper setTextColor(int viewId, int color) {
		((TextView) this.retrieveView(viewId)).setTextColor(color);
		return this;
	}

	public BaseAdapterHelper setTextEnabled(int viewId, Boolean enabled) {
		this.retrieveView(viewId).setEnabled(enabled.booleanValue());
		return this;
	}

	public BaseAdapterHelper setTextOnClickListener(int viewId, View.OnClickListener listener) {
		this.retrieveView(viewId).setOnClickListener(listener);
		return this;
	}

	public BaseAdapterHelper setTypeface(int viewId, Typeface typeface) {
		((TextView) this.retrieveView(viewId)).setTypeface(typeface);
		return this;
	}

	public BaseAdapterHelper setTypeface(Typeface typeface, int[] viewIds) {
		int v3 = viewIds.length;
		int v2;
		for (v2 = 0; v2 < v3; ++v2) {
			((TextView) this.retrieveView(viewIds[v2])).setTypeface(typeface);
		}

		return this;
	}

	public BaseAdapterHelper setView(int viewId, int resid) {
		this.retrieveView(viewId).setBackgroundResource(resid);
		return this;
	}

	public BaseAdapterHelper setVisible(int viewId, boolean visible) {
		this.retrieveView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}

}
