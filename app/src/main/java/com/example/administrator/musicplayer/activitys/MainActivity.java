package com.example.administrator.musicplayer.activitys;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.PlaybackList;
import com.example.administrator.musicplayer.fragment.PlayinglisttypeFragment;
import com.example.administrator.musicplayer.fragment.SonglistFragment;
import com.example.administrator.musicplayer.interfaces.PlaylistypeOnItemClickListener;
import com.example.administrator.musicplayer.interfaces.SonglistOnItemClickListener;
import com.example.administrator.musicplayer.utils.InfoUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends Activity implements PlaylistypeOnItemClickListener, SonglistOnItemClickListener {


    public PlayinglisttypeFragment playlisttypefragment;
    public SonglistFragment songlistfragment;
    @Bind(R.id.fm_content)
    FrameLayout fm_Content;
    @Bind(R.id.iv_playing_album)
    ImageView iv_PlayingAlbum;
    @Bind(R.id.tv_playing_name)
    TextView tv_PlayingName;
    @Bind(R.id.ibtn_bottom_playorpause)
    ImageButton ibtn_Bottom_Playorpause;
    @Bind(R.id.bottomplaying)
    RelativeLayout bottomplaying;
    @Bind(R.id.nv_playlisttype_navigation)
    NavigationView nv_Playlisttype_Navigation;
    @Bind(R.id.dl_playlisttype)
    DrawerLayout dl_Playlisttype;


    private ArrayList<PlaybackList> PlaybackListdata;

    MusicPlayerApplication musicPlayerApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicPlayerApplication = MusicPlayerApplication.getInstance();
        Log.i("State", "State -->" + musicPlayerApplication.getState() + "&" + musicPlayerApplication.getPLAYING());
        initData();
        if (savedInstanceState == null) {
            initView();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void initData() {
        PlaybackListdata = InfoUtil.InitPlaybackListInfo(this);
    }

    //未解决：获取不了控件对象
    private void initView() {
//        View layout = getLayoutInflater().inflate(R.layout.layout_bottomplaying, null);
//        tv_PlayingName = (TextView) layout.findViewById(R.id.tv_playing_name);
//        ibtn_Bottom_Playorpause = (ImageButton) layout.findViewById(R.id.ibtn_bottom_playorpause);
//        tv_PlayingName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SongPlayingActivity.class);
//                startActivityForResult(intent, 0);
//            }
//        });
//        if (ibtn_Bottom_Playorpause == null) {
//            Log.e("Error", "控件为空");
//        }
//        ibtn_Bottom_Playorpause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pause();
//            }
//        });
        setDefaultFragment();

    }

    private void pause() {
        if (musicPlayerApplication.getMediaPlayer().isPlaying()) {
            musicPlayerApplication.getMediaPlayer().pause();
            musicPlayerApplication.ChangetoPauseState();
        } else if (musicPlayerApplication.getState() == musicPlayerApplication.getPAUSE()) {
            musicPlayerApplication.getMediaPlayer().start();
            musicPlayerApplication.ChangetoPlayingState();
        }
    }

    private void setDefaultFragment() {
        playlisttypefragment = new PlayinglisttypeFragment(PlaybackListdata);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fm_content, playlisttypefragment, "Playlisttype");
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnListtypeItemClick(int position) {
        Log.i("position", "" + position);
        Log.i("Playbacklistdatasize", "size = " + PlaybackListdata.size());

        if (position > 0) {
            //position-1是因为第一个item为新建列表按钮
            if (PlaybackListdata.get(position - 1).getSonglist().size() > 0) {
                musicPlayerApplication.setCurSongList(PlaybackListdata.get(position - 1).getSonglist());
                songlistfragment = new SonglistFragment(PlaybackListdata.get(position - 1).getSonglist());
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(playlisttypefragment);
                ft.add(R.id.fm_content, songlistfragment, "Songlist");
                ft.addToBackStack(null);
                ft.commit();
                Toast.makeText(MainActivity.this, "click" + position, Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }

    @Override
    public void OnSonglistItemClick(View view, int position) {
        TextView tv_songname = (TextView) view.findViewById(R.id.tv_item_songlist_songname);
        TextView tv_singername = (TextView) view.findViewById(R.id.tv_item_songlist_singername);

        tv_PlayingName.setText(tv_songname.getText().toString() + "-" + tv_singername.getText().toString());

        musicPlayerApplication.setCurposition(position);

        if (musicPlayerApplication.getState() == musicPlayerApplication.getPLAYING()) {
            musicPlayerApplication.getMediaPlayer().stop();
        }
        Intent intent = new Intent(MainActivity.this, SongPlayingActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                String str = data.getStringExtra("PlayingTitle");//str即为回传的值
                tv_PlayingName.setText(str);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.setAction("com.example.administrator.service.AUDIOPLAY_SERVICE");
        stopService(intent);
    }
}
