package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.myapplication.R;
import com.example.myapplication.model.VendorItem;

import java.util.ArrayList;

/**
 * Created by kurisani on 19/7/18.
 */

public class AutoCompleteProductNameAdapter extends ArrayAdapter<VendorItem> {

    public AutoCompleteProductNameAdapter(@NonNull Context context, ArrayList<VendorItem> listModels){
        super(context, R.layout.simple_textview,listModels);
    }


}
