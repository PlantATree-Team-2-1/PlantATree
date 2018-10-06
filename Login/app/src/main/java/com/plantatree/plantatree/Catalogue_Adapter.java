package com.plantatree.plantatree;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream53.plantatree.plantatree.R;

public class Catalogue_Adapter extends BaseAdapter {

    private List<Catalog_Product> mCatalogProductList;
    private LayoutInflater mInflater;
    private boolean mShowQuantity;

    public Catalogue_Adapter(List<Catalog_Product> list, LayoutInflater inflater, boolean showQuantity) {
        mCatalogProductList = list;
        mInflater = inflater;
        mShowQuantity = showQuantity;
    }

    @Override
    public int getCount() {
        return mCatalogProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCatalogProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewItem item;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);
            item = new ViewItem();

            item.productImageView = (ImageView) convertView
                    .findViewById(R.id.ImageViewItem);

            item.productTitle = (TextView) convertView
                    .findViewById(R.id.TextViewItem);

            item.productQuantity = (TextView) convertView
                    .findViewById(R.id.textViewQuantity);

            convertView.setTag(item);
        } else {
            item = (ViewItem) convertView.getTag();
        }

        Catalog_Product curCatalogProduct = mCatalogProductList.get(position);

        item.productImageView.setImageDrawable(curCatalogProduct.productImage);
        item.productTitle.setText(curCatalogProduct.title);

        // Show the quantity in the cart or not
        if (mShowQuantity) {
            item.productQuantity.setText("Quantity: "
                    + Shopping_Details.getProductQuantity(curCatalogProduct));
        } else {
            // Hid the view
            item.productQuantity.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewItem {
        ImageView productImageView;
        TextView productTitle;
        TextView productQuantity;
    }

}

