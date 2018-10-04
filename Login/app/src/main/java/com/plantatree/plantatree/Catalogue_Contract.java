package com.plantatree.plantatree;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

public class Catalogue_Contract {

    private Catalogue_Contract() {
    }

    public static final String CONTENT_AUTHORITY = "com.delaroystudios.treecart";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TREE = "Catalogue_Tree-path";
    public static final String PATH_CART = "cart-path";

    public static final class treeEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TREE);
        public static final Uri CONTENT_URI_CART = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CART);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TREE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TREE;
        public final static String _ID = BaseColumns._ID;
        public final static String _CARTID = BaseColumns._ID;

        /*
        Tree Database Names
         */

        public final static String TABLE_NAME = "trees";
        public final static String CART_TABLE = "cart";
        public final static String COLUMN_NAME = "treename";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_IMAGE = "imageurl";
        public final static String COLUMN_PRICE = "price";

        public final static String COLUMN_CART_NAME = "carttreename";
        public final static String COLUMN_CART_IMAGE = "cartimageurl";
        public final static String COLUMN_CART_QUANTITY = "cartquantity";
        public final static String COLUMN_CART_TOTAL_PRICE = "carttotalprice";

    }
}