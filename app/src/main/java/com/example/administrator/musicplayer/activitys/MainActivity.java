package com.example.administrator.musicplayer.activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.PlaylisttypeAdapter;
import com.example.administrator.musicplayer.fragment.PlayinglisttypeFragment;
import com.example.administrator.musicplayer.fragment.SonglistFragment;
import com.example.administrator.musicplayer.interfaces.PlaylistypeOnItemClickListener;
import com.example.administrator.musicplayer.interfaces.SonglistOnItemClickListener;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity implements PlaylistypeOnItemClickListener,SonglistOnItemClickListener{

    @Bind(R.id.iv_playing_album)
    ImageView iv_PlayingAlbum;
    @Bind(R.id.tv_playing_name)
    TextView tv_PlayingName;
    @Bind(R.id.nv_playlisttype_navigation)
    NavigationView nv_PlaylisttypeNavigation;
    @Bind(R.id.dl_playlisttype)
    DrawerLayout dl_Playlisttype;

    public PlayinglisttypeFragment playlisttypefragment;
    public SonglistFragment songlistfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            initView();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    private void initView() {
//        PlaylisttypeAdapter playlisttypeAdapter = new PlaylisttypeAdapter(MainActivity.this, 147, 20);
//        lv_Playlisttype.setAdapter(playlisttypeAdapter);
//        lv_Playlisttype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(MainActivity.this, "click"+ position, Toast.LENGTH_SHORT).show();
//                if (position < 2) {
//                    Intent intent = new Intent(MainActivity.this, SonglistActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

            setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        playlisttypefragment = new PlayinglisttypeFragment();
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
        if (position < 2) {
//                    activity.replaceFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            if (this.songlistfragment == null) {
                this.songlistfragment = new SonglistFragment();
            }
//                    ft.replace(R.id.fm_content, activity.songlistfragment);
            ft.hide(playlisttypefragment);
            ft.add(R.id.fm_content, this.songlistfragment, "Songlist");
            ft.addToBackStack(null);
            ft.commit();
            Toast.makeText(MainActivity.this, "click" + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnSonglistItemClick(View view, int position) {
        String songname;
        TextView tv_songname = (TextView)view.findViewById(R.id.tv_item_songlist_songname);
        TextView tv_singername = (TextView)view.findViewById(R.id.tv_item_songlist_singername);
        Intent intent = new Intent(MainActivity.this, SongPlayingActivity.class);
        intent.putExtra("playingtitle", tv_songname.getText().toString()+"-"+tv_singername.getText().toString());
        startActivity(intent);
    }
}
