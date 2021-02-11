
package com.on.coffee;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final int PRICE_COFFEE =2500;
    private final int PRICE_WHIP =500;

    private int mQuantity = 0;
    private TextView mPriceTextView;
    private TextView mQuantityTextView;
    private CheckBox mToppingCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = findViewById(R.id.price_text_view);
        mQuantityTextView = findViewById(R.id.quantity_text_view);
        mToppingCheckBox = findViewById(R.id.topping_checkbox);
    }
    public void submitOrder(View view){
       // Log.d(TAG,"버튼입니다.");
        //display(mQuantity);
        //displayPrice(PRICE_COFFEE * mQuantity);
        String name = "Name : kitty";
        String topping = "Add whipped cream? " + mToppingCheckBox.isChecked();
        String quantity = "Quantity : " + mQuantity;

        int price = PRICE_COFFEE * mQuantity;

        if (mToppingCheckBox.isChecked()){
            price += mQuantity * PRICE_WHIP;
        }

        String formattedPrice = "Total : " + price + " 원";
        String message =name + "\n" +
                topping + "\n" +
                quantity + "\n" +
                price + " won" + "\n" +
                getString(R.string.thank_you);

        displayMessage(message);
    }
    public void increment(View view){
        mQuantity++;
        display(mQuantity);
        displayPrice(PRICE_COFFEE * mQuantity);
    }
    public void decrement(View view) {
        mQuantity--;

        if (mQuantity <0){
            mQuantity = 0;
        }
        display(mQuantity);
        displayPrice(PRICE_COFFEE * mQuantity);
    }
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format( number));
        mPriceTextView.setText("" + number + " won");
    }
    private void displayMessage(String message){
        mPriceTextView.setText(message);
    }


}