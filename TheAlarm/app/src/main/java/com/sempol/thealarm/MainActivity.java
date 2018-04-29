package com.sempol.thealarm;

        import android.annotation.SuppressLint;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.support.design.widget.TabLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.FragmentActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;

        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.sempol.thealarm.model.TimeModel;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.TimeUnit;

public class MainActivity extends FragmentActivity {

    public static List<TimeModel> times = new ArrayList<>();
    public static List<Long> alarm_time = new ArrayList<Long>();
    public static TimeAdapter adapter;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_access_alarm_black_24dp);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_settings_black_24dp);
        final TabLayout.Tab alarm = tabLayout.newTab();
        final TabLayout.Tab pengaturan = tabLayout.newTab();

        alarm.setIcon(R.drawable.ic_access_alarm_green_24dp);
        pengaturan.setIcon(R.drawable.ic_settings_black_24dp);
        alarm.setText("Alarm");
        pengaturan.setText("Setting");

        tabLayout.addTab(alarm, 0);
        tabLayout.addTab(pengaturan, 1);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        alarm.setIcon(R.drawable.ic_access_alarm_green_24dp);
                        pengaturan.setIcon(R.drawable.ic_settings_black_24dp);

                        break;
                    case 1:
                        alarm.setIcon(R.drawable.ic_access_alarm_black_24dp);
                        pengaturan.setIcon(R.drawable.ic_settings_grenn_24dp);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
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

        public PlaceholderFragment() {
        }

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

        @SuppressLint("ResourceType")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
           View rootView = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:
                    //Pindah ke TAB 1
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    RecyclerView rvTime = (RecyclerView) rootView.findViewById(R.id.rv_time);

                    ///inflater recyclerview
                    adapter = new TimeAdapter(getActivity(), times);

                    rvTime.setAdapter(adapter);
                    rvTime.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                    FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
                    fab.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view) {
                            DialogHandler dialogHandler = new DialogHandler();
                            dialogHandler.show(getActivity().getSupportFragmentManager(),"time_picker");
                        }
                    });
                    break;

                case 2:
                    //pindah ke TAB 2
                    rootView = inflater.inflate(R.layout.activity_setting, container, false);
                    break;
            }


            return rootView;

         }
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
            return PlaceholderFragment.newInstance(position+1);
        }

        @Override
        public int getCount() {
            // Show the numbe of pages.
            return 2;
        }
    }

    public void setAlarm(long time) {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent intent = new Intent(this, AlarmReceiver.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
    }

//    public Long converter (long hour, long minute){
//        hour = TimeUnit.HOURS.toMillis(hour);
//        minute = TimeUnit.MINUTES.toMillis(minute);
//
//        long time_inMillis = hour+minute;
//        return  time_inMillis;
//    }
}
