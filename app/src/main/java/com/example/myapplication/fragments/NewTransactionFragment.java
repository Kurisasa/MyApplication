package com.example.myapplication.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.BarcodeCaptureActivity;
import com.example.myapplication.activity.ScannerActivity;
import com.example.myapplication.adapters.EditItemAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Transaction;
import com.example.myapplication.model.TransactionLineItem;
import com.example.myapplication.model.Vendor;
import com.example.myapplication.model.VendorItem;
import com.example.myapplication.service.MyService;
import com.example.myapplication.session.SessionManager;
import com.example.myapplication.utils.CommonFunctions;
import com.example.myapplication.utils.Message;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.lowagie.text.pdf.PdfPCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class NewTransactionFragment extends Fragment implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    Context context;
    View view;
    HashMap<String, ArrayList<VendorItem>> arrayListHashMap;
    private ProgressDialog progressDialog;
    RecyclerView rv_item_list;
    EditItemAdapter editItemAdapter;
    ArrayList<VendorItem> vendorItemArrayList = new ArrayList<>();
    EditText et_customer_name;
    LinearLayout scan_more_layout,add_manually_layout,total_price_layout;
    TextView tv_totalLabel, tv_total_price, tv_save_arrow;
    TextView btn_scan_icon, btn_add_icon;
    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = NewTransactionFragment.class.getSimpleName();
    int width;
    int height;
    double total_price,total_tax;
    DatabaseHelper databaseHelper;
    SessionManager session;
    String token;
    public NewTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_transaction, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar();
        bindView(view);
        if (android.os.Build.VERSION.SDK_INT > 16) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // Session class instance
        session = new SessionManager(context);

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);


    }

    private void bindView(View itemView){
        context = getContext();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        btn_scan_icon = itemView.findViewById(R.id.btn_scan_icon);
        btn_add_icon = itemView.findViewById(R.id.btn_add_icon);
        btn_scan_icon.setTypeface(CommonFunctions.setFontIcon(context));
        btn_add_icon.setTypeface(CommonFunctions.setFontIcon(context));
        rv_item_list = itemView.findViewById(R.id.rv_item_list);
        et_customer_name = itemView.findViewById(R.id.et_customer_name);
        total_price_layout = itemView.findViewById(R.id.total_price_layout);
        tv_totalLabel = itemView.findViewById(R.id.tv_totalLabel);
        tv_totalLabel.setText(getResources().getString(R.string.save_total));
        tv_total_price = itemView.findViewById(R.id.tv_total_price);
        tv_total_price.setText(" " + getResources().getString(R.string.currency)+" 0");
        tv_save_arrow = itemView.findViewById(R.id.tv_save_arrow);
        tv_save_arrow.setTypeface(CommonFunctions.setFontIcon(context));
        tv_save_arrow.setText("B");
        scan_more_layout = itemView.findViewById(R.id.scan_more_layout);
        add_manually_layout = itemView.findViewById(R.id.add_manually_layout);
        rv_item_list.setHasFixedSize(true);
        arrayListHashMap = new HashMap<>();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        databaseHelper = new DatabaseHelper(context);
        add_manually_layout.setOnClickListener(this);
        scan_more_layout.setOnClickListener(this);
        total_price_layout.setOnClickListener(this);
        this.context = getActivity();
        getActivity().startService(new Intent(getActivity(), MyService.class));

    }

    private void setAdapter(){
        editItemAdapter = new EditItemAdapter(context, vendorItemArrayList, new EditItemAdapter.DeletedAdapterListener() {
            @Override
            public void onRemoveMember(VendorItem dto) {
               vendorItemArrayList.remove(dto);
                editItemAdapter.notifyDataSetChanged();
                rv_item_list.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                rv_item_list.setAdapter(editItemAdapter);

                updateTotal();
            }
        });
        rv_item_list.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        rv_item_list.setAdapter(editItemAdapter);

        updateTotal();
    }
    private double m_Text = 0.0;
    private void setToolbar(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scan_more_layout:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            CAMERA_REQUEST);
                } else {
                    Intent intent = new Intent(context, ScannerActivity.class);
                    startActivityForResult(intent, RC_BARCODE_CAPTURE);
                }
                break;
            case R.id.add_manually_layout:
                try {
                    addToLocal();

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.total_price_layout:
                //  scanBluetooth();

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // Do the file write
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Proceed Payments");

                     // Set up the input
                    final EditText input = new EditText(context);
                  // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(input);

                      // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_Text = Double.parseDouble(input.getText().toString());
                            Log.d(TAG,"Total "+ m_Text);

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                    createPDF();

                } else {
                    // Request permission from the user
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }

                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(context, ScannerActivity.class);
                    startActivityForResult(intent, RC_BARCODE_CAPTURE);
                } else {
                    Toast.makeText(context, context.getString(R.string.allow_camera), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
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
              Message.message(context, "Insertion Unsuccessful");
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
                Message.message(context, "Insertion Unsuccessful");
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE)
        {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String resultValue = data.getStringExtra(BarcodeCaptureActivity.BarcodeObject);
                    Log.d(TAG, "Barcode read: " + resultValue);
                    if (resultValue != null){
                        VendorItem vendorItem =  databaseHelper.search(resultValue);
                        if(vendorItem != null){
                            vendorItemArrayList.add(vendorItem);
                            setAdapter();
                        }else{
                            Toast.makeText(context, "Product Doesn't exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                Toast.makeText(context,String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)),Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    double change = 0.0;
    double tender = 0.0;

    public void updateTotal() {
        double total = 0,total_tax_value = 0,vat_value;
        tv_total_price.setText("");
        total_price = 0;
        total_tax = 0;

        Log.d(TAG,"Total"+vendorItemArrayList.size());
            for (int i = 0; i < vendorItemArrayList.size(); i++){
                vat_value = ((vendorItemArrayList.get(i).getPrice()*  vendorItemArrayList.get(i).getTxPercentage()) / 100) * 1;
                total = total + (vendorItemArrayList.get(i).getPrice()* 1) + vat_value;
                total_tax_value = total_tax_value + vendorItemArrayList.get(i).getTxPercentage();
            }

        Log.d(TAG,"Total "+ total);
        tv_total_price.setText(" " + getResources().getString(R.string.currency)+" "+String.format(java.util.Locale.US,"%.2f",total));
        total_price = total;
        total_tax = total_tax_value;


    }

    @Override
    public void onStop() {
        super.onStop();
        et_customer_name.setText("");
        tv_total_price.setText("");
        total_price = 0;
        total_tax = 0;
    }

    void FormatHeaderCell(PdfPCell cell){
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
    }
    void FormatCell(PdfPCell cell){
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
    }
    Transaction transaction = null;
    TransactionLineItem transactionLineItem = null;
    ArrayList<Transaction> transactions = new ArrayList<>();


     public void createPDF(){

         //updating transaction
         UUID uuid = UUID.randomUUID();
         String randomUUIDString = uuid.toString();

         //Create time stamp
         Date date = new Date() ;
         String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

         final int random = new Random().nextInt(61) + 20;

         change = total_price - m_Text;
         tender = m_Text;

         Log.d(TAG,"Change " + change);
         Log.d(TAG,"Tender " + tender);

         DatabaseHelper databaseHelper = new DatabaseHelper(context);
         transaction = new Transaction();
         transaction.setTransactionGuid(randomUUIDString);
         transaction.setVendorGuid("1aed3755-6968-4463-b38d-c99ca90a9216");
         transaction.setDeviceGuid("94283ff1-e34f-46ef-ae2a-4ab641cf4fea");
         transaction.setPeriodGuid(uuid.toString());
         transaction.setTransactionTypeId("1");
         transaction.setDocumentNumber(String.valueOf(random));
         transaction.setTaxableTotal(0.0);
         transaction.setNonTaxableTotal(0.0);
         transaction.setVAT(total_tax);
         transaction.setTotal(total_price);
         transaction.setTransactionDTS(timeStamp);
         transaction.setLocation("localhost");
         transaction.setTender(tender);
         transaction.setChange(change);
         transactions.add(transaction);


         for (int i = 0; i < vendorItemArrayList.size(); i++){

             transactionLineItem = new TransactionLineItem();
             transactionLineItem.setBarcode(vendorItemArrayList.get(i).getBarcode());
             transactionLineItem.setLineItemGuid(randomUUIDString);
             transactionLineItem.setLineTotal(vendorItemArrayList.get(i).getPrice());
             transactionLineItem.setName(vendorItemArrayList.get(i).getItemName());
             transactionLineItem.setQuantity(1);
             transactionLineItem.setTransactionGuid(transaction.getTransactionGuid());
             transactionLineItem.setVat(total_tax);
             transactionLineItem.setPrice(vendorItemArrayList.get(i).getPrice());

             transaction.setLineItemList(transactionLineItem);
            databaseHelper.addTransaction(transaction);
            // databaseHelper.addTransactionItem(transactionLineItem);
         }

         Transaction Addedransaction =   databaseHelper.getTransaction(randomUUIDString);

         Log.d(TAG,"ListItems " + Addedransaction.getLineItemList());
         //Step 1
//        Document doc = new Document();
//
//        try{
//            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";
//            File dir = new File(path);
//            if(!dir.exists())
//                dir.mkdir();
//
//            Log.d("PDFCreator", "PDF Path: " + path);
//
//            //Create time stamp
////            Date date = new Date() ;
////            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//
//            File file = new File(dir, "Invoice1.pdf");
//            FileOutputStream fOut = new FileOutputStream(file);
//
//            //Step 2
//            PdfWriter.getInstance(doc, fOut);
//            //Step 3
//            doc.open();
//
//            //Step 4 Add content
//
//            // Setting table's cells horizontal alignment
//            PdfPTable table = new PdfPTable(3);
//
//            // Setting table's cells vertical alignment
//            PdfPCell[] cells = new PdfPCell[3];
//
//            cells[0] = new PdfPCell(new Phrase("1001"));
//            cells[0].setColspan(3);
//            FormatHeaderCell(cells[0]);
//            table.addCell(cells[0]);
//            table.completeRow();
//
//            cells[0] = new PdfPCell(new Phrase("Vendor 1"));
//            cells[0].setColspan(3);
//            FormatHeaderCell(cells[0]);
//            table.addCell(cells[0]);
//            table.completeRow();
//
//            cells[0] = new PdfPCell(new Phrase("Test Address"));
//            cells[0].setColspan(3);
//            FormatHeaderCell(cells[0]);
//            table.addCell(cells[0]);
//            table.completeRow();
//
//            cells[0] = new PdfPCell(new Phrase("0123"));
//            cells[0].setColspan(3);
//            FormatHeaderCell(cells[0]);
//            table.addCell(cells[0]);
//            table.completeRow();
//
//            cells[0] = new PdfPCell(new Phrase(timeStamp));
//            cells[0].setColspan(3);
//            FormatHeaderCell(cells[0]);
//            table.addCell(cells[0]);
//            table.completeRow();
//
//            cells[0] = new PdfPCell(new Phrase(" "));
//            cells[0].setColspan(3);
//            FormatHeaderCell(cells[0]);
//            table.addCell(cells[0]);
//            table.completeRow();
//
//            PdfPCell cell1 = new PdfPCell(new Phrase("Qty"));
//            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell1.setBorderWidthBottom(1);
//            cell1.setBorderWidthTop(1);
//            cell1.setBorderWidthLeft(0);
//            cell1.setBorderWidthRight(0);
//            table.addCell(cell1);
//
//            PdfPCell cell2 = new PdfPCell(new Phrase("Item Name"));
//            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell2.setBorderWidthBottom(1);
//            cell2.setBorderWidthTop(1);
//            cell2.setBorderWidthLeft(0);
//            cell2.setBorderWidthRight(0);
//            table.addCell(cell2);
//
//            PdfPCell cell3 = new PdfPCell(new Phrase("Total"));
//            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell3.setBorderWidthBottom(1);
//            cell3.setBorderWidthTop(1);
//            cell3.setBorderWidthLeft(0);
//            cell3.setBorderWidthRight(0);
//            table.addCell(cell3);
//            table.completeRow();
//
//            for (int i = 0; i < vendorItemArrayList.size(); i++) {
//
//                cells[0] = new PdfPCell(new Phrase((vendorItemArrayList.get(i).getBarcode()))); FormatCell(cells[0]);
//                cells[1] = new PdfPCell(new Phrase((vendorItemArrayList.get(i).getItemName()))); FormatCell(cells[1]);
//                cells[2] = new PdfPCell(new Phrase(String.valueOf((vendorItemArrayList.get(i).getPrice())))); FormatCell(cells[2]);
//                cells[2].setHorizontalAlignment(Element.ALIGN_RIGHT);
//
//                table.addCell(cells[0]);
//                table.addCell(cells[1]);
//                table.addCell(cells[2]);
//                table.completeRow();
//            }
//
//            cells[0] = new PdfPCell(new Phrase("Total"));
//            cells[0].setColspan(2);
//            FormatHeaderCell(cells[0]);
//            cells[2] = new PdfPCell(new Phrase(tv_total_price.getText().toString()));
//            cells[2].setHorizontalAlignment(Element.ALIGN_RIGHT);
//            FormatHeaderCell(cells[2]);
//            cells[0].setBorderWidthBottom(1);
//            cells[0].setBorderWidthTop(1);
//            cells[0].setBorderWidthLeft(0);
//            cells[0].setBorderWidthRight(0);
//            cells[2].setBorderWidthBottom(1);
//            cells[2].setBorderWidthTop(1);
//            cells[2].setBorderWidthLeft(0);
//            cells[2].setBorderWidthRight(0);
//            table.addCell(cells[0]);
//            table.addCell(cells[2]);
//            table.completeRow();
//
//
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        finally {
//            //Step 5: Close the document
//            doc.close();
//
//        }
//         openPdf();
    }

    void openPdf(){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";
        File file = new File(path, "invoice1.pdf");

        intent.setDataAndType( Uri.fromFile( file ) , "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }

}
