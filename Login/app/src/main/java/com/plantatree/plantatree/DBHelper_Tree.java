package com.plantatree.plantatree;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.stream53.plantatree.plantatree.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBHelper_Tree extends SQLiteOpenHelper {
    private static final String TAG = DBHelper_Tree.class.getSimpleName();

    private static final String DATABASE_NAME = "Catalogue_Tree.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;
    ContentResolver mContentResolver;

    private Resources mResources;

    public DBHelper_Tree(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
        mContentResolver = context.getContentResolver();

        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TREE_TABLE = "CREATE TABLE " + Catalogue_Contract.treeEntry.TABLE_NAME + " (" +
                Catalogue_Contract.treeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Catalogue_Contract.treeEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                Catalogue_Contract.treeEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                Catalogue_Contract.treeEntry.COLUMN_IMAGE + " REAL NOT NULL " + " );";



        db.execSQL(SQL_CREATE_TREE_TABLE);
        Log.d(TAG, "Database Created Successfully" );


        try {
            readtreesFromResources(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Catalogue_Contract.treeEntry.TABLE_NAME);
        onCreate(db);
    }


    private void readtreesFromResources(SQLiteDatabase db) throws IOException, JSONException {
        StringBuilder builder = new StringBuilder();
        InputStream in = mResources.openRawResource(R.raw.trees);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }

        final String rawJson = builder.toString();

        final String BGS_TREE = "Catalogue_Tree";

        final String BGS_TREE_NAME = "treeName";
        final String BGS_DESCRIPTION = "description";
        final String BGS_IMAGEURL = "imageUrl";
        final String BGS_PRICE = "price";

        try {
            JSONObject treeJson = new JSONObject(rawJson);
            JSONArray treeArray = treeJson.getJSONArray(BGS_TREE);


            for (int i = 0; i < treeArray.length(); i++) {

                String treeName;
                String description;
                String imageUrl;
                Double price;

                JSONObject treeDetails = treeArray.getJSONObject(i);

                treeName = treeDetails.getString(BGS_TREE_NAME);
                description = treeDetails.getString(BGS_DESCRIPTION);
                imageUrl = treeDetails.getString(BGS_IMAGEURL);
                price = treeDetails.getDouble(BGS_PRICE);


                ContentValues treeValues = new ContentValues();

                treeValues.put(Catalogue_Contract.treeEntry.COLUMN_NAME, treeName);
                treeValues.put(Catalogue_Contract.treeEntry.COLUMN_DESCRIPTION, description);
                treeValues.put(Catalogue_Contract.treeEntry.COLUMN_IMAGE, imageUrl);
                treeValues.put(Catalogue_Contract.treeEntry.COLUMN_PRICE, price);

                db.insert(Catalogue_Contract.treeEntry.TABLE_NAME, null, treeValues);

                Log.d(TAG, "Inserted Successfully " + treeValues );
            }

            Log.d(TAG, "Inserted Successfully " + treeArray.length() );

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }
    }


}
