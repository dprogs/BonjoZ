package com.dprogs.bonjo.screens;

import java.util.ArrayList;

import com.dprogs.bonjo.R;
import com.dprogs.bonjo.db.DBStorage;
import com.dprogs.bonjo.db.SongFile;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Screen2 extends Fragment {
	
	final String TAG = "screen2";
	
	View mView;
	
	ListView mediaList;
	MediaListAdapter mediaListAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		mView = inflater.inflate(R.layout.fragment_2, container, false);
		
		mediaList = (ListView) mView.findViewById(R.id.mediaList);
		mediaList.setItemsCanFocus(false);

		mediaList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
			}
        	
        });
		
        return mView;
    }
    
	@Override
	public void onResume() {
		super.onResume();
        // Initializes list view adapter.
		mediaListAdapter = new MediaListAdapter();
		
        for (SongFile sf : DBStorage.getDatabase().getAllSongs()) {
        	Log.i("MyTag", "ID : " + sf.getId() + " / Title : " +  sf.getSong() + " / Artist : " + sf.getArtist() + " / Duration : " + sf.getDuration());
    		mediaListAdapter.addDevice(sf);
        }
        //mLeDeviceListAdapter
 		mediaList.setAdapter(mediaListAdapter);
		
	}
	
	public void onResumeFragment() {
	    Log.i(TAG, "onResumeFragment()");
	    Toast.makeText(getActivity(), "onResumeFragment():" + TAG, Toast.LENGTH_SHORT).show(); 
	}
	
	// Adapter for holding devices found through scanning.
    private class MediaListAdapter extends BaseAdapter {
        private ArrayList<SongFile> mMediaFiles;
        //private ArrayList<Integer> deviceTypeValue;
        private LayoutInflater layoutInflater;

        public MediaListAdapter() {
            super();
            mMediaFiles = new ArrayList<SongFile>();
            //deviceTypeValue = new ArrayList<Integer>();
            layoutInflater = getActivity().getLayoutInflater();//BLEScanFragment.this.getLayoutInflater();
        }
        
        public void addDevice(SongFile media) {
            if(!mMediaFiles.contains(media)) {
                mMediaFiles.add(media);
                //deviceTypeValue.add(deviceType);
            }
        }

        public SongFile getDevice(int position) {
            return mMediaFiles.get(position);
        }

//        public int getTypeValue(int position) {
//            return deviceTypeValue.get(position);
//        }

        public void clear() {
            mMediaFiles.clear();
        }

        @Override
        public int getCount() {
            return mMediaFiles.size();
        }

        @Override
        public Object getItem(int i) {
            return mMediaFiles.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("InflateParams")
		@Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = layoutInflater.inflate(R.layout.media_info_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mediaTitle = (TextView) view.findViewById(R.id.mediaTitle);
                viewHolder.mediaArtist = (TextView) view.findViewById(R.id.mediaArtist);
                viewHolder.mediaDuration = (TextView) view.findViewById(R.id.mediaDuration);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            SongFile media = mMediaFiles.get(i);
            
            viewHolder.mediaTitle.setText(media.getFileName());//media.getSong());
            viewHolder.mediaArtist.setText(media.getArtist());
            viewHolder.mediaDuration.setText(media.getDuration());
            	
            return view;
        }
    }

    static class ViewHolder {
        TextView mediaTitle;
        TextView mediaArtist;
        TextView mediaDuration;
    }

}
