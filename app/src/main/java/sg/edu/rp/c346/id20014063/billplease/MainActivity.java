package sg.edu.rp.c346.id20014063.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView amount;
    TextView pax;
    ToggleButton SVS;
    ToggleButton GST;
    EditText discount;
    RadioGroup extra;
    RadioButton cash;
    RadioButton paynow;
    Button split;
    Button reset;
    TextView bill;
    TextView pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextAmount);
        pax = findViewById(R.id.editTextPax);
        SVS = findViewById(R.id.toggleButtonSVS);
        GST = findViewById(R.id.toggleButtonGST);
        discount = findViewById(R.id.editTextDiscount);
        extra = findViewById(R.id.extra);
        cash = findViewById(R.id.radioButtonCash);
        paynow = findViewById(R.id.radioButtonPayNow);
        split = findViewById(R.id.buttonSPLIT);
        reset = findViewById(R.id.buttonRESET);
        bill = findViewById(R.id.textViewBill);
        pay = findViewById(R.id.textViewPay);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double userInput = Double.parseDouble(amount.getText().toString());

                if(!SVS.isChecked() && !GST.isChecked()){
                    userInput = userInput;
                }
                else if (SVS.isChecked() && !GST.isChecked()){
                    userInput = userInput*1.1;
                }
                else if(!SVS.isChecked() && GST.isChecked()){
                    userInput = userInput*1.07;
                }
                else{
                    userInput = userInput*1.17;
                }

                Double discountamt = Double.parseDouble(discount.getText().toString());
                Double total = userInput * ((100-discountamt)/100);

                Double paxamt = Double.parseDouble(pax.getText().toString());
                Double finalamt = total/paxamt;


                bill.setText("Total Bill: $" + String.format("%.2f",total));

                if(extra.getCheckedRadioButtonId() == R.id.radioButtonCash){
                    pay.setText("Each Pays: $" + String.format("%.2f in cash", finalamt));
                }
                else if(extra.getCheckedRadioButtonId() == R.id.radioButtonPayNow){
                    pay.setText("Each Pays: $" + String.format("%.2f via PayNow to 1234 5678", finalamt));
                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("0");
                pax.setText("0");
                SVS.setChecked(false);
                GST.setChecked(false);
                discount.setText("0");
                cash.setChecked(true);

            }
        });
    }
}