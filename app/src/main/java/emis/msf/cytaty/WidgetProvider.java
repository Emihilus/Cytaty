package emis.msf.cytaty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WidgetProvider extends AppWidgetProvider
{
    boolean b_vis;
    String calculatedID;
    String savedDID;
    boolean savedFS;
    int widgetId;
    SharedPreferences dprefs;
   int layId = R.layout.widget;

    /*BroadcastReceiver broadcastReceiverSPS = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {

            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
            //Acquire the lock
            wl.acquire();
            U.L("AM Received");
            //You can do the processing here update the widget/remote views.
            dprefs.edit().putString(widgetId + " current",
                    "" + (new Random().nextInt(580) + 1)).apply();


            Intent Fintent = new Intent(context, WidgetProvider.class);
            Fintent.setAction("refresh");
            Fintent.putExtra("
            b_vis", b_vis);
            Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            context.sendBroadcast(Fintent);
            //Release the lock
            wl.release();
        }
    };*/

    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        super.onDeleted(context, appWidgetIds);
        U.L("ON DELETED");
    }

    @Override
    public void onDisabled(Context context)
    {
        U.L("ON Disabled");
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context)
    {
        U.L("ON ENABLED");
        dprefs = PreferenceManager.getDefaultSharedPreferences(context);
        super.onDisabled(context);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions)
    {
        U.L("ON APPWIDGETOPTIONSCHANGED");
        dprefs = PreferenceManager.getDefaultSharedPreferences(context);
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds)
    {
        U.L("ON RESTORED");
        dprefs = PreferenceManager.getDefaultSharedPreferences(context);
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        U.L("ONReceived " + intent.getBooleanExtra("b_vis", false));
        dprefs = PreferenceManager.getDefaultSharedPreferences(context);
        AppWidgetManager awm = AppWidgetManager.getInstance(context);


        b_vis = intent.getBooleanExtra("b_vis", false);


        if (intent.getAction() != null && intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1) > 0)
            {

            if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE))
                {
                int wID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                U.L("Click from: " + wID);
                if (wID != -1)
                    updateAppWidget(context, awm, wID, false);
                }

            if (intent.getAction().equals("next"))
                {
                int wID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                U.L("NEXT Click from: " + wID);
                if (wID != -1)
                    {
                  /*  String prefObj = dprefs.getString("" + wID, "0,1");
                    int last = Integer.parseInt(U.getWidgetPreferenceValue(prefObj, 1)) + 1;
                    dprefs.edit()
                            .putString("" + wID, U.editWidgetPreference(prefObj, 1, last + "")).apply();*/
                    // dprefs.edit().putString(wID + " current", (new Random().nextInt(580)+1)+"").apply();
                    updateAppWidget(context, awm, wID, true);
                    }
                }

            if (intent.getAction().equals("previous"))
                {
                int wID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                U.L("PREVIOUS Click from: " + wID);
                if (wID != -1)
                    {
                    /*String prefObj = dprefs.getString("" + wID, "0,1");
                    int last = Integer.parseInt(U.getWidgetPreferenceValue(prefObj, 1)) - 1;
                    dprefs.edit()
                            .putString("" + wID, U.editWidgetPreference(prefObj, 1, last + "")).apply();*/
                    // dprefs.edit().putString(wID + " current", (new Random().nextInt(580)+1)+"").apply();
                    updateAppWidget(context, awm, wID, true);
                    }
                }

            if (intent.getAction().equals("refresh"))
                {
                int wID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                U.L("refresh Click from: " + wID);
                if (wID != -1)
                    {
                    if (intent.getBooleanExtra("peroid", false))
                        dprefs.edit().putLong(wID + " last peroid", System.currentTimeMillis()).apply();
                    updateAppWidget(context, awm, wID, true);
                    }
                }

            if (intent.getAction().equals("refresh type1"))
                {
                int wID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                U.L("refresh type1 Click from: " + wID);
                if (wID != -1)
                    {
                    /*String prefObj = dprefs.getString("" + wID, "0,1");
                    int last = Integer.parseInt(U.getWidgetPreferenceValue(prefObj, 1)) - 1;
                    dprefs.edit()
                            .putString("" + wID, U.editWidgetPreference(prefObj, 1, last + "")).apply();*/
                    dprefs.edit().putString(wID + " current", (new Random().nextInt(579) + 1) + "").apply();
                    updateAppWidget(context, awm, wID, true);
                    }
                }

            if (intent.getAction().equals("FTOGLE"))
                {
                int wID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                U.L("ftoggle Click from: " + wID + " " + intent.getStringExtra("sid") + " " + intent.getBooleanExtra("FST", false));
                if (wID != -1)
                    {

                    DBHelperCytaty ddb = DBHelperCytaty.getInstance(context);
                    if (!intent.getBooleanExtra("FST", false))
                        {
                        ddb.getWritableDatabase().rawQuery("UPDATE cytaty SET fav=1 WHERE day_id="
                                + intent.getStringExtra("sid"), null).moveToFirst();

                        } else
                        {
                        ddb.getWritableDatabase().rawQuery("UPDATE cytaty SET fav=0 WHERE day_id="
                                + intent.getStringExtra("sid"), null).moveToFirst();
                        }


                    updateAppWidget(context, awm, wID, true);
                    }
                }

            if (intent.getAction().equals("copy"))
                {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("cytat", intent.getStringExtra("copy"));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Cytat skopiowany do schowka",Toast.LENGTH_SHORT).show();
                }

            }


        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        //   final int count = appWidgetIds.length;
        U.L("ON UPDATE");
       /* context.getApplicationContext().registerReceiver(broadcastReceiverSPS,
                new IntentFilter("android.appwidget.action.AM"));*/

        dprefs = PreferenceManager.getDefaultSharedPreferences(context);
        for (int i = 0; i < appWidgetIds.length; i++)
            {
            widgetId = appWidgetIds[i];

            updateAppWidget(context, appWidgetManager, widgetId, false);
            }
    }

    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int widgetId, boolean buttonClick)
    {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
               this.getLayoutId());

        if (b_vis)
            remoteViews.setViewVisibility(R.id.buttons, View.VISIBLE);
        else
            remoteViews.setViewVisibility(R.id.buttons, View.GONE);

        String calcCyt = calculateCytat(context, widgetId, buttonClick);
        remoteViews.setTextViewText(R.id.textView, calcCyt);


        if (savedFS)
            remoteViews.setImageViewResource(R.id.wnb_fav, R.mipmap.ic_fave);
        else
            remoteViews.setImageViewResource(R.id.wnb_fav, R.mipmap.ic_favd);

        Intent intentD = new Intent(context, ActivityMain.class);
        intentD.putExtra("day_id", Long.parseLong(savedDID));
        intentD.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent DPendingIntent = PendingIntent.getActivity(context, widgetId, intentD, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wb_szczeg, DPendingIntent);


        Intent intentC = new Intent(context, WidgetConfiguration.class);
        intentC.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        intentC.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent CPendingIntent = PendingIntent.getActivity(context, widgetId, intentC, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wb_conf, CPendingIntent);

        Intent Copintent = new Intent(context, WidgetProvider.class);
        Copintent.setAction("copy");
        Copintent.putExtra("b_vis", b_vis);
        Copintent.putExtra("copy", calcCyt);
        Copintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        PendingIntent CoppendingIntent = PendingIntent.getBroadcast(context,
                widgetId, Copintent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wnb_copy, CoppendingIntent);

        Intent FTintent = new Intent(context, WidgetProvider.class);
        FTintent.setAction("FTOGLE");
        FTintent.putExtra("b_vis", b_vis);
        FTintent.putExtra("FST", savedFS);
        FTintent.putExtra("sid", savedDID);
        FTintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        PendingIntent FTpendingIntent = PendingIntent.getBroadcast(context,
                widgetId, FTintent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wnb_fav, FTpendingIntent);

      /* switch (dprefs.getString(widgetId + " type", "0"))
            {
            case "4":
                Intent Fintent = new Intent(context, WidgetProvider.class);
                Fintent.setAction("next");
                Fintent.putExtra("b_vis", b_vis);
                Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                PendingIntent FpendingIntent = PendingIntent.getBroadcast(context,
                        widgetId, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.wnb_next, FpendingIntent);

                Intent Pintent = new Intent(context, WidgetProvider.class);
                Pintent.setAction("previous");
                Pintent.putExtra("b_vis", b_vis);
                Pintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                PendingIntent PpendingIntent = PendingIntent.getBroadcast(context,
                        widgetId, Pintent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.wnb_prev, PpendingIntent);
                break;
            }*/

        remoteViews.setViewVisibility(R.id.wnb_next, View.GONE);
        remoteViews.setViewVisibility(R.id.wnb_prev, View.GONE);

      /*  if (dprefs.getString(widgetId + " type", "0").equals("4"))
            {
;
            } else
            {
            }*/

        Intent intent = new Intent(context, WidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        if (b_vis)
            intent.putExtra("b_vis", false);
        else
            intent.putExtra("b_vis", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.textView, pendingIntent);


        appWidgetManager.updateAppWidget(widgetId, remoteViews);


        U.L("Updated widget " + widgetId);
    }

    String calculateCytat(Context context, int wid, boolean buttonClick)
    {
        DBHelperCytaty dbh = DBHelperCytaty.getInstance(context);

        dprefs = PreferenceManager.getDefaultSharedPreferences(context);
        U.L("Saved data of widget: " + wid + " : " + dprefs.getString(wid + " type", "nqull"));

        String kats = dprefs.getString(wid + " kats", "");

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent Fintent = new Intent(context, WidgetProvider.class);

        Calendar calendar = new GregorianCalendar();


        //Date date = calendar.getTime();
        long hMilis = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000;//date.getTime();
        long mMilis = calendar.get(Calendar.MINUTE) * 60 * 1000;// +date.getTimezoneOffset();
        long sMilis = calendar.get(Calendar.SECOND) * 1000;
        long presentDay = calendar.getTimeInMillis() - hMilis - mMilis - sMilis + 1;

        switch (dprefs.getString(wid + " type", "0"))
            {
            case "0":
                calculatedID = "" + getCyclesPassed(579);
                savedDID = calculatedID;

                //After after 3 seconds
                Fintent.setAction("refresh");
                Fintent.putExtra("b_vis", b_vis);
                Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                PendingIntent pi = PendingIntent.getBroadcast(context, 0, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);
                am.cancel(pi);
                am.set(AlarmManager.RTC_WAKEUP, presentDay + 86405000, pi);
                break;

            case "1":
                calculatedID = dprefs.getString(wid + " current", "2");
                savedDID = calculatedID;


                U.L("Scheduling " + wid);
                Fintent.setAction("refresh type1");
                Fintent.putExtra("b_vis", b_vis);
                Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, wid);
                PendingIntent pi1 = PendingIntent.getBroadcast(context, wid, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);
                am.cancel(pi1);
                am.set(AlarmManager.RTC_WAKEUP, presentDay + 86405000, pi1);
                break;

            case "2":
                Cursor c2 = dbh.getReadableDatabase().rawQuery("SELECT cytat,day_id,fav FROM cytaty WHERE fav=1", null);
                if (c2.getCount() == 0)
                    {
                    savedDID = "0";
                    return "Brak ulubionych cytatów";
                    }
                U.L(c2.getCount() + " " + c2.getColumnCount());
                calculatedID = getCyclesPassed(c2.getCount()) + "";
                c2.moveToPosition(Integer.parseInt(calculatedID) - 1);
                savedDID = c2.getString(1);

                Fintent.setAction("refresh");
                Fintent.putExtra("b_vis", b_vis);
                Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                PendingIntent pi2 = PendingIntent.getBroadcast(context, wid, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);
                am.cancel(pi2);
                am.set(AlarmManager.RTC_WAKEUP, presentDay + 86405000, pi2);

                if (c2.getInt(2) == 1)
                    savedFS = true;
                else
                    savedFS = false;
                return '"'+c2.getString(0)+'"';

            case "3":
                Cursor c3 = dbh.getReadableDatabase().rawQuery("SELECT cytat,day_id,fav FROM cytaty WHERE fav=1", null);
                if (c3.getCount() == 0)
                    {
                    savedDID = "0";
                    return "Brak ulubionych cytatów";
                    }


                if ((presentDay - 86400000) > dprefs.getLong(wid + " dl_start", 0))
                    {
                    dprefs.edit().putString(wid + " current",
                            "" + (new Random().nextInt(c3.getCount()))).apply();
                    dprefs.edit().putLong(wid + " dl_start", presentDay).apply();
                    }
                calculatedID = dprefs.getString(wid + " current", "2");
                c3.moveToPosition(Integer.parseInt(calculatedID));
                savedDID = c3.getString(1);

                Fintent.setAction("refresh");
                Fintent.putExtra("b_vis", b_vis);
                Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                PendingIntent pi3 = PendingIntent.getBroadcast(context, 0, Fintent, 0);
                am.cancel(pi3);
                am.set(AlarmManager.RTC_WAKEUP, presentDay + 86405000, pi3);

                if (c3.getInt(2) == 1)
                    savedFS = true;
                else
                    savedFS = false;

                return '"'+c3.getString(0)+'"';

            case "4": //niestandardowy

                String order = dprefs.getString(wid + " order", "0"); //kolejność
                long interval = dprefs.getLong(wid + " interval", 0); // co ile
                long startPeroid = dprefs.getLong(wid + " start peroid", 0); // począwszy
                boolean onlyFavs = dprefs.getBoolean(wid + " ofav", false); // czy tylko ulubione
                long lastPeroid = dprefs.getLong(wid + " last peroid", 50);

                U.L("stp: " + startPeroid + " inter: " + interval);

                if (onlyFavs)
                    {
                    Cursor c4 = dbh.getReadableDatabase().rawQuery("SELECT cytat,day_id,fav FROM cytaty WHERE fav=1", null);
                    if (c4.getCount() == 0)
                        {
                        savedDID = "0";
                        return "Brak ulubionych cytatów";
                        }

                    if (order.equals("0")) // nielosowy
                        {
                        calculatedID = "" + getCustomCyclesPassed(c4.getCount(), startPeroid, interval);
                        c4.moveToPosition(Integer.parseInt(calculatedID)-1);
                        savedDID = c4.getString(1);


                        U.L("adding intent  " + wid);
                        Fintent.setAction("refresh");
                        Fintent.putExtra("b_vis", b_vis);
                        Fintent.putExtra("peroid", true);
                        Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, wid);
                        PendingIntent pi4 = PendingIntent.getBroadcast(context, wid, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);


                        am.cancel(pi4);
                        am.set(AlarmManager.RTC_WAKEUP, lastPeroid + interval, pi4);

                        if (c4.getInt(2) == 1)
                            savedFS = true;
                        else
                            savedFS = false;

                        return '"'+c4.getString(0)+'"';
                        }
                    else
                        {

                        if ((lastPeroid + interval) < System.currentTimeMillis())
                            {
                            dprefs.edit().putString(wid + " current",
                                    "" + (new Random().nextInt(c4.getCount()))).apply();
                            dprefs.edit().putLong(wid + " last peroid", System.currentTimeMillis()).apply();
                           U.L("New value is " +dprefs.getString(wid + " current", "0"));
                            lastPeroid = dprefs.getLong(wid + " last peroid", 50);
                            }
                        calculatedID = dprefs.getString(wid + " current", "0");
                        c4.moveToPosition(Integer.parseInt(calculatedID));
                        savedDID = c4.getString(1);

                        U.L("adding intent  " + wid + " intrwl: " + System.currentTimeMillis() + " -> " + (lastPeroid+interval));
                        Fintent.setAction("refresh");
                        Fintent.putExtra("b_vis", b_vis);
                        Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, wid);
                        PendingIntent pi5 = PendingIntent.getBroadcast(context, 0, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);
                        am.cancel(pi5);
                        am.set(AlarmManager.RTC_WAKEUP, lastPeroid + interval, pi5);

                        if (c4.getInt(2) == 1)
                            savedFS = true;
                        else
                            savedFS = false;

                        return '"'+c4.getString(0)+'"';

                        }

                    //savedDID = calculatedID;
                    }
                else
                    {

                    if ((order).equals("0")) // nielosowy
                        {
                        calculatedID = "" + getCustomCyclesPassed(579, startPeroid, interval);
                        U.L("adding intent  " + wid);


                        if ((lastPeroid + interval) < System.currentTimeMillis())
                            {
                            dprefs.edit().putLong(wid + " last peroid", System.currentTimeMillis()).apply();
                            lastPeroid = dprefs.getLong(wid + " last peroid", 50);
                            }

                        Fintent.setAction("refresh");
                        Fintent.putExtra("b_vis", b_vis);
                        Fintent.putExtra("peroid", true);
                        Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, wid);
                        PendingIntent pi4 = PendingIntent.getBroadcast(context, wid, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);


                        am.cancel(pi4);
                        am.set(AlarmManager.RTC_WAKEUP, lastPeroid + interval, pi4);
                        }
                    else
                        {
                        calculatedID = dprefs.getString(wid + " current", "2"); //losowy
                        U.L("adding intent  " + wid);

                        if ((lastPeroid + interval) < System.currentTimeMillis())
                            {
                            dprefs.edit().putLong(wid + " last peroid", System.currentTimeMillis()).apply();
                            lastPeroid = dprefs.getLong(wid + " last peroid", 50);
                            }

                        Fintent.setAction("refresh type1");
                        Fintent.putExtra("b_vis", b_vis);
                        Fintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, wid);
                        PendingIntent pi4 = PendingIntent.getBroadcast(context, wid, Fintent, PendingIntent.FLAG_UPDATE_CURRENT);
                        am.cancel(pi4);
                        am.set(AlarmManager.RTC_WAKEUP, lastPeroid + interval, pi4);
                        }

                    savedDID = calculatedID;
                    }
                /*if (!kats.equals("-1"))
                    {
                    String queryPart = "";
                    String[] table = kats.split(",");
                    for (String cat : table)
                        {
                        queryPart += "chapterid=" + cat + " OR ";
                        }
                    queryPart = queryPart.substring(0, queryPart.length() - 4);
                    Cursor c = dbh.getReadableDatabase().rawQuery("SELECT cytat,day_id FROM cytaty WHERE " + queryPart, null);
                    //U.L("Count = " +c.getCount()+" query part: " + queryPart);
                    if (buttonClick)
                        dprefs.edit().putString(wid + " current", new Random().nextInt(c.getCount()) + "").apply();

                    //U.L("NUMBER ="+ dprefs.getString(wid+" current","2") +"Count = " +c.getCount()+" query part: " + queryPart);
                    if (dprefs.getString(wid + " order", "0").equals("0")) // nielosowy
                        {
                        calculatedID = getCyclesPassed(c.getCount()) - 1 + "";
                        c.moveToPosition(Integer.parseInt(calculatedID));
                        } else
                        {//losowy
                        calculatedID = dprefs.getString(wid + " current", "2");
                        c.moveToPosition(Integer.parseInt(calculatedID));
                        }


                    // savedDID = c.getString(1);
                    // return c.getString(0);

                    }*/

                break;

            }

        //U.L("calculatedid: " + calculatedID);
        Cursor c = dbh.getReadableDatabase().rawQuery("SELECT cytat,fav FROM cytaty WHERE day_id=" + calculatedID, null);
        c.moveToFirst();
        if (c.getInt(1) == 1)
            savedFS = true;
        else
            savedFS = false;
        return '"'+c.getString(0)+'"';
    }

    long getCyclesPassed(int pulaCytatow)
    {
        final Calendar myDate = new GregorianCalendar();
        myDate.setTimeInMillis(dprefs.getLong(C.PREF_INITDATE, 0));

        Calendar currentCalendar = new GregorianCalendar();


        return (TimeUnit.MILLISECONDS.toDays(currentCalendar.getTimeInMillis()
                - dprefs.getLong(C.PREF_INITDATE, 0)) % pulaCytatow) + 1;
    }

    long getCustomCyclesPassed(int pulaCytatow, long startP, long interV)
    {
        final Calendar myDate = new GregorianCalendar();
        myDate.setTimeInMillis(dprefs.getLong(C.PREF_INITDATE, 0));

        Calendar currentCalendar = new GregorianCalendar();

        return (currentCalendar.getTimeInMillis() - startP) / interV % pulaCytatow + 1;
    }

    int getLayoutId()
    {
        return R.layout.widget;
    }
}