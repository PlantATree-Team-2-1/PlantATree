package com.plantatree.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.stream53.plantatree.plantatree.R;

import java.security.KeyStore;


public class Catalogue_Main extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


        private RecyclerView recyclerView;
        Tree_adapter Tree_adapter;
        private static final int TREE_LOADER = 0;
        DBHelper_Tree DBHelper_Tree;
        private SQLiteDatabase mDb;

        private int mNotificationsCount = 0;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_catalogue);
            DBHelper_Tree dbHelper = new DBHelper_Tree(this);
            mDb = dbHelper.getWritableDatabase();


            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            }

            Tree_adapter = new Tree_adapter(this, null);
            recyclerView.setAdapter(Tree_adapter);

            getLoaderManager().initLoader(TREE_LOADER,null,this);

        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            // Define a projection that specifies the columns from the table we care about.
            String[] projection = {
                    Catalogue_Contract.treeEntry._ID,
                    Catalogue_Contract.treeEntry.COLUMN_NAME,
                    Catalogue_Contract.treeEntry.COLUMN_DESCRIPTION,
                    Catalogue_Contract.treeEntry.COLUMN_IMAGE,
                    Catalogue_Contract.treeEntry.COLUMN_PRICE,
            };

            // This loader will execute the ContentProvider's query method on a background thread
            return new CursorLoader(this,   // Parent activity context
                    Catalogue_Contract.treeEntry.CONTENT_URI,   // Provider content URI to query
                    projection,             // Columns to include in the resulting Cursor
                    null,                   // No selection clause
                    null,                   // No selection arguments
                    null);                  // Default sort order
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            Tree_adapter.swapCursor(data);

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

            Tree_adapter.swapCursor(null);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_Catalogue){

            Intent startTopic1 = new Intent (this, Catalogue_Main.class);
            startActivity(startTopic1);

        }
        /*if(id == R.id.menu_Cart){

            Intent startTopic1 = new Intent (this, Topic1.class);
            startActivity(startTopic1);

        }*/
        if(id == R.id.menu_Quiz){

            Intent startTopic1 = new Intent (this, Quiz_Start.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Compare){

            Intent startTopic1 = new Intent (this, Image_Drag.class);
            startActivity(startTopic1);

        }

        return super.onOptionsItemSelected(item);
    }
}
