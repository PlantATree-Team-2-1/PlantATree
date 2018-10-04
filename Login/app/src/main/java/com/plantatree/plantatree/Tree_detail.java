package com.plantatree.plantatree;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.stream53.plantatree.plantatree.R;

public class Tree_detail extends AppCompatActivity {

    public static final String  TREE_NAME = "treeName";
    public static final String  TREE_DESCRIPTION = "treeDescription";
    public static final String  TREE_IMAGE = "treeImage";
    public static final String  TREE_PRICE = "treePrice";

    private ImageView mImage;


    String treeName, description, treeImage;
    int rating;
    Double price;
    private int mQuantity = 1;
    private double mTotalPrice;
    String imagePath;
    TextView costTextView;
    ContentResolver mContentResolver;
    private SQLiteDatabase mDb;

    private int mNotificationsCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_description );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContentResolver = this.getContentResolver();
        DBHelper_Tree dbHelper = new DBHelper_Tree(this);
        mDb = dbHelper.getWritableDatabase();


        mImage = (ImageView) findViewById(R.id.treeImage);
        Intent intentThatStartedThisActivity = getIntent();

        costTextView = (TextView) findViewById(
                R.id.cost_text_view);

        if (intentThatStartedThisActivity.hasExtra(TREE_NAME)) {
            treeName = getIntent().getExtras().getString(TREE_NAME);
            description = getIntent().getExtras().getString(TREE_DESCRIPTION);
            treeImage = getIntent().getExtras().getString(TREE_IMAGE);
            price = getIntent().getExtras().getDouble(TREE_PRICE);

            TextView desc = (TextView) findViewById(R.id.description);
            desc.setText(description);

            TextView Price = (TextView) findViewById(R.id.price);
            DecimalFormat precision = new DecimalFormat("0.00");
            Price.setText("$" + precision.format(price));

            float f = Float.parseFloat(Double.toString(rating));

            setTitle(treeName);
        }


        if (mQuantity == 1){

            mTotalPrice = price;
            displayCost(mTotalPrice);
        }

    }

    public void increment(View view){

        price = getIntent().getExtras().getDouble(TREE_PRICE);
        mQuantity = mQuantity + 1;
        displayQuantity(mQuantity);
        mTotalPrice = mQuantity * price;
        displayCost(mTotalPrice);
    }

    public void decrement(View view){
        if (mQuantity > 1){

            mQuantity = mQuantity - 1;
            displayQuantity(mQuantity);
            mTotalPrice = mQuantity * price;
            displayCost(mTotalPrice);

        }
    }

    private void displayQuantity(int numberOfItems) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfItems));
    }

    private void displayCost(double totalPrice) {

        String convertPrice = NumberFormat.getCurrencyInstance().format(totalPrice);
        costTextView.setText(convertPrice);
    }
}
