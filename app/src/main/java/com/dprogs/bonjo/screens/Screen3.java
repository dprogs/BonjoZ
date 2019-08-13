package com.dprogs.bonjo.screens;

import com.dprogs.bonjo.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Screen3 extends Fragment {
	View mView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		mView = inflater.inflate(R.layout.fragment_3, container, false);
        return mView;
    }

}
