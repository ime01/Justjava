
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText textField = (EditText)findViewById(R.id.edit_text);
        String  value =textField.getText().toString();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("Mainactivity","Has Whipped Cream:"+hasWhippedCream);

        CheckBox addChocolate =(CheckBox) findViewById(R.id.chocklate_checkbox) ;
        boolean heOrderedChocolate = addChocolate.isChecked();
        Log.v("Mainactivity", "he ordered chocolate:"+ heOrderedChocolate);

        displayQuantity(quantity);
        int price = calculatePrice( hasWhippedCream, heOrderedChocolate);
        String PriceMessage = createOrderSummary( value, price, hasWhippedCream,heOrderedChocolate);

        Intent emailintent = new Intent (Intent.ACTION_SENDTO);
        emailintent.setData(Uri.parse("mailto:"));
        emailintent.putExtra(Intent.EXTRA_EMAIL, "ketuimevbore@yahoo.com");
        emailintent.putExtra(Intent.EXTRA_SUBJECT, "just java coffee app testing");
        emailintent.putExtra(Intent.EXTRA_TEXT, PriceMessage);
        if (emailintent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailintent);
        }


    }


    public void increment(View view) {
        if (quantity ==100){
            //show error message as a toast
            Toast.makeText(this, "you cannot have more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity ==1){
            //show error message as a toast
            Toast.makeText(this, "you cannot have more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            return;
        } quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * Calculates the price of the order.
     * @param addWhippedCream is weather or not the user wants whipped cream topping
     * @param quantity is the number of cups of coffee ordered
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean plusChocolate) {
        //price of one cup of coffee
        int basePrice = 5;

        //Add 1 dollar if user wants whippedCream
        if (addWhippedCream ) {
        basePrice = basePrice + 1;
        }
        //Add 2 dollars for chocklate
        if (plusChocolate) {
            basePrice = basePrice + 2;
        }
        //calculate total order price by multipling with quantity
        int price = quantity * basePrice;
        return price;
    }

    /**
     * this method returns text summary
     */

    private String createOrderSummary (String value, int price, boolean addWhippedCream, boolean plusChocolate){
        String PriceMessage = getString(R.string.order_summary_name) + value;
        PriceMessage += "\nadd Whipp Cream ?" + addWhippedCream;
        PriceMessage += "\nadd addChocklate ?" + plusChocolate;
        PriceMessage += "\nQuantity: "+ quantity;
        PriceMessage += "\nTotal: $" + price;
        PriceMessage += "\n" + getString(R.string.thank_you);
        return PriceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given text on the screen.
     */

}


