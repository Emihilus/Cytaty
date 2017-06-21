package emis.msf.cytaty;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActivityMain extends AppCompatActivity
{

    SharedPreferences dprefs;
    long providedDayid;
    TabLayout tabLayout;
    ViewPager vp;
    FSPAdapter adapterr;
    boolean rozne;
    boolean queneNDSC = false;
    int savedListPos;

    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dprefs = PreferenceManager.getDefaultSharedPreferences(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();


        providedDayid = getIntent().getLongExtra("day_id", -1);
        rozne = false;
        if (providedDayid != -1)
            rozne = true;

        U.L("Null value is: " + providedDayid);

        setupTabl();

        Calendar calendar = new GregorianCalendar();

    if(dprefs.getLong(C.PREF_INITDATE, -1) == -1)
            {
            //Date date = calendar.getTime();
            long hMilis = calendar.get(Calendar.HOUR_OF_DAY)*60*60*1000;//date.getTime();
            long mMilis = calendar.get(Calendar.MINUTE)*60*1000;// +date.getTimezoneOffset();
            long sMilis = calendar.get(Calendar.SECOND)*1000;
            dprefs.edit().putLong(C.PREF_INITDATE, calendar.getTimeInMillis() - hMilis - mMilis - sMilis +1 ).apply();
            }


        try
            {
            new DBHelperCytaty(ActivityMain.this).createDataBase();
            } catch (IOException e)
            {
            e.printStackTrace();
            }
       // dbh = new DBHelperCytaty(this);

    }




    class FSPAdapter extends FragmentStatePagerAdapter
    {
        public FSPAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return 5;
        }

        @Override
        public int getItemPosition(Object object) {
            // POSITION_NONE makes it possible to reload the PagerAdapter
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
                {
                case 0:
                    return new FragmentDnia();

                case 1:
                    FragmentLosowy fraLos = new FragmentLosowy();
                    U.L("provided "+ providedDayid);
                    if(providedDayid != -1)
                        {
                        Bundle bundle = new Bundle();
                        bundle.putLong("fralos", providedDayid);
                        fraLos.setArguments(bundle);
                        providedDayid = -1;
                        }
                    else
                        {
                        Bundle bundle = new Bundle();
                        bundle.putLong("fralos", -1);
                        fraLos.setArguments(bundle);
                        }
                    return  fraLos;

                case 2:
                    FragmentWszystkie fraWsz = new FragmentWszystkie();
                    U.L("Saved: " + savedListPos);
                    if(savedListPos != -1)
                        {
                        Bundle bundle = new Bundle();
                        bundle.putInt("pos", savedListPos);
                        fraWsz.setArguments(bundle);
                        savedListPos = -1;
                        }
                    return fraWsz;

                case 3:
                    return  new FragmentUlubione();

                case 4:
                    return  new FragmentBerthier();
                }
            return null;
        }
    }

    void setupTabl()
    {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        vp = (ViewPager) findViewById(R.id.view_pager);
        adapterr = new FSPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterr);
        tabLayout.setupWithViewPager(vp);

        tabLayout.getTabAt(0).setText("Cytat dnia");
        tabLayout.getTabAt(1).setText("Przeglądaj");
        tabLayout.getTabAt(2).setText("Lista cytatów");
        tabLayout.getTabAt(3).setText("Ulubione");
        tabLayout.getTabAt(4).setText("Ojciec Jan Berthier");

       vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                    if(queneNDSC)
                        favToggleAction(position);
                /*if(position==0)
                    findViewById(R.id.maaa).setBackgroundResource(R.drawable.gradient);
                else
                    findViewById(R.id.maaa).setBackgroundResource(R.drawable.gradient_s);*/
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        /*tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });*/

        if (rozne)
            {
            vp.setCurrentItem(1);
            rozne = false;
            }
    }

    void favToggleAction(int pos)
    {
        adapterr.notifyDataSetChanged();
        vp.setAdapter(adapterr);
        tabLayout.getTabAt(0).setText("Cytat dnia");
        tabLayout.getTabAt(1).setText("Przeglądaj");
        tabLayout.getTabAt(2).setText("Lista cytatów");
        tabLayout.getTabAt(3).setText("Ulubione");
        tabLayout.getTabAt(4).setText("Ojciec Jan Berthier");
        queneNDSC = false;//
        vp.setCurrentItem(pos);
    }

    void showBig(long providedDayid, int savedListPos)
    {
        adapterr.notifyDataSetChanged();
        this.providedDayid = providedDayid;
        vp.setAdapter(adapterr);
        tabLayout.getTabAt(0).setText("Cytat dnia");
        tabLayout.getTabAt(1).setText("Przeglądaj");
        tabLayout.getTabAt(2).setText("Lista cytatów");
        tabLayout.getTabAt(3).setText("Ulubione");
        tabLayout.getTabAt(4).setText("Ojciec Jan Berthier");
        vp.setCurrentItem(1);
        this.savedListPos = savedListPos;
    }
}
