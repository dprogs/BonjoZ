package com.dprogs.bonjo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class TagItem extends Button implements OnClickListener {

	private String TAG = ".TagItem";
	
	public final int ALPHA = 0;
	public final int TRANSLATE = 1;
	public final int SCALE = 2;
	public final int ROTATE = 3;
	public final int COMPLEX = 4;
	
	private String tagName;
	int textSize;
	

	public TagItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public TagItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TagItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	void init() {
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
