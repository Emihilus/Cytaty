package emis.msf.cytaty;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ireneusz on 2017-05-31.
 */
public class ShowPhotoActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_showphoto);

        ViewPager vp = (ViewPager) findViewById(R.id.view_pager);
        FSPAdapter adapterr = new FSPAdapter(getSupportFragmentManager());
        vp.setOffscreenPageLimit(0);
        vp.setAdapter(adapterr);

        super.onCreate(savedInstanceState);
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
            return 3;
        }

        @Override
        public int getItemPosition(Object object) {
            // POSITION_NONE makes it possible to reload the PagerAdapter
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position)
        {

            FragmentShowPhoto fraShP = new FragmentShowPhoto();
            Bundle bundle = new Bundle();
            switch (position)
                {
                case 0:
                    bundle.putInt("FN", position);
                    fraShP.setArguments(bundle);
                    break;

                case 1:
                        bundle.putInt("FN", position);
                        fraShP.setArguments(bundle);
                        break;

                case 2:
                    bundle.putInt("FN", position);
                    fraShP.setArguments(bundle);
                    break;
                }

            return fraShP;
        }
    }
}
