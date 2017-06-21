package emis.msf.cytaty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Ireneusz on 2017-05-06.
 */
public class WidgetConfiguration extends AppCompatActivity
{
    CheckBox fav;
    CheckBox kat_1;
    CheckBox kat_2;
    CheckBox kat_3;
    CheckBox kat_4;
    CheckBox kat_5;
    CheckBox kat_6;
    CheckBox kat_7;
    CheckBox kat_8;
    CheckBox kat_9;
    CheckBox kat_10;
    CheckBox kat_11;
    CheckBox kat_12;
    CheckBox kat_13;
    CheckBox kat_14;
    CheckBox kat_15;
    CheckBox kat_16;
    CheckBox kat_17;
    CheckBox kat_18;
    CheckBox kat_20;
    CheckBox kat_21;

    SharedPreferences dprefs;
    boolean switchs = true;
    DBHelperCytaty dbh = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        dprefs = PreferenceManager.getDefaultSharedPreferences(WidgetConfiguration.this);
        setContentView(R.layout.widget_configuration_activity);
        dbh = DBHelperCytaty.getInstance(WidgetConfiguration.this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int mAppWidgetId = 0;
        if (extras != null)
            {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            }

// If you receive an intent without the appropriate ID, then the system should kill this Activity//
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
            {
            finish();
            }

        final int finalMAppWidgetId = mAppWidgetId;
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              /*  AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(WidgetConfiguration.this);
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget);
                appWidgetManager.updateAppWidget(finalMAppWidgetId, views);*/

                Calendar calendar = new GregorianCalendar();


                //Date date = calendar.getTime();
                long hMilis = calendar.get(Calendar.HOUR_OF_DAY)*60*60*1000;//date.getTime();
                long mMilis = calendar.get(Calendar.MINUTE)*60*1000;// +date.getTimezoneOffset();
                long sMilis = calendar.get(Calendar.SECOND)*1000;

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                U.L("Final awid: " + finalMAppWidgetId + ", spineer: " + spinner.getSelectedItemId());
                switch ((int) spinner.getSelectedItemId())
                    {
                    case 0:
                        dprefs.edit().putString(finalMAppWidgetId + " type", "0").apply();
                        break;

                    case 1:
                        dprefs.edit().putString(finalMAppWidgetId + " type", "1").apply();
                        dprefs.edit().putString(finalMAppWidgetId + " current",
                                "" + (new Random().nextInt(580) + 1)).apply();
                        break;

                    case 2:
                        dprefs.edit().putString(finalMAppWidgetId + " type", "2").apply();
                        break;

                    case 3:
                        dprefs.edit().putString(finalMAppWidgetId + " type", "3").apply();

                        Cursor c = dbh.getReadableDatabase().rawQuery("SELECT cytat,day_id FROM cytaty WHERE fav=1", null);
                        if(c.getCount() != 0)
                        dprefs.edit().putString(finalMAppWidgetId + " current",
                                "" + new Random().nextInt(c.getCount())+ 1).apply();


                            dprefs.edit().putLong(finalMAppWidgetId + " dl_start",
                                    calendar.getTimeInMillis() - hMilis - mMilis - sMilis +1 ).apply();

                        break;

                    default:
                        dprefs.edit().putString(finalMAppWidgetId + " type", "4").apply();

                        switch ((int) ((Spinner) findViewById(R.id.spinner2)).getSelectedItemId())
                            {
                            case 0:
                                dprefs.edit().putString(finalMAppWidgetId + " order", "0").apply();
                                break;

                            case 1:
                                dprefs.edit().putString(finalMAppWidgetId + " order", "1").apply();
                                dprefs.edit().putString(finalMAppWidgetId + " current",
                                        "" + (new Random().nextInt(579) + 1)).apply();
                                break;
                            }

                        long interval = 0;
                        switch ((int) ((Spinner) findViewById(R.id.spinner3)).getSelectedItemId())
                            {
                            case 0:
                              interval = 86400000;
                                break;

                            case 1:
                                interval = 43200000;
                                break;

                            case 9:
                                interval = 10000;
                                break;

                            case 8:
                                interval = 1800000;
                                break;

                            case 7:
                                interval = 3600000;
                                break;

                            case 6:
                                interval = 7200000;
                                break;

                            case 5:
                                interval = 14400000;
                                break;

                            case 4:
                                interval = 21600000;
                                break;

                            case 3:
                                interval = 28800000;
                                break;

                            case 2:
                                interval = 36000000;
                                break;
                            }

                      /*  Calendar calendar2 = new GregorianCalendar();

                            //Date date = calendar.getTime();
                            long hMilis2 = calendar2.get(Calendar.HOUR_OF_DAY)*60*60*1000;//date.getTime();
                            long mMilis2 = calendar2.get(Calendar.MINUTE)*60*1000;// +date.getTimezoneOffset();
                            long sMilis2 = calendar2.get(Calendar.SECOND)*1000;*/

                        dprefs.edit().putLong(finalMAppWidgetId + " start peroid",
                                calendar.getTimeInMillis()).apply();// - hMilis - mMilis - sMilis +1 ).apply();
                        dprefs.edit().putLong(finalMAppWidgetId + " last peroid", System.currentTimeMillis() ).apply();// poczatek
                        dprefs.edit().putLong(finalMAppWidgetId + " interval", interval).apply();


                        /*int hours = Integer.parseInt(((EditText) findViewById(R.id.editText)).getText().toString());
                        int mins = Integer.parseInt(((EditText) findViewById(R.id.editText2)).getText().toString());
                        int millis = (hours * 60 * 60 * 1000) + (mins * 60 * 1000);
                        if (mins == 1)
                            millis = 5000;
*/

                        String katList = "";
                        boolean atLeastOne = false;
                        if (kat_1.isChecked())
                            katList += C.KAT_1_Cnoty + ",";
                        else
                            atLeastOne = true;

                        if (kat_2.isChecked())
                            katList += C.KAT_2_Maryja + ",";
                        else
                            atLeastOne = true;

                        if (kat_3.isChecked())
                            katList += C.KAT_3_MoPoZySaIDu + ",";
                        else
                            atLeastOne = true;

                        if (kat_4.isChecked())
                            katList += C.KAT_4_PoSzDlPrKaZa + ",";
                        else
                            atLeastOne = true;

                        if (kat_5.isChecked())
                            katList += C.KAT_5_PyEgSkSiTyNaSoIWlZd + ",";
                        else
                            atLeastOne = true;

                        if (kat_6.isChecked())
                            katList += C.KAT_6_SWIETA_RODZINA + ",";
                        else
                            atLeastOne = true;

                        if (kat_7.isChecked())
                            katList += C.KAT_7_RozneWskazania + ",";
                        else
                            atLeastOne = true;

                        if (kat_8.isChecked())
                            katList += C.KAT_8_IdealyDoskonaloscUswiecenie + ",";
                        else
                            atLeastOne = true;

                        if (kat_9.isChecked())
                            katList += C.KAT_9_PoDoZyZaUwDlZa + ",";
                        else
                            atLeastOne = true;

                        if (kat_10.isChecked())
                            katList += C.KAT_10_ZycieWspolnotyZakonnej + ",";
                        else
                            atLeastOne = true;

                        if (kat_11.isChecked())
                            katList += C.KAT_11_WoBoMiDoBoObDlWlWoISiSa + ",";
                        else
                            atLeastOne = true;

                        if (kat_12.isChecked())
                            katList += C.KAT_12_MisjeIMisjonarze + ",";
                        else
                            atLeastOne = true;

                        if (kat_13.isChecked())
                            katList += C.KAT_13_MiBlMiDoWy + ",";
                        else
                            atLeastOne = true;

                        if (kat_14.isChecked())
                            katList += C.KAT_14_NaukaIStudia + ",";
                        else
                            atLeastOne = true;

                        if (kat_15.isChecked())
                            katList += C.KAT_15_KapKazMiLu + ",";
                        else
                            atLeastOne = true;

                        if (kat_16.isChecked())
                            katList += C.KAT_16_Praca + ",";
                        else
                            atLeastOne = true;

                        if (kat_17.isChecked())
                            katList += C.KAT_17_PoSkOp + ",";
                        else
                            atLeastOne = true;

                        if (kat_18.isChecked())
                            katList += C.KAT_18_ReKoRe + ",";
                        else
                            atLeastOne = true;

                        if (kat_20.isChecked())
                            katList += C.KAT_20_UmPrNaISiSa + ",";
                        else
                            atLeastOne = true;

                        if (kat_21.isChecked())
                            katList += C.KAT_21_WaIPrSiDoSt + ",";
                        else
                            atLeastOne = true;

                        if (atLeastOne)
                            {


                            String queryPart="";
                            String[] table = katList.split(",");
                            for (String cat : table)
                                {
                                queryPart += "chapterid="+cat+" OR ";
                                }
                            queryPart = queryPart.substring(0,queryPart.length()-4);
                            Cursor ck = dbh.getReadableDatabase().rawQuery("SELECT cytat,day_id FROM cytaty WHERE "+ queryPart, null);

                           // c.moveToFirst();


                            dprefs.edit().putString(finalMAppWidgetId + " current", (new Random().nextInt(ck.getCount()))+"").apply();
                            }
                        else
                            katList = "-1";

                        dprefs.edit().putString(finalMAppWidgetId + " kats", katList).apply();

                        if (fav.isChecked())
                            {
                            dprefs.edit().putBoolean(finalMAppWidgetId + " ofav", true).apply();

                            if(((Spinner) findViewById(R.id.spinner2)).getSelectedItemId() == 1)
                                {
                                Cursor sk = dbh.getReadableDatabase().rawQuery("SELECT fav FROM cytaty WHERE fav=1", null);
                                sk.moveToFirst();
                                dprefs.edit().putString(finalMAppWidgetId + " current",
                                        "" + (new Random().nextInt(sk.getCount()) + 1)).apply();
                                }

                            }
                        else
                            dprefs.edit().putBoolean(finalMAppWidgetId + " ofav", false).apply();

                        break;
                    }

                Intent resultValue = new Intent();
                setResult(RESULT_OK, resultValue);

                Intent intent = new Intent(WidgetConfiguration.this, WidgetProvider.class);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, finalMAppWidgetId);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                sendBroadcast(intent);

                finish();
            }
        });

        kat_1 = (CheckBox) findViewById(R.id.checkBox);
        kat_2 = (CheckBox) findViewById(R.id.checkBox2);
        kat_3 = (CheckBox) findViewById(R.id.checkBox3);
        kat_4 = (CheckBox) findViewById(R.id.checkBox4);
        kat_5 = (CheckBox) findViewById(R.id.checkBox5);
        kat_6 = (CheckBox) findViewById(R.id.checkBox6);
        kat_7 = (CheckBox) findViewById(R.id.checkBox7);
        kat_8 = (CheckBox) findViewById(R.id.checkBox8);
        kat_9 = (CheckBox) findViewById(R.id.checkBox9);
        kat_10 = (CheckBox) findViewById(R.id.checkBox10);
        kat_11 = (CheckBox) findViewById(R.id.checkBox11);
        kat_12 = (CheckBox) findViewById(R.id.checkBox12);
        kat_13 = (CheckBox) findViewById(R.id.checkBox13);
        kat_14 = (CheckBox) findViewById(R.id.checkBox14);
        kat_15 = (CheckBox) findViewById(R.id.checkBox15);
        kat_16 = (CheckBox) findViewById(R.id.checkBox16);
        kat_17 = (CheckBox) findViewById(R.id.checkBox17);
        kat_18 = (CheckBox) findViewById(R.id.checkBox18);
        kat_20 = (CheckBox) findViewById(R.id.checkBox19);
        kat_21 = (CheckBox) findViewById(R.id.checkBox20);
        fav = (CheckBox) findViewById(R.id.checkBox21);

        Spinner fsp =  (Spinner) findViewById(R.id.spinner);
        fsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 4)
                    findViewById(R.id.csection).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.csection).setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (switchs)
                    switchs = false;
                else
                    switchs = true;


                kat_1.setChecked(switchs);
                kat_2.setChecked(switchs);
                kat_3.setChecked(switchs);
                kat_4.setChecked(switchs);
                kat_5.setChecked(switchs);
                kat_6.setChecked(switchs);
                kat_7.setChecked(switchs);
                kat_8.setChecked(switchs);
                kat_9.setChecked(switchs);
                kat_10.setChecked(switchs);
                kat_11.setChecked(switchs);
                kat_12.setChecked(switchs);
                kat_13.setChecked(switchs);
                kat_14.setChecked(switchs);
                kat_15.setChecked(switchs);
                kat_16.setChecked(switchs);
                kat_17.setChecked(switchs);
                kat_18.setChecked(switchs);
                kat_20.setChecked(switchs);
                kat_21.setChecked(switchs);
            }
        });

        String kats = dprefs.getString(finalMAppWidgetId + " kats", "-1");
        if (!kats.equals("-1"))
            {
            String[] table = kats.split(",");
            for (String cat : table)
                {
                if (!cat.equals(""+C.KAT_1_Cnoty))
                    kat_1.setChecked(false);

                if (!cat.equals(""+C.KAT_2_Maryja))
                    kat_2.setChecked(false);

                if (!cat.equals(""+C.KAT_3_MoPoZySaIDu))
                    kat_3.setChecked(false);

                if (!cat.equals(""+C.KAT_4_PoSzDlPrKaZa))
                    kat_4.setChecked(false);

                if (!cat.equals(""+C.KAT_5_PyEgSkSiTyNaSoIWlZd))
                    kat_5.setChecked(false);

                if (!cat.equals(""+C.KAT_6_SWIETA_RODZINA))
                    kat_6.setChecked(false);

                if (!cat.equals(""+C.KAT_7_RozneWskazania))
                    kat_7.setChecked(false);

                if (!cat.equals(""+C.KAT_8_IdealyDoskonaloscUswiecenie))
                    kat_8.setChecked(false);

                if (!cat.equals(""+C.KAT_9_PoDoZyZaUwDlZa))
                    kat_9.setChecked(false);

                if (!cat.equals(""+C.KAT_10_ZycieWspolnotyZakonnej))
                    kat_10.setChecked(false);

                if (!cat.equals(""+C.KAT_11_WoBoMiDoBoObDlWlWoISiSa))
                    kat_11.setChecked(false);

                if (!cat.equals(""+C.KAT_12_MisjeIMisjonarze))
                    kat_12.setChecked(false);

                if (!cat.equals(""+C.KAT_13_MiBlMiDoWy))
                    kat_13.setChecked(false);

                if (!cat.equals(""+C.KAT_14_NaukaIStudia))
                    kat_14.setChecked(false);

                if (!cat.equals(""+C.KAT_15_KapKazMiLu))
                    kat_15.setChecked(false);

                if (!cat.equals(""+C.KAT_16_Praca))
                    kat_16.setChecked(false);

                if (!cat.equals(""+C.KAT_17_PoSkOp))
                    kat_17.setChecked(false);

                if (!cat.equals(""+C.KAT_18_ReKoRe))
                    kat_18.setChecked(false);

                if (!cat.equals(""+C.KAT_20_UmPrNaISiSa))
                    kat_20.setChecked(false);

                if (!cat.equals(""+C.KAT_21_WaIPrSiDoSt))
                    kat_21.setChecked(false);


                }
            }

        ((Spinner) findViewById(R.id.spinner))
                .setSelection(Integer.parseInt(dprefs.getString(finalMAppWidgetId + " type", "0")));

        ((Spinner) findViewById(R.id.spinner2))
                .setSelection(Integer.parseInt(dprefs.getString(finalMAppWidgetId + " order", "0")));

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        switch ((int) dprefs.getLong(finalMAppWidgetId + " interval", 10000))
            {
            case 86400000:
                spinner3.setSelection(0);
                break;

            case 43200000:
                spinner3.setSelection(1);
                break;

            case 10000:
                spinner3.setSelection(9);
                break;

            case 1800000:
                spinner3.setSelection(8);
                break;

            case 3600000:
                spinner3.setSelection(7);
                break;

            case 7200000:
                spinner3.setSelection(6);
                break;

            case 14400000:
                spinner3.setSelection(5);
                break;

            case 21600000:
                spinner3.setSelection(4);
                break;

            case 28800000:
                spinner3.setSelection(3);
                break;

            case 36000000:
                spinner3.setSelection(2);
                break;
            }
        /*((EditText) findViewById(R.id.editText))
                .setText(""+Integer.parseInt(dprefs.getString(finalMAppWidgetId + " interval", "0"))/1000/60/60);

        ((EditText) findViewById(R.id.editText2))
                .setText(""+Integer.parseInt(dprefs.getString(finalMAppWidgetId + " interval", "0"))/1000/60);*/

        //boolean favs =  ;
        fav.setChecked(dprefs.getBoolean(finalMAppWidgetId + " ofav", false));

        findViewById(R.id.ba).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                findViewById(R.id.ba).setVisibility(View.GONE);
                findViewById(R.id.cbs).setVisibility(View.VISIBLE);
            }
        });
    }
}
