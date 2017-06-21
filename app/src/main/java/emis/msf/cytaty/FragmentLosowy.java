package emis.msf.cytaty;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Ireneusz on 2017-04-06.
 */
public class FragmentLosowy extends Fragment
{

    TextView hv;
    TextView tv5;
    SharedPreferences dprefs;
    DBHelperCytaty dbh;
    long calculatedCyt;
    ImageView img;
    long providedDayid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        providedDayid = getArguments() != null ? getArguments().getLong("fralos") : -1;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.fragment_cytat_losowy,
                container, false);


        dprefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dbh = DBHelperCytaty.getInstance(getActivity());

        tv5 = ((TextView) layoutView.findViewById(R.id.textView5));
        hv = ((TextView) layoutView.findViewById(R.id.output));

        if(providedDayid == -1)
            calculatedCyt = dprefs.getLong("last cytat", 1);
        else
            calculatedCyt = providedDayid;

        Cursor c = dbh.getReadableDatabase()
                .rawQuery("SELECT cytat,fav,chapterid FROM cytaty WHERE day_id=" + calculatedCyt, null);
        c.moveToFirst();

        String calcCyt = c.getString(0);
        setCB(layoutView, calcCyt);
        hv.setText('"'+calcCyt+'"');
        tv5.setText(U.getKategoria(c.getInt(2)));

        layoutView.findViewById(R.id.blosuj).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hvClick(layoutView);
            }
        });

        img = (ImageView) layoutView.findViewById(R.id.random_img);
        if (c.getInt(1) == 1)
            img.setImageResource(R.mipmap.ic_fave);
        else
            img.setImageResource(R.mipmap.ic_favd);

        layoutView.findViewById(R.id.random_img).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor current = dbh.getReadableDatabase().rawQuery("SELECT fav FROM cytaty WHERE day_id="+calculatedCyt, null);
                current.moveToFirst();
                if (current.getInt(0) == 1)
                    {
                    Cursor untogle = dbh.getWritableDatabase().rawQuery("UPDATE cytaty SET fav=0 WHERE day_id="+calculatedCyt,null);
                    untogle.moveToFirst();
                    untogle.close();
                    Toast.makeText(getContext(), "Usunięto z ulubionych", Toast.LENGTH_SHORT).show();
                    img.setImageResource(R.mipmap.ic_favd);
                    }
                else
                    {
                    Cursor untogle = dbh.getWritableDatabase().rawQuery("UPDATE cytaty SET fav=1 WHERE day_id="+calculatedCyt,null);
                    untogle.moveToFirst();
                    untogle.close();
                    Toast.makeText(getContext(), "Dodano do ulubionych", Toast.LENGTH_SHORT).show();
                    img.setImageResource(R.mipmap.ic_fave);
                    }
                // c.close();
            }
        });

        layoutView.findViewById(R.id.bnx).setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            calculatedCyt +=1;
            if (calculatedCyt >579)
                calculatedCyt = 1;
            String calcCyt = getCytat((int) calculatedCyt);
            setCB(layoutView,calcCyt);
            hv.setText(calcCyt);
            dprefs.edit().putLong("last cytat", calculatedCyt).apply();
        }
    });

        layoutView.findViewById(R.id.bprv).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                calculatedCyt -=1;
                if (calculatedCyt == 0)
                    calculatedCyt = 579;
                String calcCyt = getCytat((int) calculatedCyt);
                setCB(layoutView, calcCyt);
                hv.setText(calcCyt);
                dprefs.edit().putLong("last cytat", calculatedCyt).apply();
            }
        });


        return layoutView;
    }

    void hvClick(View lva)
    {
        calculatedCyt = ((new Random().nextInt(579)) + 1);
        Cursor c = dbh.getReadableDatabase()
                .rawQuery("SELECT cytat,fav,chapterid FROM cytaty WHERE day_id=" + calculatedCyt, null);
        c.moveToFirst();

        String calcCyt = c.getString(0);
        setCB(lva, calcCyt);
        hv.setText('"'+calcCyt+'"');
        tv5.setText(U.getKategoria(c.getInt(2)));

        if (c.getInt(1) == 1)
            img.setImageResource(R.mipmap.ic_fave);
        else
            img.setImageResource(R.mipmap.ic_favd);

        dprefs.edit().putLong("last cytat", calculatedCyt).apply();
    }

    String getCytat(int dayid)
    {
        Cursor c = dbh.getReadableDatabase()
                .rawQuery("SELECT cytat,fav,chapterid FROM cytaty WHERE day_id=" + dayid, null);
        c.moveToFirst();


        tv5.setText(U.getKategoria(c.getInt(2)));

        if (c.getInt(1) == 1)
            img.setImageResource(R.mipmap.ic_fave);
        else
            img.setImageResource(R.mipmap.ic_favd);

        return '"'+c.getString(0)+'"';
    }

    void setCB(View lv, final String CBdata)
    {
        lv.findViewById(R.id.copya).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("cytat", CBdata);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Cytat skopiowany do schowka",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
