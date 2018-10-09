package com.plantatree.plantatree;

public class ShoppingCartEntry {

    private Catalogue_Product mCatalogProduct;
    private int mQuantity;

    public ShoppingCartEntry(Catalogue_Product catalogProduct, int quantity) {
        mCatalogProduct = catalogProduct;
        mQuantity = quantity;
    }

    public Catalogue_Product getProduct() {
        return mCatalogProduct;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

}
