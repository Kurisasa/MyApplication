package com.example.myapplication.fragments;//package com.simp.kurisanic.dgi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;
import com.example.myapplication.model.User;
import com.example.myapplication.utils.SharedPrefs;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceReceiptFragment extends Fragment {
    View view;
   // UIUpdatedListener uiUpdatedListener;
    private WebView webView;
    String invoiceNumber;
    double totalAmount;
    SharedPrefs sharedPrefs;
    User merchant;
    public InvoiceReceiptFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
           // uiUpdatedListener = (UIUpdatedListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement UIUpdatedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            Bundle bundle = getArguments();
            invoiceNumber = bundle.getString("invoiceNumber");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_invoice_receipt, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar();
        bindView(view);
    }

    private void setToolbar() {
      //  uiUpdatedListener.changeToolbar(false, false, getString(R.string.receipt),true);
    }

    private void bindView(View view) {
        Gson gson = new Gson();
        sharedPrefs  = new SharedPrefs(getContext());
        String json = sharedPrefs.getString(SharedPrefs.SharedPrefsVariable.Merchant);
        merchant = gson.fromJson(json, User.class);

        webView = (WebView) view.findViewById(R.id.webView_receipt);
//        webView.loadUrl(ApiClient.BASE_URL+"viewInvoice?id="+invoiceNumber+"&merchant_id="+merchant.getId()+"&lang="+ LocaleHelper.getLanguage(getContext()));
//        MiniPosApp.getInstance().setPdfUrl(ApiClient.BASE_URL+"txt?id="+invoiceNumber+"&merchant_id="+merchant.getId()+"&lang="+ LocaleHelper.getLanguage(getContext()));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });

        webView.setWebChromeClient(new WebChromeClient());
    }
}
