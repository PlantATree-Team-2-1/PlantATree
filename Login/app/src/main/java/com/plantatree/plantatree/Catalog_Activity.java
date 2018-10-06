package com.plantatree.plantatree;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.stream53.plantatree.plantatree.R;

public class Catalog_Activity extends AppCompatActivity {

    private List<Catalog_Product> PRODUCT_LIST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*inflater variable, reads the chosen xml file and
        * creates the corresponding objects to it*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        /*Redirects the user depending where they are wanting to go
        * e.g Catalogue triggers, redirects the activity the catalog_activity
        * where the elected layout is displayed for that class*/

        if(id == R.id.menu_Catalogue){

            Intent startTopic1 = new Intent (this, Catalog_Activity.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Cart){

            Intent startTopic1 = new Intent (this, Shopping_Cart.class);
            startActivity(startTopic1);

        }
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

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Will be created when class is called upon first.
        setContentView(R.layout.activity_catalogue);

        //References an object from the product catalog
        PRODUCT_LIST = Shopping_Details.getCatalog(getResources());








        // Create the list
        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);


        listViewCatalog.setAdapter(new Catalogue_Adapter(PRODUCT_LIST, getLayoutInflater(), false));



        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),Catalogue_Details.class);
                productDetailsIntent.putExtra(Shopping_Details.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
        viewShoppingCart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), Shopping_Cart.class);
                startActivity(viewShoppingCartIntent);
            }
        });

    }
}
