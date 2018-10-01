package com.plantatree.plantatree;

import android.database.Cursor;


public class tree {

    public int id;

    public String name;
    public String description;
    public String imageUrl;
    public Double price;

    public tree(Cursor cursor) {
        this.name = cursor.getString(cursor.getColumnIndex(treeContract.treeEntry.COLUMN_NAME));
        this.description = cursor.getString(cursor.getColumnIndex(treeContract.treeEntry.COLUMN_DESCRIPTION));
        this.imageUrl = cursor.getString(cursor.getColumnIndex(treeContract.treeEntry.COLUMN_IMAGE));
        this.price = cursor.getDouble(cursor.getColumnIndex(treeContract.treeEntry.COLUMN_PRICE));
    }
}
