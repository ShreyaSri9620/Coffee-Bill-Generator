package com.example.dell.coffeebillgenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int counter=1;//this variable specifies the number of coffees ordered
    int price=0;//this variable specifies the price of each coffee

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //increments the value by one
    public void increment(View view){
        counter++;
        if(counter > 30) {
            counter = 30;
            display(counter);
            Toast toast = Toast.makeText(this,"Cannot Order More Than 30 Cups of Coffee",Toast.LENGTH_SHORT);
            toast.show();
        }
        display(counter);
    }


    //increments the value by one
    public void decrement(View view){
        counter--;
        if(counter < 1) {
            counter = 1;
            display(counter);
            Toast toast = Toast.makeText(this, "Cannot Order Less Than 1 Cup of Coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
        display(counter);
    }
    /**
     This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        price = 5;
        String priceMessage = "";
        EditText name = (EditText) findViewById(R.id.userName);
        EditText email = (EditText) findViewById(R.id.userEmail);
        //String priceMessage = "Total: $" + (counter * 5);
        //priceMessage = priceMessage +"\nThank You!";
        //String priceMessage=createOrderSummary(counter*5);

        CheckBox ob1 = (CheckBox) findViewById(R.id.chk);
        CheckBox ob2 = (CheckBox) findViewById(R.id.chk1);

        if(ob1.isChecked())
            price=price+1;

        if(ob2.isChecked())
            price=price+2;

        priceMessage = createOrderSummary(counter * price, ob1.isChecked(),ob2.isChecked(),name.getText().toString());

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL,email.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order for "+ name.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage+"\nOrder Placed successfully!"+"\n\nKindly Pay Immediately at the counter and collect the receipt in order to collect the coffee from the install");
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayMessage(priceMessage);
    }

    public String createOrderSummary(int pr,boolean value1,boolean value2,String customer){
        String msg="Name: "+ customer +"\nAdd Whipped Cream? "+value1 +"\nAdd Chocolate? "+ value2+"\nQuantity: " + counter + "\nTotal: $" + pr + "\nThank You!";
        return msg;
    }

    /**
     * This method displays the given text on the screen.
     */

    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

    /**
     This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given price on the screen.
     */
    /*private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        if (number >= 0) {
            priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));//this statement display the price along with the currency
        }
        else{
            priceTextView.setText(NumberFormat.getCurrencyInstance().format(0));
        }
    }
*/
}


