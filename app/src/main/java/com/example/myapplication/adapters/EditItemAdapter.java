package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.VendorItem;

import java.util.ArrayList;

public class EditItemAdapter extends RecyclerView.Adapter<EditItemAdapter.EditItemViewHolder>{

    public interface DeletedAdapterListener {
         void onRemoveMember(VendorItem dto);
    }

    private  Context context;
    private  ArrayList<VendorItem> vendorItemArrayList;
    DeletedAdapterListener mListener;

    public EditItemAdapter(Context context, ArrayList<VendorItem> itemModels, DeletedAdapterListener mListener){
        this.context = context;
        this.vendorItemArrayList = itemModels;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public EditItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.edit_item_detail_row,parent,false);
        return new EditItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final EditItemViewHolder holder, final int position) {
        final VendorItem vendorItem = vendorItemArrayList.get(position);
        holder.tv_food_name.setText(vendorItem.getItemName());
        holder.tv_quantity.setText("1");
        holder.tv_vat_percent.setText(String.valueOf(vendorItem.getTxPercentage()));
        holder.tv_price.setText(String.valueOf(vendorItem.getPrice()));
        calculateVatValue(holder, vendorItem);
        sumOperation(holder, vendorItem);

        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (vendorItemArrayList.size() == 0) {
                   return;
                }else{

                    mListener.onRemoveMember(vendorItem);

                }

            }
        });

    }

    private void calculateVatValue(EditItemViewHolder holder, VendorItem productItemModel){
        long qty;
        double mrp,vat_percent;
        mrp = productItemModel.getPrice();
        vat_percent = productItemModel.getTxPercentage();
        double vat_value = ((mrp * vat_percent) / 100) * 1;
        holder.tv_vat_value.setText(String.format(java.util.Locale.US,"%.2f",vat_value));
    }

    private void sumOperation(EditItemViewHolder holder, VendorItem productItemModel){
        long qty;
        double mrp,vat_value,vat_percent;
        mrp = productItemModel.getPrice();
        vat_percent = productItemModel.getTxPercentage();
        vat_value = ((mrp * vat_percent) / 100) * 1;
        double total = (mrp * 1) + vat_value;
        holder.tv_total_item_price.setText(context.getString(R.string.currency)+" "+ String.format(java.util.Locale.US,"%.2f",total));
    }

    @Override
    public int getItemCount() {
        return vendorItemArrayList != null ? vendorItemArrayList.size() : 0;
    }


    public class EditItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_food_name,tv_total_item_price,tv_vat_value, tv_priceLabel, tv_vatLabel;
        TextView tv_vat_percent,tv_price;
        EditText tv_quantity;
        LinearLayout quantity_layout,price_layout,vat_percent_layout,vat_value_layout;
        ImageButton mRemoveButton;

        public EditItemViewHolder(View itemView){
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
            mRemoveButton = itemView.findViewById(R.id.ib_remove);

        }
    }


}
