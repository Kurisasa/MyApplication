package com.example.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.InvoiceDateAdapter;
import com.example.myapplication.model.DateModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTransactionsFragment extends Fragment implements InvoiceDateAdapter.OnSelectedDateListener{
    Context context;
    RecyclerView rv_all_transactions_list;
    ArrayList<DateModel> getDateList;
    InvoiceDateAdapter invoiceDateAdapter;
    View view;

    private ProgressDialog progressDialog;
    TextView tv_no_result_found;

    public AllTransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try{
//            uiUpdatedListener = (UIUpdatedListener) context;
//        }catch (ClassCastException e){
//            throw new ClassCastException(context.toString()
//                    + " must implement UIUpdatedListener");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_transactions, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar();
        bindView(view);
    }

    private void setToolbar(){
    }

    private void bindView(View itemView){
        context = getContext();
        rv_all_transactions_list = itemView.findViewById(R.id.rv_all_transactions_list);
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        rv_all_transactions_list.setHasFixedSize(true);
        tv_no_result_found =itemView.findViewById(R.id.no_result_found);

    }

    @Override
    public void selectedDate(DateModel dateModel) {
        if (dateModel != null) {
            Bundle bundle = new Bundle();
            bundle.putString("selectedDate", dateModel.getDate());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //invoiceDateAdapter = null;
        getDateList = null;
    }


}
