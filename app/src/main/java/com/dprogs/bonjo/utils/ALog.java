package com.dprogs.bonjo.utils;

import com.dprogs.bonjo.BuildConfig;

import android.util.Log;

public class ALog  {

	/**
	 * Default value based on BuildConfig.DEBUG
	 */
	private static boolean DEBUG = BuildConfig.DEBUG;
	
	private ALog() {
	}
	
	/**
	 * Set debug mode: <b>true</b> - for print log, <b>false</b> - nothing
	 * @param debug
	 */
	public static void setDebug(boolean debugFlag) {
		DEBUG = debugFlag;
	}
 	 
    /**
     * Send a {@link #VERBOSE} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (DEBUG) Log.v(tag, msg);
    }
	
    /**
     * Send a {@link #VERBOSE} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(boolean debugFlag, String tag, String msg) {
        if (debugFlag) Log.v(tag, msg);
    }

    /**
     * Send a {@link #DEBUG} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (DEBUG) Log.d(tag, msg);
    }
	
    /**
     * Send a {@link #DEBUG} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(boolean debugFlag, String tag, String msg) {
        if (debugFlag) Log.d(tag, msg);
    }
    
    /**
     * Send a {@link #INFO} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        if (DEBUG) Log.i(tag, msg);
    }
	
    /**
     * Send a {@link #INFO} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(boolean debugFlag, String tag, String msg) {
        if (debugFlag) Log.i(tag, msg);
    }
    
    /**
     * Send a {@link #WARN} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        if (DEBUG) Log.w(tag, msg);
    }
	
    /**
     * Send a {@link #WARN} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(boolean debugFlag, String tag, String msg) {
        if (debugFlag) Log.w(tag, msg);
    }

    /**
     * Send a {@link #ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (DEBUG) Log.e(tag, msg);
    }
	
    /**
     * Send a {@link #ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(boolean debugFlag, String tag, String msg) {
        if (debugFlag) Log.e(tag, msg);
    }
}