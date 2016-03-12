package com.example.hookdemo.ui;

import com.example.hookdemo.R;
import com.example.hookdemo.R.id;
import com.example.hookdemo.R.layout;
import com.example.hookdemo.model.HKOperateData;
import com.example.hookdemo.util.HKDataUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	private ListView lv_operatelist;

	private QuickAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		this.lv_operatelist = (ListView) this.findViewById(R.id.lv_operatelist);

		initAdapter();
		
		this.lv_operatelist.setAdapter(mAdapter);
		
		HKDataUtil.getHookInfo();
		this.mAdapter.addAll(HKDataUtil.gHookInfo);
	}

	private void initAdapter() {
		mAdapter = new QuickAdapter(this, R.layout.hook_item) {
			@Override
			protected void convert(BaseAdapterHelper helper, Object objModel) {
				final HKOperateData model = (HKOperateData) objModel;
				helper.setText(R.id.tv_team, model.team);
				CheckBox cb_hook = (CheckBox) helper.getView(R.id.cb_hook);
				cb_hook.setChecked("1".equals(model.hook));
				CheckBox cb_stack = (CheckBox) helper.getView(R.id.cb_stack);
				cb_stack.setChecked("1".equals(model.stack));

				cb_hook.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						model.hook = isChecked ? "1" : "0";
						HKDataUtil.saveHookInfo();
					}
				});

				cb_stack.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						model.stack = isChecked ? "1" : "0";
						HKDataUtil.saveHookInfo();
					}
				});
			}
		};
	}

	@Override
	public void onClick(View v) {

	}
}
