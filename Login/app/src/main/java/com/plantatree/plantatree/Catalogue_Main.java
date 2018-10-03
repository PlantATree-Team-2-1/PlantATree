package com.plantatree.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.stream53.plantatree.plantatree.R;

public class Catalogue_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
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
        /*if(id == R.id.menu_About){

            Intent startTopic1 = new Intent (this, Topic1.class);
            startActivity(startTopic1);

        }*/

        return super.onOptionsItemSelected(item);
    }
}
