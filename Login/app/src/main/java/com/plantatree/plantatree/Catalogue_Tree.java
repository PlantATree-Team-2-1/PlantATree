package com.plantatree.plantatree;

import android.database.Cursor;


public class Catalogue_Tree {

    public int id;

    public String name;
    public String description;
    public String imageUrl;
    public Double price;

    public Catalogue_Tree(Cursor cursor) {
        this.name = cursor.getString(cursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_NAME));
        this.description = cursor.getString(cursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_DESCRIPTION));
        this.imageUrl = cursor.getString(cursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_IMAGE));
        this.price = cursor.getDouble(cursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_PRICE));
    }
}
