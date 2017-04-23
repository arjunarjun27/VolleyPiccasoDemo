package com.example.dell.picassodemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.picassodemo.Model.MApFragment;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ViewPager viewpager = (ViewPager) findViewById(R.id.myviewpager);
        viewpager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));

    }


    public class MyFragmentAdapter extends FragmentPagerAdapter {
        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override

        public Fragment getItem(int position) {
            if (position == 0) {
                return new NewsFragment();
            } else {
                return  new MApFragment();
            }

        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "NEWS";
            } else {
                return  "MAP";
            }
        }
    }

}
