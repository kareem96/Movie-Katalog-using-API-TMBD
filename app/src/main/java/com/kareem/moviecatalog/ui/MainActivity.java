package com.kareem.moviecatalog.ui;

import android.content.Intent;
import android.provider.Settings;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kareem.moviecatalog.R;
import com.kareem.moviecatalog.ui.fragment.movie.MovieFragment;
import com.kareem.moviecatalog.ui.fragment.tvshow.TvShowFragment;

public class MainActivity extends AppCompatActivity{
    private TabLayoutAdapter tabLayoutAdapter;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.app_name));
        setPager();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static MainActivity newInstance(FragmentManager fragmentManager){
        MainActivity fragment= new MainActivity();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }

    private void setPager() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        TabLayout tabs= findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager());
        tabLayoutAdapter.addFragment(MovieFragment.newInstance(fragmentManager), getString(R.string.movie));
        tabLayoutAdapter.addFragment(TvShowFragment.newInstance(fragmentManager), getString(R.string.tvshow));
        viewPager.setAdapter(tabLayoutAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings){
            if (item.getItemId() == R.id.action_settings){
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}