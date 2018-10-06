package com.plantatree.plantatree;

public class Shopping_Returns {

    private Catalog_Product mCatalogProduct;
    private int mQuantity;

    public Shopping_Returns(Catalog_Product catalogProduct, int quantity) {
        mCatalogProduct = catalogProduct;
        mQuantity = quantity;
    }

    public Catalog_Product getProduct() {
        return mCatalogProduct;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

}
