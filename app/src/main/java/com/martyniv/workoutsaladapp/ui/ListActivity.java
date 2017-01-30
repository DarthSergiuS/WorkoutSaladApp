package com.martyniv.workoutsaladapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.martyniv.workoutsaladapp.R;
import com.martyniv.workoutsaladapp.fragment.WorkoutListFragment;

import java.util.ArrayList;
import java.util.List;
public class ListActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    private WorkoutListFragment workoutList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.workoutsalad);
        toolbar.setTitleTextColor(Color.WHITE);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setFab();

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(workoutList= new WorkoutListFragment(), "Workout List");
        adapter.addFragment(workoutList= new WorkoutListFragment(), "Week List");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setFab(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.getPageTitle(tabLayout.getSelectedTabPosition())=="Week List"){
                    Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", -1);
                    bundle.putString("Title", null);
                    bundle.putString("Text", null);
                    bundle.putString("Type",workoutList.getCurrentBodyType());
                    intent.putExtra("bundleExtras", bundle);
                    startActivity(intent);
                }
            }
        });
    }
}




