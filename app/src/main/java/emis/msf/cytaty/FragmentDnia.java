package emis.msf.cytaty;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ireneusz on 2017-04-06.
 */
public class FragmentDnia extends Fragment
{
    SharedPreferences dprefs;
    DBHelperCytaty dbh;

    TextView hv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.fragment_cytat_dnia,
            container, false);

        dprefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dbh = DBHelperCytaty.getInstance(getActivity());


        hv = ((TextView) layoutView.findViewById(R.id.hworld));
        TextView tv3 = ((TextView) layoutView.findViewById(R.id.textView3));

        final Calendar myDate = new GregorianCalendar();
        myDate.setTimeInMillis(dprefs.getLong(C.PREF_INITDATE, 0));

       /* hv.setText("Calculated date: /n "+ myDate.get(Calendar.HOUR_OF_DAY) +" "
                + myDate.get(Calendar.MINUTE)+ " " + myDate.get(Calendar.SECOND));*/




        Calendar currentCalendar = new GregorianCalendar();
        final long days = TimeUnit.MILLISECONDS.toDays(currentCalendar.getTimeInMillis()
                - dprefs.getLong(C.PREF_INITDATE, 0));
        final long calculatedCyt = ((days%579)+1);
        final Cursor c = dbh.getReadableDatabase().rawQuery("SELECT cytat,fav,chapterid FROM cytaty WHERE day_id="+calculatedCyt, null);
        c.moveToFirst();

        tv3.setText(U.getKategoria(Integer.parseInt(c.getString(2))));

        final ImageView img = (ImageView) layoutView.findViewById(R.id.imgbut);
        if (c.getInt(1) == 1)
            img.setImageResource(R.mipmap.ic_fave);
        else
            img.setImageResource(R.mipmap.ic_favd);

        final String calcCyt = c.getString(0);
        hv.setText(/*"Days since first run = " + days
                + " minutes: " + (TimeUnit.MILLISECONDS.toMinutes(currentCalendar.getTimeInMillis()
                - dprefs.getLong(C.PREF_INITDATE, 0)))
                + " Calculated CYTAT: "+ */'"'+calcCyt+'"');

        layoutView.findViewById(R.id.copys).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("cytat", calcCyt);
                clipboard.setPrimaryClip(clip);
                Toast.makeText( getContext(), "Cytat skopiowany do schowka",Toast.LENGTH_SHORT).show();
            }
        });

        hv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((ActivityMain)getActivity()).showBig(calculatedCyt,-1);
            }
        });

        layoutView.findViewById(R.id.imgbut).setOnClickListener(new View.OnClickListener()
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

        return layoutView;
    }
}
