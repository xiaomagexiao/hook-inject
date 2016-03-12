package com.example.hookdemo.ui;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ProgressBar;


public abstract class QuickAdapter extends BaseAdapter {
    private static final String TAG  = "QuickAdapter";
    private final Context context;
    private final List data;
    private boolean displayIndeterminateProgress;
    private final int layoutResId;

    public QuickAdapter() {
    	this.context = null;
    	   this.data=new ArrayList();
    	   this.layoutResId=0;
    }
    
    public QuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

	public QuickAdapter(Context context, int layoutResId, List list) {
		super();
		this.displayIndeterminateProgress = false;
		this.data = list == null ? new ArrayList() : list;
		this.context = context;
		this.layoutResId = layoutResId;
	}

	public void add(int index, Object obj) {
        this.data.add(index, obj);
        this.notifyDataSetChanged();
    }

    public void add(Object list) {
        this.data.add(list);
        this.notifyDataSetChanged();
    }

    public void addAll(List list) {
        this.data.addAll(list);
        this.notifyDataSetChanged();
    }

    public void clear() {
    	if(this.data!=null){
    		this.data.clear();
    	}
        this.notifyDataSetChanged();
    }

    public boolean contains(Object obj) {
        return this.data.contains(obj);
    }

    protected abstract void convert(BaseAdapterHelper arg1, Object objModel);

    private View createIndeterminateProgressView(View convertView, ViewGroup parent) {
        FrameLayout frameLayout = null;
        if(convertView == null) {
            FrameLayout temp = new FrameLayout(this.context);
            temp.setForegroundGravity(17);
            temp.addView(new ProgressBar(this.context));
            frameLayout = temp;
        }

        return frameLayout;
    }

    public List getAdapterList() {
        return this.data;
    }

    public int getCount() {
        int count = this.displayIndeterminateProgress ? 1 : 0;
        return this.data==null?0:this.data.size() + count;
    }

	public Object getItem(int position) {
		return position >= this.data.size() ? null : this.data.get(position);
	}

    public long getItemId(int position) {
        return position;
    }

	public int getItemViewType(int position) {
		return position >= this.data.size() ? 1 : 0;
	}

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(this.getItemViewType(position) == 0) {
            BaseAdapterHelper baseAdapterHelper = BaseAdapterHelper.get(this.context, convertView, parent, this.layoutResId, position);
            this.convert(baseAdapterHelper, this.getItem(position));
            view = baseAdapterHelper.getView();
        }
        else {
            view = this.createIndeterminateProgressView(convertView, parent);
        }

        return view;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int indexOfItem(Object obj) {
        return this.data.indexOf(obj);
    }

    public boolean isEnabled(int position) {
    	return position < this.data.size();
    }

    public void remove(int index) {
        this.data.remove(index);
        this.notifyDataSetChanged();
    }

    public void remove(Object obj) {
        this.data.remove(obj);
        this.notifyDataSetChanged();
    }

	public void removeAll(List list) {
		if (list != null) {
			this.data.removeAll(list);
			this.notifyDataSetChanged();
		}
	}

    public void set(int index, Object obj) {
        this.data.set(index, obj);
        this.notifyDataSetChanged();
    }

    public void set(Object objIndex, Object obj) {
        this.set(this.data.indexOf(objIndex), obj);
    }

    public void showIndeterminateProgress(boolean display) {
        if(display != this.displayIndeterminateProgress) {
            this.displayIndeterminateProgress = display;
            this.notifyDataSetChanged();
        }
    }
    
	/**
	 * 是否是最后一个条目
	 * @param 集合中的对象
	 * @return
	 */
	public boolean isLastItem(Object obj) {
		if (this.data == null || this.data.size() < 1) {
			return false;
		}
		if (indexOfItem(obj) == this.data.size() - 1) {
			return true;
		}
		return false;
	}
}

