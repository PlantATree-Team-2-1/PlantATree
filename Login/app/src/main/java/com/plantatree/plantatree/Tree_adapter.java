package com.plantatree.plantatree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream53.plantatree.plantatree.R;

import static com.plantatree.plantatree.Tree_detail.TREE_DESCRIPTION;
import static com.plantatree.plantatree.Tree_detail.TREE_IMAGE;
import static com.plantatree.plantatree.Tree_detail.TREE_NAME;
import static com.plantatree.plantatree.Tree_detail.TREE_PRICE;

public class Tree_adapter extends RecyclerView.Adapter<Tree_adapter.ViewHolder> {

    Cursor dataCursor;
    Context context;
    int id;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, userrating, description, price;
        public ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, Tree_detail.class);
                        intent.putExtra(TREE_NAME, getItem(pos).name);
                        intent.putExtra(TREE_DESCRIPTION, getItem(pos).description);
                        intent.putExtra(TREE_PRICE, getItem(pos).price);
                        intent.putExtra(TREE_IMAGE, getItem(pos).imageUrl);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    public Tree_adapter(Activity mContext, Cursor cursor) {
        dataCursor = cursor;
        context = mContext;
    }

    @Override
    public Tree_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_views, parent, false);
        return new ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(Tree_adapter.ViewHolder holder, int position) {


        dataCursor.moveToPosition(position);

        id = dataCursor.getInt(dataCursor.getColumnIndex(Catalogue_Contract.treeEntry._ID));
        String mName = dataCursor.getString(dataCursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_NAME));
        String mDescription = dataCursor.getString(dataCursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_DESCRIPTION));
        String mImageUrl = dataCursor.getString(dataCursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_IMAGE));
        int mPrice = dataCursor.getInt(dataCursor.getColumnIndex(Catalogue_Contract.treeEntry.COLUMN_PRICE));





    }


    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }

    public Catalogue_Tree getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            throw new IllegalArgumentException("Item position is out of adapter's range");
        } else if (dataCursor.moveToPosition(position)) {
            return new Catalogue_Tree(dataCursor);
        }
        return null;
    }
}
