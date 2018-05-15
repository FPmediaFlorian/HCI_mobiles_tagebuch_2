package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DiaryDbHandler extends SQLiteOpenHelper {

    private static final String TAG = DiaryDbHandler.class.getSimpleName();

    private static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "entries";
    private static final String _id = "_id";
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private static final String DATE = "date";
    private static final String IMG_LINK = "imgLink";
    private static final String FEELING = "feeling";

    public static String getID() {
        return _id;
    }

    public static String getTITLE() {
        return TITLE;
    }

    public static String getTEXT() {
        return TEXT;
    }

    public static String getDATE() {
        return DATE;
    }

    public static String getImgLink() {
        return IMG_LINK;
    }

    /*
         * Create Table Entries
         */
    private static final String CREATE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _id +
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
         * Log Row _id of inserted Row
         * In case of Error: return value = -1
         */
        long insertedRowId = -1;

        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues contentVals = new ContentValues();

            Log.w(TAG, "Database Path: " + database.getPath());
            contentVals.put(TITLE, title);
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
             * currently logging _id of inserted Row
             */
            Log.d(TAG, "Function insert() executed. _id of inserted Row = " + insertedRowId);
        }
    }

    public void changeTitle(long uuid, String title) {
        /*
         * Log Row _id of inserted Row
         * In case of Error: return value = -1
         */
        long insertedRowId = -1;

        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues contentVal = new ContentValues();

            Log.w(TAG, "Database Path: " + database.getPath());
            contentVal.put(TITLE, title);

            insertedRowId = database.update(TABLE_NAME, contentVal, _id + " = ?",
                    new String[]{Long.toString(uuid)});
        } catch (SQLException e) {
            Log.e(TAG, "Error at changeTitle() function", e);
        } finally {
            Log.d(TAG, "Function changeTitle() executed. _id of inserted Row = " + insertedRowId + " New Title: " + title);
        }
    }

    public void changeText(long uuid, String text) {
        /*
         * Log Row _id of inserted Row
         * In case of Error: return value = -1
         */
        long insertedRowId = -1;

        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues contentVal = new ContentValues();

            Log.w(TAG, "Database Path: " + database.getPath());
            contentVal.put(TEXT, text);

            insertedRowId = database.update(TABLE_NAME, contentVal, _id + " = ?",
                    new String[]{Long.toString(uuid)});
        } catch (SQLException e) {
            Log.e(TAG, "Error at changeText() function", e);
        } finally {
            Log.d(TAG, "Function changeText() executed. _id of inserted Row = " + insertedRowId);
        }
    }

    public void changeDate(long uuid, String date) {
        /*
         * Log Row _id of inserted Row
         * In case of Error: return value = -1
         */
        long insertedRowId = -1;

        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues contentVal = new ContentValues();

            Log.w(TAG, "Database Path: " + database.getPath());
            contentVal.put(DATE, date);

            insertedRowId = database.update(TABLE_NAME, contentVal, _id + " = ?",
                    new String[]{Long.toString(uuid)});
        } catch (SQLException e) {
            Log.e(TAG, "Error at changeDate() function", e);
        } finally {
            Log.d(TAG, "Function changeDate() executed. _id of inserted Row = " + insertedRowId);
        }
    }

    public void changeImgLink(long uuid, String imgLink){
        /*
         * Log Row _id of inserted Row
         * In case of Error: return value = -1
         */
        long insertedRowId = -1;

        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues contentVal = new ContentValues();

            Log.w(TAG, "Database Path: " + database.getPath());
            contentVal.put(IMG_LINK, imgLink);

            insertedRowId = database.update(TABLE_NAME, contentVal, _id + " = ?",
                    new String[]{Long.toString(uuid)});
        } catch (SQLException e) {
            Log.e(TAG, "Error at changeImgLink() function", e);
        } finally {
            Log.d(TAG, "Function changeImgLink() executed. _id of inserted Row = " + insertedRowId);
        }
    }
    public void deleteEntry(long uuid) {
        SQLiteDatabase database = getWritableDatabase();
        int deletedRow = database.delete(TABLE_NAME, _id + " = ?",
                                            new String[] {Long.toString(uuid)});
        if(deletedRow == 0){
            Log.d(TAG, "Function deleteEntry() executed. Couldn't find specified row entry!");
        } else {
            Log.d(TAG, "Function deleteEntry() executed. _id of deleted Row = " + uuid);
        }
    }

    public Cursor getEntry(long uuid){
        String fetchRow = "select * from " + TABLE_NAME +" where " + _id + " = ' " + uuid + " ' ";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(fetchRow, null);
        return cursor;
    }

    public Cursor queryEntries(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query(TABLE_NAME, null, null, null,
                null, null, _id + " DESC");
    }
}
