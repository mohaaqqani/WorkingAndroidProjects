package com.example.omer.databasetry1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.omer.databasetry1.Helper.FeedReaderDbHelper;
import com.example.omer.databasetry1.Models.FeedReaderContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  To Insert Inton DB
        FeedReaderDbHelper  mDbHelper   =   new FeedReaderDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        SQLiteDatabase  db  =   mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values  =   new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,"Hi");

        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,"Hello World");

        // Insert the new row, returning the primary key value of the new row
        long    newRowId    =   db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);

        Log.d(TAG, "onCreate: RowID :   "+newRowId);
        db.close();


        //  To Read From DB
//        FeedReaderDbHelper  mDbHelper   =   new FeedReaderDbHelper(this);

        // Gets the data repository in Right mode
        SQLiteDatabase  db1  =   mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[]    projection  =   {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db1.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List    itemIds =   new ArrayList<>();
        while (cursor.moveToNext()){
            long    itemId  =   cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)
            );
            Log.d(TAG, "onCreate: "+cursor);
            itemIds.add(itemId);
        }
        cursor.close();

        db1.close();

        /*// Define 'where' part of query.
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { "MyTitle" };
// Issue SQL statement.
        db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs)*/

    }
}
