package com.example.ridz.payumoneywebview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PAYU_REQUEST_CODE =100 ;
    EditText fname, pnumber, emailAddress, rechargeAmt,invoice;
    Button Paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname        = (EditText)findViewById(R.id.fname);
        pnumber      = (EditText)findViewById(R.id.pnumber);
        emailAddress = (EditText)findViewById(R.id.emailAddress);
        rechargeAmt  = (EditText)findViewById(R.id.rechargeAmt);
        rechargeAmt  = (EditText)findViewById(R.id.rechargeAmt);
        invoice  = (EditText)findViewById(R.id.invoice);
        Paynow       = (Button)findViewById(R.id.Paynow);

        Paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getFname = fname.getText().toString().trim();
                String getPhone = pnumber.getText().toString().trim();
                String getEmail = emailAddress.getText().toString().trim();
                String getAmt   = rechargeAmt.getText().toString().trim();
                String getInvoice   = invoice.getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), PayMentGateWay.class);
                intent.putExtra("FIRST_NAME",getFname);
                intent.putExtra("PHONE_NUMBER",getPhone);
                intent.putExtra("EMAIL_ADDRESS",getEmail);
                intent.putExtra("RECHARGE_AMT",getAmt);
                intent.putExtra("PRODUCT_INFO",getInvoice);
                startActivityForResult(intent,PAYU_REQUEST_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYU_REQUEST_CODE) {

            if(resultCode == Activity.RESULT_OK){
                if(data!=null){
                    Log.d("RESPONSE_HASH",data.getStringExtra("transaction_id"));
                }
            }
            else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_LONG).show();
            }


        }
    }
}
