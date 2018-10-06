package com.plantatree.plantatree;

import java.util.List;

import android.app.Activity;
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

public class ShoppingCartActivity extends AppCompatActivity {

    private List<Product> mCartList;
    private Product_Adapter mProductAdapter;

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

            Intent startTopic1 = new Intent (this, CatalogActivity.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Cart){

            Intent startTopic1 = new Intent (this, ShoppingCartActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        mCartList = ShoppingCartHelper.getCart();

        // Make sure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new Product_Adapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product selectedProduct = mCartList.get(position);
                if(selectedProduct.selected == true) { selectedProduct.selected = false; }
                else { selectedProduct.selected = true; }

                mProductAdapter.notifyDataSetInvalidated();

            }
        });

        Button removeButton = (Button) findViewById(R.id.ButtonRemoveFromCart);
        removeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Same process, however working backwards to complete
                a remove action
                 */

                for(int i=mCartList.size()-1; i>=0; i--) {

                    if(mCartList.get(i).selected) {
                        mCartList.remove(i);
                    }
                }
                mProductAdapter.notifyDataSetChanged();
            }
        });

    }

}
