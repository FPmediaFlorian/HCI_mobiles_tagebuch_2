package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DiaryDbHandler extends SQLiteOpenHelper {

    private static final String TAG = DiaryDbHandler.class.getSimpleName();

    private static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "entries";
    private static final String ID = "uuid";
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private static final String DATE = "date";
    private static final String IMG_LINK = "imgLink";
    private static final String FEELING = "feeling";

    /*
     * Create Table Entries
     */
    private static final String CREATE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + TEXT + " TEXT, " + DATE +
            " TEXT, " + IMG_LINK + " TEXT, " + FEELING + " INTEGER);";

    /*
     * Drop Table Entries
     */
    private static final String DROP_ENTRIES_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    DiaryDbHandler(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w(TAG, "Database created...");
        sqLiteDatabase.execSQL(CREATE_ENTRIES_TABLE);
    }

    /*
     * Note on onUpgrade():
     * Naive and simple implementation. Should be changes at later stage of development.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldDBVersion, int newDBVersion) {
        Log.w(TAG, "Database Version update...(old Table deleted");
        sqLiteDatabase.execSQL(DROP_ENTRIES_TABLE);
        this.onCreate(sqLiteDatabase);
    }

    public void insertEntry(String title, String text, String date, String imgLink, int feeling ) {
        /*
         * Log Row ID of inserted Row
         * In case of Error: return value = -1
         */
        long insertedRowId = -1;

        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues contentVals = new ContentValues();

            Log.w(TAG, "Database Path: " + database.getPath());
            contentVals.put(TITLE, title );
            contentVals.put(TEXT, text);
            contentVals.put(DATE, date);
            contentVals.put(IMG_LINK, imgLink);
            contentVals.put(FEELING, feeling);

            insertedRowId = database.insert(TABLE_NAME, null, contentVals);
        } catch (SQLException e) {
            Log.e(TAG, "Error at insert() function", e);
        } finally {
            /*
             * log something... :)
             * currently logging ID of inserted Row
             */
            Log.d(TAG, "Function insert() executed. Id of inserted Row = " + insertedRowId);
        }
    }
}
