package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.TransactionLineItem;

import java.util.ArrayList;

/**
 * Created by manish on 19/7/18.
 */

public class AddNewTransactionDialog extends Dialog implements View.OnClickListener{
    Context context;
    private TextView et_qty,et_price,et_vat_percent;
    private TextView tv_close,save_btn,tv_total_item_price,tv_vat_value, tv_PriceHeader, tv_vatHeader;
    private AutoCompleteTextView act_product_name;
    private ArrayList<TransactionLineItem> listModels;
    private ArrayAdapter<TransactionLineItem> arrayAdapter;
    AddProductInterface  addProductInterface;
    TransactionLineItem productsListModel;
    double total;
    public AddNewTransactionDialog(@NonNull Context context, ArrayList<TransactionLineItem> listModels, AddProductInterface addProductInterface) {
        super(context);
        this.context = context;
        this.listModels = listModels;
        this.addProductInterface = addProductInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_transaction_dialog);
        act_product_name = findViewById(R.id.act_product_name);
        et_qty = findViewById(R.id.tv_quantity);
        et_price = findViewById(R.id.tv_price);
        et_vat_percent = findViewById(R.id.tv_vat_percent);
        tv_close = findViewById(R.id.tv_close_btn);
        tv_close.setTypeface(CommonFunctions.setFontIcon(context));
        save_btn = findViewById(R.id.save_btn);
        tv_total_item_price = findViewById(R.id.tv_total_item_price);
        tv_total_item_price.setText(context.getResources().getString(R.string.currency)+" 0");
        tv_vat_value = findViewById(R.id.tv_vat_value);
        tv_PriceHeader = findViewById(R.id.tv_priceHeader);
        tv_vatHeader = findViewById(R.id.tv_vatHeader);
        /*tv_PriceHeader.setText("Price("+context.getResources().getString(R.string.currency)+")");
        tv_vatHeader.setText("VAT("+context.getResources().getString(R.string.currency)+")");*/
        arrayAdapter = new ArrayAdapter<TransactionLineItem>(
                context, android.R.layout.simple_dropdown_item_1line, listModels);
        act_product_name.setAdapter(arrayAdapter);
        tv_close.setOnClickListener(this);
        save_btn.setOnClickListener(this);

        et_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    calculateVatValue();
                    sumOperation();
                }
            }
        });

       /* et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    calculateVatValue();
                    sumOperation();
                }
            }
        });

        et_vat_percent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateVatValue();
                sumOperation();
            }
        });*/

        /*et_vat_percent.setFilters(new InputFilter[] {
                new DigitsKeyListener(Boolean.FALSE, Boolean.TRUE) {
                    int beforeDecimal = Integer.MAX_VALUE, afterDecimal = 2;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = et_vat_percent.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.";
                        }
                        else if (temp.toString().indexOf(".") == -1) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        } else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }
                }
        });*/

        act_product_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productsListModel = (TransactionLineItem) parent.getAdapter().getItem(position);
                et_qty.setText(String.valueOf(1));
                et_price.setText(String.valueOf(productsListModel.getLineTotal()));
                et_vat_percent.setText(String.valueOf(productsListModel.getVat()));
                calculateVatValue();
                sumOperation();
            }
        });
    }

    private void calculateVatValue(){
        long qty;
        double mrp,vat_percent;
        String qty_text = et_qty.getText().toString();
        String price_text = et_price.getText().toString();
        String vat_percent_text = et_vat_percent.getText().toString();

        qty = Long.parseLong(!qty_text.isEmpty() ? qty_text : "0");
        mrp = Double.parseDouble(!price_text.isEmpty() ? price_text : "0");
        vat_percent = Double.parseDouble(!vat_percent_text.isEmpty() ? vat_percent_text : "0");
        double vat_value = ((mrp * vat_percent) / 100) * qty;
        tv_vat_value.setText(String.format(java.util.Locale.US,"%.2f",vat_value));
        Logger.d("TAG","VAT_VALUE"+vat_value);
    }

    private void sumOperation(){
        long qty;
        double mrp,vat_value;
        String qty_text = et_qty.getText().toString();
        String price_text = et_price.getText().toString();
        String vat_value_text = tv_vat_value.getText().toString();

        qty = Long.parseLong(!qty_text.isEmpty() ? qty_text : "0");
        mrp = Double.parseDouble(!price_text.isEmpty() ? price_text : "0");
        vat_value = Double.parseDouble(!vat_value_text.isEmpty() ? vat_value_text : "0");
        total = (double) (mrp * qty) + vat_value;
        tv_total_item_price.setText(context.getResources().getString(R.string.currency)+" "+ String.format(java.util.Locale.US,"%.2f",total));
        Logger.d("TAG","TOTAL"+total);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_btn:
                saveOperation();
                break;
            case R.id.tv_close_btn:
                dismiss();
                break;
        }
    }

    private void saveOperation(){
        int qty;
        double mrp,vat_percent,vat_value;
        if (!act_product_name.getText().toString().trim().isEmpty()){
            if (productsListModel != null){
                String qty_text = et_qty.getText().toString();
                String price_text = et_price.getText().toString();
                String vat_value_text = tv_vat_value.getText().toString();
                String vat_percent_text = et_vat_percent.getText().toString();

                qty = Integer.parseInt(!qty_text.isEmpty() ? qty_text : "0");
                mrp = Double.parseDouble(!price_text.isEmpty() ? price_text : "0");
                vat_value = Double.parseDouble(!vat_value_text.isEmpty() ? vat_value_text : "0");
                vat_percent = Double.parseDouble(!vat_percent_text.isEmpty() ? vat_percent_text : "0");

                if (qty != 0 && mrp != 0){
                    productsListModel.setQuantity(qty);
                    productsListModel.setVat(vat_value);
                    productsListModel.setLineTotal(mrp);
                    addProductInterface.addProdcutInterface(productsListModel,total);
                    dismiss();
                }else{
                    Toast.makeText(context, R.string.should_not_be_zero, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, R.string.no_product_found, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, R.string.please_enter_product_name, Toast.LENGTH_SHORT).show();
        }
    }

    public interface AddProductInterface{
        void addProdcutInterface(TransactionLineItem listModel, double total_price);
    }
}
