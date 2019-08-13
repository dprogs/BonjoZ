package com.dprogs.bonjo;

import java.io.File;
import java.util.HashMap;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.dprogs.bonjo.db.SongFile;

public class MediaUtils {

	static final String TAG = ".MediaUtils";
	
	public static SongFile parseMP3(Context context, Uri uri) {
		SongFile sf;// = null;
		MediaMetadataRetriever mmr = new MediaMetadataRetriever();
		if(mmr != null) {
			try {
//				if (Build.VERSION.SDK_INT >= 14)
//					 mmr.setDataSource(path, new HashMap<String, String>());
//					else
//					 mmr.setDataSource(path);
				mmr.setDataSource(uri.getPath());
				//mmr.setDataSource(context, uri);//setDataSource(path,new HashMap<String, String>());
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				Log.e(TAG, "MediaMetadataRetriever source error: " + uri.getPath());
			}
		}

        File f = new File(uri.getPath());

        String destFolder = f.getParent();

        String fileName = f.getName();
        
        String albumName =
		     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);		
		String artistName =
			     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);		
		String titleName =
			     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
		String duration =
			     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		
        System.out.println("Song path: " + f.getParent());// /home/jigar/Desktop
        System.out.println("Song file: " + f.getName());  //1.txt
        System.out.println("Duration: " + parseDuration(duration));// /home/jigar/Desktop
        System.out.println("Artist name: " + artistName);  //1.txt
        System.out.println("Album name: " + albumName);// /home/jigar/Desktop
        System.out.println("Title name: " + titleName);// /home/jigar/Desktop

		sf = new SongFile(destFolder, fileName, titleName, parseDuration(duration), artistName, albumName);
		
		return sf;
	}
	
	static String parseDuration(String value) {
//		  long durationMs = Long.parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
//          long duration = durationMs / 1000;
//          long h = duration / 3600;
//          long m = (duration - h * 3600) / 60;
//          long s = duration - (h * 3600 + m * 60);
//          String durationValue;
//          if (h == 0) {
//             durationValue = String.format(
//             activity.getString(R.string.details_ms), m, s);
//             } else {
//                  durationValue = String.format(
//                  activity.getString(R.string.details_hms), h, m, s);
//               }
//          }  

		int duration = 0;
		if (value != null) {
		    duration = Integer.parseInt(value);
		}
		
		duration = duration / 1000;
		
		long h = duration / 3600;
		long m = (duration - h * 3600) / 60;
		long s = duration - (h * 3600 + m * 60);
		
		return m + ":" + s;
	}
}