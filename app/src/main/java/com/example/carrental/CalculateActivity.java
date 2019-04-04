package com.example.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class CalculateActivity extends AppCompatActivity {

     private ImageView imageView;
     private TextView title;
     private TextView display;
    public NumberFormat money = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        imageView = (ImageView) findViewById(R.id.imageID);
        title = (TextView) findViewById(R.id.titleID);
        display = (TextView) findViewById(R.id.total_display);

        // declare
        double month, down, rating, amount , total;
        Intent clear = getIntent();
        if(clear != null){
            Bundle params = clear.getExtras();
            if(params != null){
                month = params.getDouble("months");
                down = params.getDouble("downs");
                rating = params.getDouble("rates");
                amount = params.getDouble("amounts");

                total = (rating/100*down)*(month/amount);
                display.setText(money.format(total));
            }
        }


    }
}
