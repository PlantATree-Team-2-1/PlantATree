package com.plantatree.plantatree;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import java.text.DecimalFormat;

import com.stream53.plantatree.plantatree.R;

public class Shopping_Cart extends AppCompatActivity {

    TextView productPriceTextView;
    private List<Catalogue_Product> mCartList;
    private Catalogue_Adapter mProductAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_Catalogue) {

            Intent startTopic1 = new Intent(this, Catalogue_Activity.class);
            startActivity(startTopic1);

        }
        if (id == R.id.menu_Cart) {

            Intent startTopic1 = new Intent(this, Shopping_Cart.class);
            startActivity(startTopic1);

        }
        if (id == R.id.menu_Quiz) {

            Intent startTopic1 = new Intent(this, Quiz_Start.class);
            startActivity(startTopic1);

        }
        if (id == R.id.menu_Compare) {

            Intent startTopic1 = new Intent(this, Image_Drag.class);
            startActivity(startTopic1);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        mCartList = Catalogue_Helper.getCartList();

        // Make sure to clear the selections
        for (int i = 0; i < mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new Catalogue_Adapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            //TODO: ISSUE HERE WITH ITEMS IN CATALOGUE SENDING TO WRONG ITEM WHEN CLICKED

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent productDetailsIntent = new Intent(getBaseContext(), Catalogue_Details.class);
                productDetailsIntent.putExtra(Catalogue_Helper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        final Button buttonPayment = (Button) findViewById(R.id.button_checkout);
        buttonPayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkout();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if (mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }

        double subTotal = 0;
        subTotal =Double.parseDouble(new DecimalFormat("00.00").format(subTotal));

        for (Catalogue_Product p : mCartList) {
            int quantity = Catalogue_Helper.getProductQuantity(p);
            subTotal += p.price * quantity;
        }

        productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + subTotal);

        if(Quiz_Activity.quizDone == true){

            double a;

            a = subTotal-((Quiz_Activity.scoreCart/100)*subTotal);

            TextView textView = (TextView) findViewById(R.id.TextViewSubtotal);
            textView.setText("Subtotal: $ DISCOUNT" + a);
        }
    }

    public void checkout(){

        //redirects the cart activity to checkout.
        Intent intent = new Intent(Shopping_Cart.this, Pay_Shipping.class);
        startActivityForResult(intent, 1);
    }
}

