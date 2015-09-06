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

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.PlaylisttypeAdapter;
import com.example.administrator.musicplayer.fragment.PlayinglisttypeFragment;
import com.example.administrator.musicplayer.fragment.SonglistFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

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
        initView();
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
        transaction.add(R.id.fm_content, playlisttypefragment);
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
}
