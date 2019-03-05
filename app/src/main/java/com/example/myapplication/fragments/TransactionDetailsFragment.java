package com.example.myapplication.fragments;//package com.example.myapplication.fragments;//package com.simp.kurisanic.dgi.fragments;
//
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.myapplication.R;
//import com.example.myapplication.adapters.ItemListAdapter;
//import com.example.myapplication.listener.TransactionDetailPresenter;
//import com.example.myapplication.listener.TransactionDetailView;
//import com.example.myapplication.listener.UIUpdatedListener;
//import com.example.myapplication.model.TransactionLineItem;
//import com.example.myapplication.model.InvoiceModel;
//import com.example.myapplication.model.User;
//import com.example.myapplication.utils.CommonFunctions;
//import com.example.myapplication.utils.Constants;
//
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class TransactionDetailsFragment extends Fragment implements TransactionDetailView, View.OnClickListener {
//    Context context;
//    UIUpdatedListener uiUpdatedListener;
//    InvoiceModel invoiceModel;
//    View view;
//    private ProgressDialog progressDialog;
//    User merchant;
//    TextView tv_customer_name,total_price, tv_save_arrow;
//    LinearLayout total_price_layout;
//    RecyclerView rv_item_list;
//    TransactionDetailPresenter transactionDetailPresenter;
//    ItemListAdapter itemListAdapter;
//
//    public TransactionDetailsFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try{
//            uiUpdatedListener = (UIUpdatedListener) context;
//        }catch (ClassCastException e){
//            throw new ClassCastException(context.toString()
//                    + " must implement UIUpdatedListener");
//        }
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null){
//            Bundle bundle = getArguments();
//            invoiceModel = (InvoiceModel) bundle.getSerializable("selectedInvoice");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_transaction_details, container, false);
//        setToolbar();
//        bindView(view);
//        return view;
//    }
//
//    private void setToolbar(){
//        if (uiUpdatedListener != null){
//            uiUpdatedListener.changeToolbar(true,false,getString(R.string.transaction_detail),false);
//        }
//    }
//
//    private void bindView(View itemView){
//        context = getContext();
//        total_price_layout = itemView.findViewById(R.id.total_price_layout);
//        total_price_layout.setOnClickListener(this);
//        total_price = itemView.findViewById(R.id.tv_total_price);
//        tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
//        rv_item_list = itemView.findViewById(R.id.rv_item_list);
//        tv_save_arrow = itemView.findViewById(R.id.tv_save_arrow);
//        tv_save_arrow.setTypeface(CommonFunctions.setFontIcon(context));
//        tv_save_arrow.setText("B");
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        rv_item_list.setHasFixedSize(true);
//
//     //   transactionDetailPresenter = new TransactionDetailPresenterImpl();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (invoiceModel != null){
//            total_price.setText(getResources().getString(R.string.currency)+" "+invoiceModel.getTotalAmount());
//            tv_customer_name.setText(invoiceModel.getInvoiceNumber());
//            itemListAdapter = new ItemListAdapter(context,(ArrayList<TransactionLineItem>) invoiceModel.getItems());
//            rv_item_list.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
//            rv_item_list.setAdapter(itemListAdapter);
//        }
//    }
//
//    @Override
//    public void showDialog(String message) {
//        if (progressDialog != null && !progressDialog.isShowing()){
//            progressDialog.setMessage(message);
//            progressDialog.show();
//        }
//    }
//
//    @Override
//    public void hideDialog() {
//        if (progressDialog != null && progressDialog.isShowing())
//            progressDialog.dismiss();
//    }
//
//    @Override
//    public void onError(String message) {
//        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onSuccess() {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.total_price_layout:
//                TransactionGeneratedFragment transactionGeneratedFragment = new TransactionGeneratedFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("invoiceNumber", invoiceModel.getInvoiceNumber());
//                bundle.putDouble("totalAmount", invoiceModel.getTotalAmount());
//                transactionGeneratedFragment.setArguments(bundle);
//                uiUpdatedListener.changeFragment(Constants.TransactionGeneratedFragment, Constants.TransactionGeneratedFragment,true,bundle);
//                break;
//        }
//    }
//}
