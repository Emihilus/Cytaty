package emis.msf.cytaty;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Ireneusz on 2017-04-14.
 */
public class ActivityCard extends AppCompatActivity
{
    DBHelperCytaty dbh;
    Cursor cursor;
    ViewPager viewPager;

    String currentId;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        currentId = getIntent().getLongExtra("day_id", -1)+"";

        dbh = DBHelperCytaty.getInstance(this);
        cursor = dbh.getReadableDatabase().rawQuery("SELECT * FROM cytaty",null);

        viewPager = (ViewPager) findViewById(R.id.view_pager_card);
        viewPager.setAdapter(new FSPAdapter(getSupportFragmentManager(),cursor.getCount()));
        viewPager.setCurrentItem(Integer.parseInt(currentId)-1);
    }

    String getCytat(int dayid)
    {
       /* while (cursor.moveToNext())
            {
            if(cursor.getString(cursor.getColumnIndex(C.KOL_DAY_ID)).equals( dayid))
                {
                String toReturn ="("+ dayid+") "+cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT));
                cursor.moveToFirst();
                return toReturn;
                }
            }*/
        cursor.moveToPosition(dayid);
        return "("+ dayid+") "+cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT));
    }

    class FSPAdapter extends FragmentStatePagerAdapter
    {
        int count;
         FSPAdapter(FragmentManager fm)
        {
            super(fm);
        }

        FSPAdapter(FragmentManager fm, int count)
        {
            super(fm);
            this.count = count;
        }

        @Override
        public int getCount()
        {
            return count;
        }

        @Override
        public Fragment getItem(int position)
        {

            return ActivityCardFragment.init(getCytat(position));
        }
    }

}
