package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.TransactionLineItem;

import java.util.ArrayList;

/**
 * Created by kurisani on 18/7/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>{
    Context context;
    ArrayList<TransactionLineItem> itemModels;

    public ItemListAdapter(Context context,ArrayList<TransactionLineItem> itemModels){
        this.context = context;
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_detail_row,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        TransactionLineItem itemModel = itemModels.get(position);
        holder.tv_total_item_price.setText(context.getResources().getString(R.string.currency)+" "+String.valueOf(itemModel.getLineTotal()));
        holder.tv_quantity.setText(String.valueOf(itemModel.getQuantity()));
        holder.tv_price.setText(String.valueOf(itemModel.getPrice()));
        if (itemModel.getTransactionGuid() != null){
            holder.tv_food_name.setText(itemModel.getName());
            holder.tv_vat_percent.setText(String.valueOf(itemModel.getVat()));
            holder.tv_vat_value.setText(String.valueOf(itemModel.getLineTotal()));
        }else{
            holder.tv_food_name.setText(itemModel.getBarcode());
        }

//        if (itemModel.getVat()){
//            holder.vat_percent_layout.setVisibility(View.VISIBLE);
//            holder.vat_value_layout.setVisibility(View.VISIBLE);
//        }else{
//            holder.vat_percent_layout.setVisibility(View.GONE);
//            holder.vat_value_layout.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return itemModels != null ? itemModels.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_food_name,tv_total_item_price,tv_vat_value,tv_vat_percent,tv_quantity,tv_price, tv_priceLabel, tv_vatLabel;
        LinearLayout quantity_layout,price_layout,vat_percent_layout,vat_value_layout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_food_name = itemView.findViewById(R.id.tv_food_name);
            tv_total_item_price = itemView.findViewById(R.id.tv_total_item_price);
            tv_vat_value = itemView.findViewById(R.id.tv_vat_value);
            tv_vat_percent = itemView.findViewById(R.id.tv_vat_percent);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_priceLabel = itemView.findViewById(R.id.tv_priceLabel);
            tv_vatLabel = itemView.findViewById(R.id.tv_vatLabel);
            quantity_layout = itemView.findViewById(R.id.quantity_layout);
            price_layout = itemView.findViewById(R.id.price_layout);
            vat_percent_layout = itemView.findViewById(R.id.vat_percent_layout);
            vat_value_layout = itemView.findViewById(R.id.vat_value_layout);
        }
    }

}
