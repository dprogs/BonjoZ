package com.dprogs.bonjo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.legacy.app.FragmentPagerAdapter;
//android.support.v13.app.FragmentPagerAdapter;
import androidx.legacy.app.FragmentStatePagerAdapter;
//android.support.v13.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
//android.support.v4.view.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
//android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.dprogs.bonjo.db.DBStorage;
import com.dprogs.bonjo.db.SongFile;
import com.dprogs.bonjo.db.SongFileTag;
import com.dprogs.bonjo.db.Tag;
import com.dprogs.bonjo.screens.Screen1;
import com.dprogs.bonjo.screens.Screen2;
import com.dprogs.bonjo.screens.Screen3;
import com.dprogs.bonjo.utils.ALog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Locale;


public class StartActivity extends Activity {

    private String TAG = ".Bonjo";

    boolean DBG = true;

    final String format1 = "%2d%17s%22s%18s%16s%16s";
    final String format2 = "%2s%16s%22s%18s%16s%16s";
    //DBHelper db;
    //SQLiteMyHelper myDb;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        DBStorage.createDatabase(this);

//        myDb = new SQLiteMyHelper(this, DBAppData.DB_NAME, null, 1);
//        Log.e("2", "123");
//        myDb.onCreate(myDb.getWritableDatabase());
//        Log.e("2", "123");
        boolean test = false;
        if (test) {
            addSongs();
            DBStorage.readSongs();
            Log.w("2", "#Delete song 11 from the table");
            deleteSongById(11);

            DBStorage.readSongs();

            DBStorage.getDatabase().TagSong(10, "home");
            DBStorage.getDatabase().TagSong(10, "guitar");
            DBStorage.getDatabase().TagSong(2, "night drive");
            DBStorage.getDatabase().TagSong(2, "home");
            DBStorage.getDatabase().TagSong(3, "rock");

            readTags();
            readTaggedSongs();

            //        Log.i("mi", "Song : " + myDb.getSongById(3).getSong());
            //        Log.i("mi", "Song : " + myDb.getSongById(4).getSong());

            showPlaylistByTagId(1);

            showTagsBySongId(10);
        }
        //deleteDB();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void initViews() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);

        //mViewPager.get
        //Button btnTranslate = (Button) mViewPager.findViewById(R.id.btn_scale);

//	    btnTranslate.setOnClickListener(new OnClickListener() {
//	
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			v.startAnimation(animTranslate);	
//			}
//	    });
    }

    private void addSongs() {
        ALog.i(TAG, "# Add songs");
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music", "laura.mp3", "Acapello", "02:04", "Laura Boji", "Memories 2003"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music", "laura3.mp3", "Laluby", "03:08", "Laura Boji", "Memories 2003"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music", "laura11.mp3", "I'm in love", "01:02", "Laura Boji", "Memories 2003"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music", "laura14.mp3", "Waterfall", "04:16", "Laura Boji", "Memories 2003"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music", "laura07.mp3", "Adventure", "00:31", "Laura Boji", "Greatest Hits"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music\\New", "bon jovi.mp3", "It's my life", "02:14", "John Bonjovi", "2000"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music\\New", "tnmk - voda.mp3", "����", "02:24", "�.�.�.�.", "���� 2004"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music\\New", "woc.mp3", "Wind of changes", "02:34", "Scorpions", null));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music\\New", "tropicana.mp3", "Club tropicna", "02:44", "George Michael", "Memories 2003"));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music\\New", "behind_blue_eyes.mp3", "Behind blue eyes", "02:54", "Limp bizkit", ""));
        DBStorage.getDatabase().addSong(new SongFile("D:\\Music\\New", "shapeofmyheart.mp3", "Shape of my heart", "03:04", "Sting", ""));
    }

//    private void readSongs() {
//    	ALog.w(TAG, "# Read songs table");
//    	
//        List<SongFile> songList = DBStorage.getDatabase().getAllSongs();
//        Log.w("MyTag", "[SONGS TABLE]");
//        Log.i("MyTag", String.format(format2, "ID", "FOLDER", "FILE", "SONG", "ARTIST", "ALBUM"));
//        for (SongFile sf : songList) {
//        	Log.i("MyTag", String.format(format1, sf.getId(), sf.getDestFolder(), sf.getFileName(), sf.getSong(), sf.getArtist(), sf.getAlbum()));
//        }
//    }

    private void readTags() {
        ALog.w(TAG, "# Read tags table");

        List<Tag> tagList = DBStorage.getDatabase().getAllTags();
        Log.w("MyTag", "[TAGS TABLE]");
        Log.i("MyTag", String.format("%2s%20s", "ID", "TAG"));
        for (Tag t : tagList) {
            Log.i("MyTag", String.format("%2d%20s", t.getId(), t.getTag()));
        }
    }

    private void readTaggedSongs() {
        ALog.w(TAG, "# Read tagged songs table");

        List<SongFileTag> taggedSongsList = DBStorage.getDatabase().getAllTaggedSongs();
        Log.w("MyTag", "[SONGS&TAGS TABLE]");
        Log.i("MyTag", String.format("%8s%8s", "SONG ID", "TAG ID"));
        for (SongFileTag t : taggedSongsList) {
            Log.i("MyTag", String.format("%4d%8d", t.getSongFileId(), t.getTagId()));
        }
    }

    private void showTagsBySongId(int songId) {
        //List<SongFileTag> taggedSongsList = myDb.getAllTaggedSongs();
        List<Tag> tagList = DBStorage.getDatabase().getTagsBySongId(songId);

        Log.i("MyTag", "[***********]");
        Log.w("MyTag", String.format("%8s%8s", "SONG ID", "#TAG"));
        for (Tag t : tagList) {
            Log.i("MyTag", String.format("%4d%8s", songId, "#" + t.getTag()));
        }
    }

    private void showPlaylistByTagId(int tagId) {
        List<SongFile> tagPlaylist = DBStorage.getDatabase().getSongsByTagId(tagId);
        Log.w("MyTag", "[PLAYLIST TAG #" + DBStorage.getDatabase().getTagById(tagId) + "]");
        Log.i("MyTag", String.format("%8s%8s", "SONG ID", "SONG NAME"));
        for (SongFile t : tagPlaylist) {
            Log.i("MyTag", String.format("%6d%8s", t.getId(), t.getSong()));
        }

    }

    private void deleteSongById(int id) {
        ALog.w(TAG, "# Delete song " + id + " from table");
        DBStorage.getDatabase().deleteSongFromTableById(id);
    }

    private void deleteDB() {
        ALog.w(TAG, "# Delete all DB tables");
        DBStorage.getDatabase().dropAllTables();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Start Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e(TAG, "--------------------------------------- pos " + position);
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return new Screen1();
                case 1:
                    return new Screen2();
                case 2:
                    return new Screen3();
            }
            Log.e(TAG, "--------------------------------------- finish ");
            return new Screen1();//PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.section_label);

            String label = String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER, 0));
            tv.setText(label);
            return rootView;
        }
    }

}
