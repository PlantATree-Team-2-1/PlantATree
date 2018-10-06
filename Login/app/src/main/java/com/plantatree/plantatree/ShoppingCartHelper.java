package com.plantatree.plantatree;

import java.util.List;
import java.util.Vector;

import android.content.res.Resources;
import com.stream53.plantatree.plantatree.R;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static List<Product> cart;

    public static List<Product> getCatalog(Resources res){

        if(catalog == null) {
            catalog = new Vector<Product>();
            catalog.add(new Product("Palm Tree", res
                    .getDrawable(R.drawable.palm_tree),
                    "Palm Trees are big", 29.99));
            catalog.add(new Product("Oak Tree", res
                    .getDrawable(R.drawable.oak_tree),
                    "Oak Trees are really big", 74.99));
            catalog.add(new Product("Kauri Tree", res
                    .getDrawable(R.drawable.kauri_tree),
                    "Wtf? Is that even a tree?", 154.99));
        }

        return catalog;
    }

    public static List<Product> getCart() {
        if(cart == null) {
            cart = new Vector<Product>();
        }

        return cart;
    }

}
