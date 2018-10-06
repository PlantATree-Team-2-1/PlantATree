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
                    "The Arecaceae are a botanical family of perennial plants. Their growth form can be climbers, shrubs, trees and stemless plants, all commonly known as palms. Those having a tree form are colloquially called palm trees.", 29.99));
            catalog.add(new Product("Oak Tree", res
                    .getDrawable(R.drawable.oak_tree),
                    "An oak is a tree or shrub in the genus Quercus of the beech family, Fagaceae. There are approximately 600 extant species of oaks. The common name \"oak\" also appears in the names of species in related genera, notably Lithocarpus, as well as in those of unrelated species such as Grevillea robusta and the Casuarinaceae.", 74.99));
            catalog.add(new Product("Kauri Tree", res
                    .getDrawable(R.drawable.kauri_tree),
                    "Agathis australis, commonly known by its Māori name kauri, is a coniferous tree of Araucariaceae in the genus Agathis, found north of 38°S in the northern districts of New Zealand's North Island.", 154.99));
            catalog.add(new Product("Bay Tree", res
                    .getDrawable(R.drawable.bay_tree),
                    "The bay tree is a popular evergreen shrub suitable for containers or growing in the ground.", 12.99));
            catalog.add(new Product("Cabbage Tree", res
                    .getDrawable(R.drawable.cabbage_tree),
                    "Cordyline australis, commonly known as the cabbage tree, cabbage-palm or tī kōuka, is a widely branched monocot tree endemic to New Zealand. It grows up to 20 metres tall with a stout trunk and sword-like leaves, which are clustered at the tips of the branches and can be up to 1 metre long.", 8.99));
            catalog.add(new Product("Plant pot", res
                    .getDrawable(R.drawable.plant_pot),
                    "Plant pot is a container in which flowers and other plants are cultivated and displayed", 4.99));
            catalog.add(new Product("Spade", res
                    .getDrawable(R.drawable.spade),
                    "A tool with a sharp-edged, typically rectangular, metal blade and a long handle, used for digging or cutting earth, sand, turf, etc.", 24.99));

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
