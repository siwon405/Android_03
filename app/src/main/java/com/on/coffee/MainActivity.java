
package com.on.coffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final int PRICE_COFFEE =2500;
    private final int PRICE_WHIP =700;
    private final int PRICE_CHOCOLATE =600;

    private int mQuantity = 0;
    private TextView mPriceTextView;
    private TextView mQuantityTextView;
    private CheckBox mWhipCheckBox;
    private CheckBox mChocolateCheckBox;
    private CheckBox mMailCheckBox;
    private View mNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = findViewById(R.id.price_text_view);
        mQuantityTextView = findViewById(R.id.quantity_text_view);
        mWhipCheckBox = findViewById(R.id.whip_checkbox);
        mChocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        mMailCheckBox = findViewById(R.id.mail_checkbox);
        mNameEditText = findViewById(R.id.name_edit_text);
    }

    /**
     * 주문 버튼 이벤트 처리
     * @param view
     */
    public void submitOrder(View view){
       // Log.d(TAG,"버튼입니다.");
        //display(mQuantity);
        //displayPrice(PRICE_COFFEE * mQuantity);
        String name = "Name : kitty";
        String whip = "Add whipped cream? " + mWhipCheckBox.isChecked();
        String chocolate = "Add whipped cream? " + mChocolateCheckBox.isChecked();
        String quantity = "Quantity : " + mQuantity;

        int price = PRICE_COFFEE * mQuantity;

        if (mWhipCheckBox.isChecked()){
            price += mQuantity * PRICE_WHIP;
        }
        if (mChocolateCheckBox.isChecked()){
            price += mQuantity * PRICE_CHOCOLATE;
        }

        String formattedPrice = "Total : " + price + " 원";
        String message =name + "\n" +
                whip + "\n" +
                chocolate + "\n" +
                quantity + "\n" +
                price + " won" + "\n" + "\n" +
                getString(R.string.thank_you);

        displayMessage(message);

        //OK_COFFEE_HELLMONEY전화걸기 예제
//        Uri uri = Uri.parse("tel:01046990059");
//        Intent intent = new Intent(Intent.ACTION_CALL,uri);
//        startActivity(intent);

        if (mMailCheckBox.isChecked()){
            String[] addresses = new String[] {"on.siwon@gmail.com"};
            composeEmail(addresses, "커피주문 test",null, message);
        }else {
            Toast.makeText(this, price  + " 원 결제 완료", Toast.LENGTH_SHORT).show();
        }


    }

    public void composeEmail(String[] addresses, String subject, Uri attachment,String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//    public void dialPhoneNumber(String phoneNumber) {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:" + phoneNumber));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
    public void increment(View view){
        mQuantity++;

        if (mQuantity > 9){
            mQuantity = 9;
            Toast.makeText(getApplicationContext(), "주문수량 10잔 초과시, 바리스타의 안내가 필요합니다", Toast.LENGTH_SHORT).show();
        }
        display(mQuantity);
        displayPrice(PRICE_COFFEE * mQuantity);
    }
    public void decrement(View view) {
        mQuantity--;

        if (mQuantity <0){
            mQuantity = 0;
            Toast.makeText(MainActivity.this, "최소 주문수량, 1잔 입니다", Toast.LENGTH_SHORT).show();
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