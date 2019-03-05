package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.myapplication.R;
import com.example.myapplication.fragments.NewTransactionFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by kurisani on 16/7/18.
 */

public class CommonFunctions {

    public static String getTimeStamp() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "" + date;

        return photoFile;
    }

    public static Fragment changeFragment(String fragmentName){
        Fragment fragment = null;
        switch (fragmentName){
           case Constants.NewTransactionMenu:
               fragment = new NewTransactionFragment();
//                break;
//            case Constants.AllTransactionsMenu:
//                fragment = new AllTransactionsFragment();
//                break;
//            case Constants.InvoiceListFragment:
//                fragment = new InvoiceDateListFragment();
//                break;
//            case Constants.TransactionDetailFragment:
//                fragment = new TransactionDetailsFragment();
//                break;
//            case Constants.ProductsMenu:
//                fragment = new ProductListFragment();
//                break;
//            case TransactionGeneratedFragment:
//                fragment = new TransactionGeneratedFragment();
//                break;
//            case NewProductFragment:
//                fragment = new NewProductFragment();
//                break;
//            case Constants.InvoiceReceiptFragment:
//                fragment = new InvoiceReceiptFragment();
//                break;
        }
        return fragment;
    }

    public static boolean isNetworkConnected(Context context) {
       // ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //return cm.getActiveNetworkInfo() != null;
        return true;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //Set Font Icons
    public static Typeface setFontIcon(Context context) {
        return Typeface.createFromAsset(context.getAssets(), context.getString(R.string.font_icons_file));
    }

    public static void DownloadFile(String fileURL, File directory) {
        try {

            FileOutputStream f = new FileOutputStream(directory);
            URL u = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            InputStream in = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
                System.out.println(""+buffer.toString());
            }
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
