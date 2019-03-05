package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.DateModel;
import com.example.myapplication.utils.CommonFunctions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by manish on 17/7/18.
 */

public class InvoiceDateAdapter extends RecyclerView.Adapter<InvoiceDateAdapter.InvoiceViewHolder>{
    Context context;
    private ArrayList<DateModel> dateModels;
    OnSelectedDateListener onSelectedDateListener;

    public InvoiceDateAdapter(Context context, ArrayList<DateModel> dateModels, OnSelectedDateListener onSelectedDateListener){
        this.context = context;
        this.dateModels = dateModels;
        this.onSelectedDateListener = onSelectedDateListener;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.date_list_row,parent,false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        final DateModel dateModel = dateModels.get(position);
        holder.date_text.setText(convertStringToData(dateModel.getDate()));
        /*try{
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date dt = sd1.parse(dateModel.getDate());
            SimpleDateFormat sd2 = new SimpleDateFormat("dd-mmm-yyyy");
            String newDate = sd2.format(dt);

        }catch (Exception ex){
            ex.printStackTrace();
        }*/

        holder.date_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedDateListener.selectedDate(dateModel);
            }
        });
    }

    public static String convertStringToData(String stringData) {

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd MMM yyyy" );
        Date date;
        String oldformat,newformat;
        try {
            date = originalFormat.parse(stringData);
            oldformat = originalFormat.format(date);
            newformat =  targetFormat.format(date);
            System.out.println("Old Format :   " + originalFormat.format(date));
            System.out.println("New Format :   " + targetFormat.format(date));
            return newformat;
        } catch (ParseException ex) {
            // Handle Exception.
        }
        return stringData;
    }

    @Override
    public int getItemCount() {
        if (dateModels != null)
            return dateModels.size();
        else
            return 0;
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder{
        TextView date_text, calendar_icon;

        public InvoiceViewHolder(View view){
            super(view);
            calendar_icon = view.findViewById(R.id.calendar_icon);
            date_text = view.findViewById(R.id.tv_invoice_date);
            calendar_icon.setTypeface(CommonFunctions.setFontIcon(context));
            calendar_icon.setText("J");
        }
    }

    public interface OnSelectedDateListener{
        void selectedDate(DateModel dateModel);
    }
}
