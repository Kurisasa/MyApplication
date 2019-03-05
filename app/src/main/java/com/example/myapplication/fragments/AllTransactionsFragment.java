package com.example.myapplication.fragments;//package com.example.myapplication.fragments;
//
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.myapplication.R;
//import com.example.myapplication.adapters.InvoiceDateAdapter;
//
//import com.example.myapplication.model.DateModel;
//import com.example.myapplication.model.User;
//import com.example.myapplication.utils.Constants;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class AllTransactionsFragment extends Fragment implements AllTransactionView, InvoiceDateAdapter.OnSelectedDateListener{
//    Context context;
//    RecyclerView rv_all_transactions_list;
//    ArrayList<DateModel> getDateList;
//    InvoiceDateAdapter invoiceDateAdapter;
//    View view;
//    UIUpdatedListener uiUpdatedListener;
//
//    private ProgressDialog progressDialog;
//    User merchant;
//   // SharedPrefs sharedPrefs;
//    TextView tv_no_result_found;
//
//    public AllTransactionsFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
////        try{
////            uiUpdatedListener = (UIUpdatedListener) context;
////        }catch (ClassCastException e){
////            throw new ClassCastException(context.toString()
////                    + " must implement UIUpdatedListener");
////        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_all_transactions, container, false);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setToolbar();
//       // bindView(view);
//    }
//
//    private void setToolbar(){
//        if (uiUpdatedListener != null){
//            uiUpdatedListener.switchTabs(Constants.AllTransactionsMenu);
//            uiUpdatedListener.changeToolbar(true,false,getString(R.string.all_transaction),false);
//        }
//    }
//
//    private void bindView(View itemView){
//        context = getContext();
//        rv_all_transactions_list = itemView.findViewById(R.id.rv_all_transactions_list);
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        rv_all_transactions_list.setHasFixedSize(true);
//        tv_no_result_found =itemView.findViewById(R.id.no_result_found);
//
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
//        Snackbar.make(view,message, Snackbar.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onSuccess(List<DateModel> dateModels) {
//        if (dateModels.size() > 0) {
//            if (getDateList == null && invoiceDateAdapter == null) {
//                getDateList = (ArrayList<DateModel>) dateModels;
//                invoiceDateAdapter = new InvoiceDateAdapter(context, getDateList, this);
//                rv_all_transactions_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//                rv_all_transactions_list.setAdapter(invoiceDateAdapter);
//                tv_no_result_found.setVisibility(View.GONE);
//            } else {
//                getDateList.addAll((ArrayList<DateModel>) dateModels);
//                invoiceDateAdapter.notifyDataSetChanged();
//            }
//        } else {
//            tv_no_result_found.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void selectedDate(DateModel dateModel) {
//        if (dateModel != null){
//            Bundle bundle = new Bundle();
//            bundle.putString("selectedDate",dateModel.getDate());
////            if (uiUpdatedListener != null)
////                uiUpdatedListener.changeFragment(Constants.InvoiceListFragment,Constants.InvoiceListFragment,true,bundle);
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        //invoiceDateAdapter = null;
//        getDateList = null;
//    }
//}
