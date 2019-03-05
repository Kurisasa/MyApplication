package com.example.myapplication.fragments;//package com.simp.kurisanic.dgi.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

//
//
//import android.app.DownloadManager;
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.graymatrix.minipos.R;
//import com.graymatrix.minipos.UIUpdatedListener;
//import com.graymatrix.minipos.models.InvoiceModel;
//import com.graymatrix.minipos.models.Merchant;
//import com.graymatrix.minipos.network.ApiClient;
//import com.graymatrix.minipos.presenters.DownloadFileAsync;
//import com.graymatrix.minipos.utils.Constants;
//import com.graymatrix.minipos.utils.Logger;
//import com.graymatrix.minipos.utils.SharedPrefs;
//
///**
// * A simple {@link Fragment} subclass.
// */
public class TransactionGeneratedFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }
//    View view;
//    TextView transaction_no, transaction_price,btn_download_receipt, btn_view_receipt, btn_new_transaction;
//    UIUpdatedListener uiUpdatedListener;
//    Context context;
//    String invoiceNumber;
//    double totalAmount;
//    SharedPrefs sharedPrefs;
//    Merchant merchant;
//    public TransactionGeneratedFragment() {
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
//            invoiceNumber = bundle.getString("invoiceNumber");
//            totalAmount = bundle.getDouble("totalAmount");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_transaction_generated, container, false);
//        return view;
//    }
//
//    public void showMessage(String message) {
//        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setToolbar();
//        bindView(view);
//    }
//
//    private void setToolbar() {
//        uiUpdatedListener.changeToolbar(false, false, getString(R.string.transaction_generated),false);
//    }
//
//    private void bindView(View view) {
//        context = getContext();
//        Gson gson = new Gson();
//        sharedPrefs  = new SharedPrefs(getContext());
//        String json = sharedPrefs.getString(SharedPrefs.SharedPrefsVariable.Merchant);
//        merchant = gson.fromJson(json, Merchant.class);
//        transaction_no = (TextView) view.findViewById(R.id.transaction_no);
//        transaction_price = (TextView) view.findViewById(R.id.transaction_price);
//        btn_view_receipt = (TextView) view.findViewById(R.id.btn_view_receipt);
//        btn_new_transaction = (TextView) view.findViewById(R.id.btn_new_transaction);
//        btn_download_receipt = view.findViewById(R.id.btn_download_receipt);
//        transaction_no.setText(invoiceNumber);
//        transaction_price.setText(getResources().getString(R.string.currency)+" "+String.valueOf(totalAmount)+"  "+getResources().getString(R.string.generated));
//        btn_view_receipt.setOnClickListener(this);
//        btn_download_receipt.setOnClickListener(this);
//        btn_new_transaction.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_view_receipt:
//                InvoiceReceiptFragment invoiceReceiptFragment = new InvoiceReceiptFragment();
//                Bundle bundle = new Bundle();
//                Log.d("onViewReceipt", "no: "+invoiceNumber);
//                bundle.putString("invoiceNumber", invoiceNumber);
//                invoiceReceiptFragment.setArguments(bundle);
//                uiUpdatedListener.changeFragment(Constants.InvoiceReceiptFragment, Constants.InvoiceReceiptFragment,true, bundle);
//                break;
//            case R.id.btn_new_transaction:
//                uiUpdatedListener.changeFragment(Constants.NewTransactionMenu,Constants.NewTransactionMenu,true,null);
//                break;
//            case R.id.btn_download_receipt:
//                String pdf_url = ApiClient.PDF_URL+"pdf?id="+invoiceNumber+"&merchant_id="+merchant.getId();
//                startDownload(pdf_url);
//               // downloadDocument(invoiceNumber);
//                break;
//        }
//    }
//
//    Handler solutionsHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            String messege = msg.getData().getString(Constants.response);
//            showMessage(messege);
//        }
//    };
//
//    private void downloadDocument(String invoiceNumber) {
//       /* DownloadFileAsync file = new DownloadFileAsync(context, "PDF Downloaded", pdf_url, solutionsHandler);
//        file.execute();*/
//    }
//
//    public void startDownload(String url) {
//        Logger.d("TAG",url);
//        DownloadManager mManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        DownloadManager.Request mRqRequest = new DownloadManager.Request(
//                Uri.parse(url));
//        mRqRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        mRqRequest.setVisibleInDownloadsUi(true);
//        mRqRequest.setDescription("Successfully Downloaded PDF");
////  mRqRequest.setDestinationUri(Uri.parse("give your local path"));
//        long idDownLoad=mManager.enqueue(mRqRequest);
//    }
}
