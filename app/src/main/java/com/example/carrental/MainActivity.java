package com.example.carrental;



import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.text.NumberFormat;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    private TextView check_title;
    private TextInputLayout duration;
    private TextInputLayout down_payment;
    private TextInputLayout lease;
    private TextInputLayout car_amount;
    private Button next;
    private TextView testing;
    public NumberFormat money = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.v_flipper);

        int images[] = {R.drawable.car1, R.drawable.car3, R.drawable.car4};

        for (int image : images) {
            flipperImages(image);
        }

        check_title = (TextView) findViewById(R.id.checkID);
        duration = (TextInputLayout) findViewById(R.id.duration_monthID);
        down_payment = (TextInputLayout) findViewById(R.id.down_paymentID);
        lease = (TextInputLayout) findViewById(R.id.leaseID);
        car_amount = (TextInputLayout) findViewById(R.id.car_valueID);
        testing = (TextView) findViewById(R.id.test);
        next = (Button) findViewById(R.id.nextID);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(duration.getEditText().getText().toString(), down_payment.getEditText().getText().toString(), lease.getEditText().getText().toString(), car_amount.getEditText().getText().toString());

                //calculation for monthly car rent
                String month = duration.getEditText().getText().toString();
                String down = down_payment.getEditText().getText().toString();
                String rate = lease.getEditText().getText().toString();
                String amount = car_amount.getEditText().getText().toString();

                try {
                   //transition to the next Activity and passing data through intent action
                Intent intent = new Intent(getApplicationContext(), CalculateActivity.class);
                Bundle params = new Bundle();
                params.putDouble("months", Double.valueOf(month));
                params.putDouble("downs", Double.valueOf(down));
                params.putDouble("rates", Double.valueOf(rate));
                params.putDouble("amounts", Double.valueOf(amount));
                intent.putExtras(params);

                startActivity(intent);

                } catch (NumberFormatException nfe) {
                    //pop up an alert view here
                }



            }
        });
        //odd codes
           /*
        double monthly = Double.parseDouble(month);
        double downthly = Double.parseDouble(down);
        double rately = Double.parseDouble(rate);
        double amountly = Double.parseDouble(amount);
        double total = 0.0;

        total = (rately / 100 * amountly) * (monthly / downthly);
        testing.setText(money.format(total));
          */

    }

    private void calculate(String duration_month, String payment, String leases, String amount) {

        if (duration_month.isEmpty() && payment.isEmpty() && leases.isEmpty() && amount.isEmpty()) {
            duration.setError("Field cant be empty");
            down_payment.setError("Field cant be empty");
            lease.setError("Field cant be empty");
            car_amount.setError("Field cant be empty");
        }else{
            duration.setError(null);
            down_payment.setError(null);
            lease.setError(null);
            car_amount.setError(null);
            next.setEnabled(true);
        }

    }
    private void flipperImages(int image) {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        //animation

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }
}
