package com.dprogs.bonjo.utils;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public class FileUtils {

	private static String TAG = ".FileUtils";
	
    public static boolean createFolder(String folder) {
    	File dir = new File(Environment.getExternalStorageDirectory() + folder);
    	Log.i(TAG, "Making app folder "+Environment.getExternalStorageDirectory() + folder);
    	return dir.mkdir();
    }

    public static boolean isFolderExists(String folder) {
    	File dir = new File(Environment.getExternalStorageDirectory() + folder);
        return dir.exists();
    }
    
}
