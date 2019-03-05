package com.example.myapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Vendor;
import com.example.myapplication.model.VendorItem;
import com.example.myapplication.session.SessionManager;
import com.example.myapplication.utils.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class MyService extends Service {

    private static String TAG = MyService.class.getSimpleName();
    public boolean isRunning = false;
    Context ctx;
    DatabaseHelper databaseHelper;
    SessionManager session;
    String token;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        ctx = getApplicationContext();
        session = new SessionManager(ctx);

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // name
        token = user.get(SessionManager.KEY_TOKEN);
        //networkManager = new NetworkManager(context,token);
        try {
            addToLocal();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }

    @Override
    public synchronized void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart");
    }


    private void addToLocal() throws IOException, JSONException {
        // Sending get request
        URL url = new URL("https://dgi.simp.africa/backend/api/Vendor/getdevicevendordata/94283ff1-e34f-46ef-ae2a-4ab641cf4fea");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization","Bearer "+ token);

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());
        String jsonStr = response.toString();


        JSONObject jsonObj = new JSONObject(jsonStr);

        String vendorGuid  = jsonObj.getJSONObject("returnData").getString("vendorGuid");
        String vendorNumber = jsonObj.getJSONObject("returnData").getString("vendorNumber");
        String vendorName = jsonObj.getJSONObject("returnData").getString("vendorName");
        String vendorDescription = jsonObj.getJSONObject("returnData").getString("vendorDescription");
        String address = jsonObj.getJSONObject("returnData").getString("address");
        String phoneNo  = jsonObj.getJSONObject("returnData").getString("phoneNo");
        String email = jsonObj.getJSONObject("returnData").getString("email");
        String registrationDate = jsonObj.getJSONObject("returnData").getString("registrationDate");
        String isActive = jsonObj.getJSONObject("returnData").getString("isActive");

        // adding each child node to HashMap key => value

        Vendor vendor1 = new Vendor();
        vendor1.setVendorGuid(vendorGuid);
        vendor1.setVendorNumber(vendorNumber);
        vendor1.setVendorName(vendorName);
        vendor1.setVendorDescription(vendorDescription);
        vendor1.setAddress(address);
        vendor1.setPhoneNo(phoneNo);
        vendor1.setEmail(email);
        vendor1.setRegistrationDate(registrationDate);
        vendor1.setIsActive(isActive);
        long id;

        if(databaseHelper.checkVendorItem(vendorGuid)){
            return;
        }else{
            id = databaseHelper.addVendor(vendor1);
        }

        if(id <=0){
            Message.message(ctx, "Insertion Unsuccessful");
        }

        JSONArray arr = (JSONArray) jsonObj.getJSONObject("returnData").getJSONArray("fiN_VendorItem");

        VendorItem vendorItem = null;
        for (int i = 0; i < arr.length(); i++) {
            String vendorItemGuid = arr.getJSONObject(i).getString("vendorItemGuid");
            String vendorGuid1 =  arr.getJSONObject(i).getString("vendorGuid");
            String barcode = arr.getJSONObject(i).getString("barcode");
            String itemGuid = arr.getJSONObject(i).getString("itemGuid");
            String itemName = arr.getJSONObject(i).getString("itemName");
            Double  price = arr.getJSONObject(i).getDouble("price");
            Double taxPercentage = arr.getJSONObject(i).getDouble("taxPercentage");

            System.out.println(vendorItemGuid);

            vendorItem = new VendorItem();
            vendorItem.setVendorItemGuid(vendorItemGuid);
            vendorItem.setVendorGuid(vendorGuid1);
            vendorItem.setBarcode(barcode);
            vendorItem.setItemGuid(itemGuid);
            vendorItem.setItemName(itemName);
            vendorItem.setPrice(price);
            vendorItem.setTxPercentage(taxPercentage);
            long id_item;

            if(databaseHelper.checkVendorItem(vendorItemGuid)){
                return;
            }else{
                id_item =  databaseHelper.addVendorItem(vendorItem);
            }


            if(id_item <=0){
                Message.message(ctx, "Insertion Unsuccessful");
            }
        }

    }
}
