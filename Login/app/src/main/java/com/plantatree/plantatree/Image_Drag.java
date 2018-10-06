package com.plantatree.plantatree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.stream53.plantatree.plantatree.R;

public class Image_Drag extends AppCompatActivity {

    private int xCord;
    private int yCord;
    private ImageView image;
    private ViewGroup dragLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_drag);

        dragLayout = (ViewGroup) findViewById(R.id.view_root);
        image = (ImageView) dragLayout.findViewById(R.id.imageView);

        //provides the images parameters, having a width 150 and height of 150
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        image.setLayoutParams(layoutParams);
        image.setOnTouchListener(new DragNDropListener());
    }

    private final class DragNDropListener implements OnTouchListener {

        public boolean onTouch(View view, MotionEvent event) {

            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            //Controls the location of the image dependent on users desired location
            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    xCord = X - lParams.leftMargin;
                    yCord = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                            .getLayoutParams();
                    layoutParams.leftMargin = X - xCord;
                    layoutParams.topMargin = Y - yCord;
                    layoutParams.rightMargin = -150;
                    layoutParams.bottomMargin = -150;
                    view.setLayoutParams(layoutParams);
                    break;
            }
            dragLayout.invalidate();
            return true;
        }
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
}


