package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.fragments.InvoiceReceiptFragment;


public class TransactionIdGeneratedDialog extends Dialog {
    Context context;
    TextView transaction_no, transaction_price, btn_view_receipt, btn_new_transaction;
    String transaction_number;
    double amount;
//    TransactionIdGeneratedListener transactionIdGeneratedListener;
    //UIUpdatedListener uiUpdatedListener;

    public TransactionIdGeneratedDialog(@NonNull Context context, String transaction_number, double amount){//, UIUpdatedListener uiUpdatedListener
        super(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        this.context = context;
        this.amount = amount;
        this.transaction_number = transaction_number;
//        this.transactionIdGeneratedListener = transactionIdGeneratedListener;
      //  this.uiUpdatedListener = uiUpdatedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transaction_generated);
        transaction_no = (TextView) findViewById(R.id.transaction_no);
        transaction_price = (TextView) findViewById(R.id.transaction_price);
        btn_view_receipt = (TextView) findViewById(R.id.btn_view_receipt);
        btn_new_transaction = (TextView) findViewById(R.id.btn_new_transaction);

        transaction_no.setText(transaction_number);
        transaction_price.setText(context.getResources().getString(R.string.currency)+" "+ String.format(java.util.Locale.US,"%.2f",amount)+" "+context.getResources().getString(R.string.generated));
        btn_view_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvoiceReceiptFragment invoiceReceiptFragment = new InvoiceReceiptFragment();
                Bundle bundle = new Bundle();
                Log.d("onViewReceipt", "no: "+transaction_number);
                bundle.putString("invoiceNumber", transaction_number);
                invoiceReceiptFragment.setArguments(bundle);
                //uiUpdatedListener.changeFragment(Constants.InvoiceReceiptFragment, Constants.InvoiceReceiptFragment,true, bundle);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 1000);
            }
        });
        btn_new_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
