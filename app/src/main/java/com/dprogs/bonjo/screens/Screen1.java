package com.dprogs.bonjo.screens;

import com.dprogs.bonjo.MediaUtils;
import com.dprogs.bonjo.R;
import com.dprogs.bonjo.db.DBStorage;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Screen1 extends Fragment {
	//FileDialog
	//https://code.google.com/archive/p/android-file-dialog/
	
	final int REQUEST_SAVE = 35;
	final int REQUEST_LOAD = 36;
	
	View mView;
	Button pickFileButton;
	Button showSongsButton;
	Button deleteDBButton;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		mView = inflater.inflate(R.layout.fragment_1, container, false);
		
		pickFileButton = (Button) mView.findViewById(R.id.pickFileButton);
		showSongsButton = (Button) mView.findViewById(R.id.showSongsListButton);
		deleteDBButton = (Button) mView.findViewById(R.id.clearTableButton);
		
		pickFileButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			    Intent intent = new Intent()
			    .setType("*/mp3")
			    .setAction(Intent.ACTION_GET_CONTENT);

			    startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_SAVE);		
			}
		});
		
		showSongsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DBStorage.readSongs();
			}
		});

		deleteDBButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DBStorage.getDatabase().dropAllTables();
			}
		});
		
        return mView;
    }

	public void prepareMedia(Uri uri) {
        DBStorage.getDatabase().addSong(MediaUtils.parseMP3(getActivity(), uri));
        DBStorage.readSongs();
	}
	
	@Override
	public synchronized void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {

	        Log.w("", "file path: " + data.getData().getPath());
	        prepareMedia(data.getData());

	    } else if (resultCode == Activity.RESULT_CANCELED) {
	    	Log.w("", "File not selected");
	    }		
	}
}