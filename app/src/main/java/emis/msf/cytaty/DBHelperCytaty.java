package emis.msf.cytaty;

/**
 * Created by Ireneusz on 2016-11-07.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelperCytaty extends SQLiteOpenHelper
{

    //The Android's default system path of your application database.
    private static String DB_SCIEZKA;// = Environment.getDataDirectory().getPath() + "/data/emis.katalog.ptakipolski/databases/";
    private static String DB_NAZWA = "cytaty.db";
    private final Context myContext;
    SQLiteDatabase checkDB;
    private SQLiteDatabase myDataBase;
    private volatile static DBHelperCytaty instance = null;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */

    public static DBHelperCytaty getInstance(Context context)
    {
        if (instance == null)
            {
            synchronized (DBHelperCytaty.class)
                {
                if (instance == null) {
                instance = new DBHelperCytaty(context);
                }
                }
            }
        return instance;
    }


    public DBHelperCytaty(Context context)
    {

        super(context, DB_NAZWA, null, C.WERSJA_BAZY_DANYCH);
        this.myContext = context;
        DB_SCIEZKA =  context.getApplicationInfo().dataDir+"/databases/";
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public boolean createDataBase() throws IOException
    {
        SQLiteDatabase db_Read = null;

        if (!checkDataBase())
            {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            db_Read = this.getReadableDatabase();
            db_Read.close();

            try
                {
                copyDataBase();
                } catch (IOException e)
                {
                e.printStackTrace();
                }

            return true;
            } else
            return false;

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase()
    {

        checkDB = null;

        try
            {
            String myPath = DB_SCIEZKA + DB_NAZWA;
           // U.L("path" , myPath);
            //;
          //  U.L("path CHECK" , new File(myPath).exists()+"");
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

            } catch (Exception e)
            {
            e.printStackTrace();

            }

        if (checkDB != null)
            {
            if (checkDB.getVersion() < C.WERSJA_BAZY_DANYCH)
                {


                checkDB.close();
                return false;
                } else
                {
                checkDB.close();
                return true;
                }

            } else
            {
            return false;
            }
    }


    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException
    {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAZWA);

        // Path to the just created empty db
        String outFileName = DB_SCIEZKA + DB_NAZWA;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0)
            {
            myOutput.write(buffer, 0, length);
            }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException
    {

        //Open the database
        String myPath = DB_SCIEZKA + DB_NAZWA;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close()
    {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}