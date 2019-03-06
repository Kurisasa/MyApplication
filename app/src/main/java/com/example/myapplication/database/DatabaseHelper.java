package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.model.Device;
import com.example.myapplication.model.Transaction;
import com.example.myapplication.model.TransactionLineItem;
import com.example.myapplication.model.User;
import com.example.myapplication.model.Vendor;
import com.example.myapplication.model.VendorItem;

import java.util.ArrayList;

import static com.example.myapplication.MiniPosApp.TAG;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "bigradap.db";
    // User table names
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    //Register device
    private static final String TABLE_DEVICE= "FIN_Device";
    private static final String COLUMN_DEVICE_GUID = "DeviceGuid";
    private static final String COLUMN_DEVICE_MAKE= "DeviceMake";
    private static final String COLUMN_DEVICE_MODEL = "DeviceModel";
    private static final String COLUMN_DEVICE_PURCHASE_DATE = "DevicePurchaseDate";
    private static final String COLUMN_DEVICE_ISACTIVE = "DeviceIsActive";
    private static final String COLUMN_DEVICE_SERIAL_NUMBER = "DeviceSerialNumber";
    //register vendor item
    private static final String TABLE_VENDOR_ITEM = "FIN_VendorItem";
    private static final String COLUMN_VENDOR_ITEM_GUID = "VendorItemGuid";
    private static final String COLUMN_VENDOR_GUID = "vendorGuid";
    public static final  String  COLUMN_BARCODE= "barcode";
    private static final String COLUMN_ITEM_GUID= "ItemGuid";
    private static final String COLUMN_TAX_PERCENTAGE = "TaxPercentage";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_NAMER= "Name";
    //register vendor
    private static final String TABLE_VENDOR = "FIN_Vendor";
    private static final String COLUMN_VENDORS_GUID = "VendorGuid";
    private static final String COLUMN_VENDOR_NUMBER = "VendorNumber";
    private static final String COLUMN_VENDOR_NAME = "VendorName";
    private static final String COLUMN_VENDOR_DESCRIPTION = "vendorDescription";
    private static final String COLUMN_VENDOR_ADDRESS = "Address";
    private static final String COLUMN_VENDOR_PHONE_NUMBER = "PhoneNo";
    private static final String COLUMN_VENDOR_EMAIL_ADDRESS = "Email";
    //add transaction
    private static final String TABLE_TRANSACTION = "FIN_Transaction";
    private static final String COLUMN_TRANSACTION_GUID = "TransactionGuid";
    private static final String COLUMN_VENDORR_GUID = "VendorGuid";
    private static final String COLUMN_PERIOD_GUID = "PeriodGuid";
    private static final String COLUMN_TRANSACTION_TYPE = "TransactionTypeId";
    private static final String COLUMN_DOCUMENT_NUMBER = "DocumentNumber";
    private static final String COLUMN_TAXABLE_TOTAL = "TaxableTotal";
    private static final String COLUMN_VAT = "VAT";
    private static final String COLUMN_TOTAL = "Total";
    private static final String COLUMN_TRANSACTION_DTS = "TransactionDTS";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_TENDER = "Tender";
    private static final String COLUMN_CHANGE = "Change";
    //add transaction type
    private static final String TABLE_TRANSACTION_TYPE = "FIN_TransactionType";
    private static final String COLUMN_TRANSACTION_TYPEID = "TransactionTypeId";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DESCRIPTION= "Description";
    //add transaction line item
    private static final String TABLE_TRANSACTION_LINE_ITEM = "FIN_TransactionLineItem";
    private static final String COLUMN_LINE_ITEM_GUID = "LineItemGuid";
    private static final String COLUMN_TRANSACTIONS_GUID = "TransactionGuid";
    private static final String COLUMN_BARCODESS = "Barcode";
    private static final String COLUMN_PRICES = "Price";
    private static final String COLUMN_VATS = "Vat";
    private static final String COLUMN_QUANTITY = "Quantity";
    private static final String COLUMN_LINE_TOTAL = "LineTotal";
    private static final String COLUMN_NAMES= "Name";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
            + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT"
            + ")";

    // create table sql query
    private String CREATE_TABLE_DEVICE = "CREATE TABLE " + TABLE_DEVICE
            + "("
            + COLUMN_DEVICE_GUID + " TEXT ,"
            + COLUMN_DEVICE_MAKE + " TEXT,"
            + COLUMN_DEVICE_MODEL + " TEXT,"
            + COLUMN_DEVICE_PURCHASE_DATE + " TEXT,"
            + COLUMN_DEVICE_ISACTIVE + " TEXT,"
            + COLUMN_DEVICE_SERIAL_NUMBER + " TEXT"
            + ")";

    // create table sql query
    private String CREATE_TABLE_VENDOR_ITEM = "CREATE TABLE " + TABLE_VENDOR_ITEM
            + "("
            + COLUMN_VENDOR_ITEM_GUID + " TEXT,"
            + COLUMN_VENDOR_GUID + " TEXT,"
            + COLUMN_BARCODE + " TEXT,"
            + COLUMN_ITEM_GUID + " TEXT,"
            + COLUMN_TAX_PERCENTAGE + " REAL,"
            + COLUMN_PRICE + " REAL,"
            + COLUMN_NAMER + " TEXT"
            + ")";

    // create table sql query
    private String CREATE_TABLE_VENDOR = "CREATE TABLE " + TABLE_VENDOR
            + "("
            + COLUMN_VENDORS_GUID+ " TEXT,"
            + COLUMN_VENDOR_NUMBER + " TEXT,"
            + COLUMN_VENDOR_NAME + " TEXT,"
            + COLUMN_VENDOR_DESCRIPTION + " TEXT,"
            + COLUMN_VENDOR_ADDRESS + " TEXT,"
            + COLUMN_VENDOR_PHONE_NUMBER+ " TEXT,"
            + COLUMN_VENDOR_EMAIL_ADDRESS +  " TEXT"
            + ")";

    // create table sql query
    private String CREATE_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_TRANSACTION
            + "("
            + COLUMN_TRANSACTION_GUID + " TEXT,"
            + COLUMN_VENDORR_GUID + " TEXT,"
            + COLUMN_PERIOD_GUID + " TEXT,"
            + COLUMN_TRANSACTION_TYPE + " TEXT,"
            + COLUMN_DOCUMENT_NUMBER + " TEXT,"
            + COLUMN_TAXABLE_TOTAL + " TEXT,"
            + COLUMN_VAT + " TEXT,"
            + COLUMN_TOTAL + " TEXT,"
            + COLUMN_TRANSACTION_DTS + " TEXT,"
            + COLUMN_LOCATION + " TEXT,"
            + COLUMN_TENDER + " TEXT,"
            + COLUMN_CHANGE + " TEXT"
            + ")";

    // create table sql query
    private String CREATE_TABLE_TRANSACTION_TYPE = "CREATE TABLE " + TABLE_TRANSACTION_TYPE
            + "("
            + COLUMN_TRANSACTION_TYPEID + " TEXT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT"
            + ")";

    // create table sql query
    private String CREATE_TABLE_TRANSACTION_LINE_ITEM  = "CREATE TABLE " + TABLE_TRANSACTION_LINE_ITEM
            + "("
            + COLUMN_LINE_ITEM_GUID + " TEXT,"
            + COLUMN_TRANSACTIONS_GUID + " TEXT,"
            + COLUMN_BARCODESS + " TEXT,"
            + COLUMN_PRICES + " TEXT,"
            + COLUMN_VATS + " TEXT,"
            + COLUMN_QUANTITY + " TEXT,"
            + COLUMN_LINE_TOTAL + " TEXT,"
            + COLUMN_NAMES + " TEXT"
            + ")";


    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TABLE_DEVICE);
        db.execSQL(CREATE_TABLE_VENDOR_ITEM);
        db.execSQL(CREATE_TABLE_VENDOR);
        db.execSQL(CREATE_TABLE_TRANSACTION);
        db.execSQL(CREATE_TABLE_TRANSACTION_TYPE);
        db.execSQL(CREATE_TABLE_TRANSACTION_LINE_ITEM);
    }
    // drop table sql query
    private String DROP_TABLE_TRANSACTION = "DROP TABLE IF EXISTS " + TABLE_TRANSACTION;
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_TABLE_DEVICE = "DROP TABLE IF EXISTS " + TABLE_DEVICE;
    private String DROP_TABLE_VENDOR_ITEM= "DROP TABLE IF EXISTS " + TABLE_VENDOR_ITEM;
    private String DROP_TABLE_VENDOR = "DROP TABLE IF EXISTS " + TABLE_VENDOR;
    private String DROP_TABLE_TRANSACTION_TYPE = "DROP TABLE IF EXISTS " + TABLE_TRANSACTION_TYPE;
    private String DROP_TABLE_TRANSACTION_LINE_ITEM = "DROP TABLE IF EXISTS " + TABLE_TRANSACTION_LINE_ITEM;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_TABLE_DEVICE);
        db.execSQL(DROP_TABLE_VENDOR);
        db.execSQL(DROP_TABLE_TRANSACTION);
        db.execSQL(DROP_TABLE_VENDOR_ITEM);
        db.execSQL(DROP_TABLE_TRANSACTION_TYPE);
        db.execSQL(DROP_TABLE_TRANSACTION_LINE_ITEM);
        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to create user record
     *
     * @param FINDevice
     */
    public void addDevice(Device FINDevice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEVICE_GUID, FINDevice.getDeviceGuid());
        values.put(COLUMN_DEVICE_MAKE, FINDevice.getDeviceMake());
        values.put(COLUMN_DEVICE_MODEL, FINDevice.getDeviceModel());
        values.put(COLUMN_DEVICE_PURCHASE_DATE, FINDevice.getDevicePurchaseDate());
        values.put(COLUMN_DEVICE_ISACTIVE, FINDevice.getDeviceIsActive());
        values.put(COLUMN_DEVICE_SERIAL_NUMBER, FINDevice.getDeviceSerialNumber());
        // Inserting Row
        db.insert(TABLE_DEVICE, null, values);
        db.close();
    }

    /**
     * This method to update FINDevice record
     *
     * @param FINDevice
     */
    public void updateDevice(Device FINDevice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEVICE_MAKE, FINDevice.getDeviceMake());
        values.put(COLUMN_DEVICE_MODEL, FINDevice.getDeviceModel());
        values.put(COLUMN_DEVICE_PURCHASE_DATE, FINDevice.getDevicePurchaseDate());
        values.put(COLUMN_DEVICE_ISACTIVE, FINDevice.getDeviceIsActive());
        values.put(COLUMN_DEVICE_SERIAL_NUMBER, FINDevice.getDeviceSerialNumber());


        // updating row
        db.update(TABLE_DEVICE, values, COLUMN_DEVICE_SERIAL_NUMBER + " = ?",
                new String[]{String.valueOf(FINDevice.getDeviceSerialNumber())});
        db.close();
    }

    /**
     * This method is to create FINVendorItem record
     *
     * @param FINVendorItem
     */
    public long addVendorItem(VendorItem FINVendorItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VENDOR_ITEM_GUID, FINVendorItem.getVendorItemGuid());
        values.put(COLUMN_VENDOR_GUID, FINVendorItem.getVendorGuid());
        values.put(COLUMN_BARCODE, FINVendorItem.getBarcode());
        values.put(COLUMN_ITEM_GUID, FINVendorItem.getItemGuid());
        values.put(COLUMN_TAX_PERCENTAGE, FINVendorItem.getTxPercentage());
        values.put(COLUMN_PRICE, FINVendorItem.getPrice());
        values.put(COLUMN_NAMER, FINVendorItem.getItemName());

        // Inserting Row
        long vendorItem_id =  db.insert(TABLE_VENDOR_ITEM, null, values);
        db.close();

        return vendorItem_id;
    }

    public VendorItem search(String barcode) {

        VendorItem FINVendorItem = null;
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_VENDOR_ITEM + " WHERE " + COLUMN_BARCODE  + " = " + barcode;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor == null && cursor.getCount() == 0){
            return null ;
        }else if (cursor.moveToFirst()) {   // looping through all rows and adding to list
            do {
                FINVendorItem = new VendorItem();
                FINVendorItem.setVendorItemGuid(cursor.getString(cursor.getColumnIndex(COLUMN_VENDOR_ITEM_GUID)));
                FINVendorItem.setVendorGuid(cursor.getString(cursor.getColumnIndex(COLUMN_VENDOR_GUID)));
                FINVendorItem.setBarcode(cursor.getString(cursor.getColumnIndex(COLUMN_BARCODE)));
                FINVendorItem.setItemGuid(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_GUID)));
                FINVendorItem.setTxPercentage(cursor.getDouble(cursor.getColumnIndex(COLUMN_TAX_PERCENTAGE)));
                FINVendorItem.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                FINVendorItem.setItemName(cursor.getString(cursor.getColumnIndex(COLUMN_NAMER)));
            } while (cursor.moveToNext());
        }

   return  FINVendorItem;

    }

    /**
     * This method is to create FINVendor record
     *
     * @param FINVendor
     */
    public long  addVendor(Vendor FINVendor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VENDORS_GUID, FINVendor.getVendorGuid());
        values.put(COLUMN_VENDOR_NUMBER, FINVendor.getVendorNumber());
        values.put(COLUMN_VENDOR_NAME, FINVendor.getVendorName());
        values.put(COLUMN_VENDOR_DESCRIPTION, FINVendor.getVendorDescription());
        values.put(COLUMN_VENDOR_ADDRESS, FINVendor.getAddress());
        values.put(COLUMN_VENDOR_PHONE_NUMBER, FINVendor.getPhoneNo());
        values.put(COLUMN_VENDOR_EMAIL_ADDRESS, FINVendor.getEmail());
        // Inserting Row
       long vendor_id = db.insert(TABLE_VENDOR, null, values);
        db.close();

        return vendor_id;
    }

    /**
     * This method to update FINVendor record
     *
     * @param FINVendor
     */
    public void updateVendor(Vendor FINVendor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VENDOR_NUMBER, FINVendor.getVendorNumber());
        values.put(COLUMN_VENDOR_NAME, FINVendor.getVendorName());
        values.put(COLUMN_VENDOR_DESCRIPTION, FINVendor.getVendorDescription());
        values.put(COLUMN_VENDOR_ADDRESS, FINVendor.getAddress());
        values.put(COLUMN_VENDOR_PHONE_NUMBER, FINVendor.getPhoneNo());
        values.put(COLUMN_VENDOR_EMAIL_ADDRESS, FINVendor.getEmail());

        // updating row
        db.update(TABLE_VENDOR, values, COLUMN_VENDOR_NUMBER + " = ?",
                new String[]{String.valueOf(FINVendor.getVendorNumber())});
        db.close();
    }

    /**
     * This method is to create FINTransaction record
     *
     * @param FINTransaction
     */
    public Transaction addTransaction(Transaction FINTransaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TRANSACTION_GUID, FINTransaction.getTransactionGuid());
        values.put(COLUMN_VENDORR_GUID, FINTransaction.getVendorGuid());
        values.put(COLUMN_PERIOD_GUID, FINTransaction.getPeriodGuid());
        values.put(COLUMN_TRANSACTION_TYPE, FINTransaction.getTransactionTypeId());
        values.put(COLUMN_DOCUMENT_NUMBER, FINTransaction.getDocumentNumber());
        values.put(COLUMN_TAXABLE_TOTAL, FINTransaction.getTaxableTotal());
        values.put(COLUMN_VAT, FINTransaction.getVAT());
        values.put(COLUMN_TOTAL, FINTransaction.getTotal());
        values.put(COLUMN_TRANSACTION_DTS, FINTransaction.getTransactionDTS());
        values.put(COLUMN_LOCATION, FINTransaction.getLocation());
        values.put(COLUMN_TENDER, FINTransaction.getTender());
        values.put(COLUMN_CHANGE, FINTransaction.getChange());

        // Inserting Row
        long transaction_id = db.insert(TABLE_TRANSACTION, null, values);

        //Save your line Item

        //for (int i = 0; i < FINTransaction.getLineItemList().size(); i++){
            for(TransactionLineItem item: FINTransaction.getLineItemList())
            {
                addTransactionItem(item);
//            TransactionLineItem transactionLineItem = new TransactionLineItem();
//            transactionLineItem.setBarcode(FINTransaction.getLineItemList().get(i).getBarcode());
//            transactionLineItem.setLineItemGuid(FINTransaction.getLineItemList().get(i).getLineItemGuid());
//            transactionLineItem.setLineTotal(FINTransaction.getLineItemList().get(i).getPrice());
//            transactionLineItem.setName(FINTransaction.getLineItemList().get(i).getName());
//            transactionLineItem.setQuantity(1);
//            transactionLineItem.setTransactionGuid(FINTransaction.getTransactionGuid());
//            transactionLineItem.setVat(FINTransaction.getLineItemList().get(i).getVat());
//            transactionLineItem.setPrice(FINTransaction.getLineItemList().get(i).getPrice());

            // Inserting Row
           // long transaction_item_id = db.insert(TABLE_TRANSACTION_LINE_ITEM, null, values);
        }

        db.close();

        //Return the full transaction with line items
        return FINTransaction;
    }

    public Transaction  getTransaction(String TransactionGuid) {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION + " WHERE " + COLUMN_TRANSACTION_GUID  + " = '"  + TransactionGuid + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Transaction FINTransaction = new Transaction();

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                FINTransaction.setTransactionGuid(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION_GUID)));
                FINTransaction.setVendorGuid(cursor.getString(cursor.getColumnIndex(COLUMN_VENDORR_GUID)));
                FINTransaction.setPeriodGuid(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_GUID)));
                FINTransaction.setDocumentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_DOCUMENT_NUMBER)));
                FINTransaction.setTransactionTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION_TYPE)));
                FINTransaction.setTaxableTotal(cursor.getDouble(cursor.getColumnIndex(COLUMN_TAXABLE_TOTAL)));
                FINTransaction.setVAT(cursor.getDouble(cursor.getColumnIndex(COLUMN_VAT)));
                FINTransaction.setTotal(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL)));
                FINTransaction.setTransactionDTS(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION_DTS)));
                FINTransaction.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
                FINTransaction.setTender(cursor.getDouble(cursor.getColumnIndex(COLUMN_TENDER)));
                FINTransaction.setChange(cursor.getDouble(cursor.getColumnIndex(COLUMN_CHANGE)));
                // Adding FINTransaction record to list
               //FINTransaction.setLineItemList();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        ArrayList<TransactionLineItem> lineItems = GetLineIteems(FINTransaction.getTransactionGuid());
        FINTransaction.setLineItemList(lineItems);

        return FINTransaction;
    }

    private ArrayList<TransactionLineItem> GetLineIteems(String TransactionGuid)
    {
        ArrayList<TransactionLineItem> lineItems = new ArrayList<TransactionLineItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION_LINE_ITEM + " WHERE " + COLUMN_TRANSACTIONS_GUID  + " = '"  + TransactionGuid + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TransactionLineItem lineItem = new TransactionLineItem();
                lineItem.setLineItemGuid(cursor.getString(cursor.getColumnIndex(COLUMN_LINE_ITEM_GUID)));
                lineItem.setTransactionGuid(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTIONS_GUID)));
                lineItem.setBarcode(cursor.getString(cursor.getColumnIndex(COLUMN_BARCODESS)));
                lineItem.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICES)));
                lineItem.setVat(cursor.getDouble(cursor.getColumnIndex(COLUMN_VATS)));
                lineItem.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                lineItem.setLineTotal(cursor.getDouble(cursor.getColumnIndex(COLUMN_LINE_TOTAL)));
                lineItem.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAMES)));
                lineItems.add(lineItem);

                // Adding FINTransaction record to list
                //FINTransaction.setLineItemList();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lineItems;

    }

    /**
     * This method is to create TransactionLineItem record
     *
     * @param transactionLineItem
     */
    public void addTransactionItem(TransactionLineItem transactionLineItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LINE_ITEM_GUID, transactionLineItem.getLineItemGuid());
        values.put(COLUMN_TRANSACTIONS_GUID, transactionLineItem.getTransactionGuid());
        values.put(COLUMN_BARCODESS, transactionLineItem.getBarcode());
        values.put(COLUMN_PRICES, transactionLineItem.getPrice());
        values.put(COLUMN_VATS, transactionLineItem.getVat());
        values.put(COLUMN_QUANTITY, transactionLineItem.getQuantity());
        values.put(COLUMN_LINE_TOTAL, transactionLineItem.getLineTotal());
        values.put(COLUMN_NAMES, transactionLineItem.getName());

        // Inserting Row
        long transactionItem_id = db.insert(TABLE_TRANSACTION_LINE_ITEM, null, values);
        db.close();
    }

    /**
     * This method to update TransactionLineItem record
     *
     * @param transactionLineItem
     */
    public void updateTransactionItem(TransactionLineItem transactionLineItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LINE_ITEM_GUID, transactionLineItem.getLineItemGuid());
        values.put(COLUMN_TRANSACTIONS_GUID, transactionLineItem.getTransactionGuid());
        values.put(COLUMN_BARCODESS, transactionLineItem.getBarcode());
        values.put(COLUMN_PRICES, transactionLineItem.getPrice());
        values.put(COLUMN_VATS, transactionLineItem.getVat());
        values.put(COLUMN_QUANTITY, transactionLineItem.getQuantity());
        values.put(COLUMN_LINE_TOTAL, transactionLineItem.getLineTotal());
        values.put(COLUMN_NAMES, transactionLineItem.getName());
        // updating row
        db.update(TABLE_TRANSACTION_LINE_ITEM, values, COLUMN_TRANSACTION_TYPE + " = ?",
                new String[]{String.valueOf(transactionLineItem.getLineItemGuid())});
        db.close();
    }



    /**
     * This method to update FINTransaction record
     *
     * @param FINTransaction
     */
    public void updateTransaction(Transaction FINTransaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TRANSACTION_GUID, FINTransaction.getTransactionGuid());
        values.put(COLUMN_VENDORR_GUID, FINTransaction.getVendorGuid());
        values.put(COLUMN_PERIOD_GUID, FINTransaction.getPeriodGuid());
        values.put(COLUMN_TRANSACTION_TYPE, FINTransaction.getTransactionTypeId());
        values.put(COLUMN_DOCUMENT_NUMBER, FINTransaction.getDocumentNumber());
        values.put(COLUMN_TAXABLE_TOTAL, FINTransaction.getTaxableTotal());
        values.put(COLUMN_VAT, FINTransaction.getVAT());
        values.put(COLUMN_TOTAL, FINTransaction.getTotal());
        values.put(COLUMN_TRANSACTION_DTS, FINTransaction.getTransactionDTS());
        values.put(COLUMN_LOCATION, FINTransaction.getLocation());
        values.put(COLUMN_TENDER, FINTransaction.getTender());
        values.put(COLUMN_CHANGE, FINTransaction.getChange());
        // updating row
        db.update(TABLE_TRANSACTION, values, COLUMN_TRANSACTION_TYPE + " = ?",
                new String[]{String.valueOf(FINTransaction.getTransactionTypeId())});
        db.close();
    }

    /**
     * This method is to fetch all transaction and return the list of transaction records
     *
     * @return list
     */
    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> FINTransactionList = new ArrayList<Transaction>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transaction FINTransaction = new Transaction();
                FINTransaction.setTransactionGuid(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION_GUID)));
                FINTransaction.setVendorGuid(cursor.getString(cursor.getColumnIndex(COLUMN_VENDORR_GUID)));
                FINTransaction.setPeriodGuid(cursor.getString(cursor.getColumnIndex(COLUMN_PERIOD_GUID)));
                FINTransaction.setDocumentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_DOCUMENT_NUMBER)));
                FINTransaction.setTransactionTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION_TYPE)));
                FINTransaction.setTaxableTotal(cursor.getDouble(cursor.getColumnIndex(COLUMN_TAXABLE_TOTAL)));
                FINTransaction.setVAT(cursor.getDouble(cursor.getColumnIndex(COLUMN_VAT)));
                FINTransaction.setTotal(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL)));
                FINTransaction.setTransactionDTS(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSACTION_DTS)));
                FINTransaction.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
                FINTransaction.setTender(cursor.getDouble(cursor.getColumnIndex(COLUMN_TENDER)));
                FINTransaction.setChange(cursor.getDouble(cursor.getColumnIndex(COLUMN_CHANGE)));
                // Adding FINTransaction record to list
                FINTransactionList.add(FINTransaction);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return FINTransactionList;
    }


    /**
     * This method is to fetch all VendorItem and return the list of VendorItem records
     *
     * @return list
     */
    public ArrayList<VendorItem> getAllProduct() {
        ArrayList<VendorItem> vendorItems = new ArrayList<VendorItem>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VENDOR_ITEM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                VendorItem FINVendorItem = new VendorItem();
                FINVendorItem.setVendorItemGuid(cursor.getString(cursor.getColumnIndex(COLUMN_VENDOR_ITEM_GUID)));
                FINVendorItem.setBarcode(cursor.getString(cursor.getColumnIndex(COLUMN_VENDOR_GUID)));
                FINVendorItem.setBarcode(cursor.getString(cursor.getColumnIndex(COLUMN_BARCODE)));
                FINVendorItem.setItemGuid(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_GUID)));
                FINVendorItem.setTxPercentage(cursor.getDouble(cursor.getColumnIndex(COLUMN_TAX_PERCENTAGE)));
                FINVendorItem.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                vendorItems.add(FINVendorItem);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return vendorItems;

    }


    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, null, null);
        db.close();
    }

    /**
     * This method is to delete FINDevice record
     *
     * @param FINDevice
     */
    public void deleteDevice(Device FINDevice) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_DEVICE, COLUMN_DEVICE_SERIAL_NUMBER + " = ?",
                new String[]{String.valueOf(FINDevice.getDeviceSerialNumber())});
        db.close();
    }

    /**
     * This method is to delete product record
     *
     *
     */
    public void deleteAllProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_VENDOR_ITEM, null, null);
        db.close();
    }

    /**
     * This method is to delete FINVendorItem record
     *
     * @param FINVendorItem
     */
    public void deleteProduct(VendorItem FINVendorItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_VENDOR_ITEM, COLUMN_BARCODE + " = ?",
                new String[]{String.valueOf(FINVendorItem.getBarcode())});
        db.close();
    }

    /**
     * This method is to delete FINVendor record
     *
     * @param FINVendor
     */
    public void deleteVendor(Vendor FINVendor) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_VENDOR, COLUMN_VENDOR_NUMBER + " = ?",
                new String[]{String.valueOf(FINVendor.getVendorNumber())});
        db.close();
    }

    public void deleteAllVendor() {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_VENDOR, null, null);
        db.close();
    }

    /**
     * This method is to delete FINTransaction record
     *
     * @param FINTransaction
     */
    public void deleteTransaction(Transaction FINTransaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_TRANSACTION, COLUMN_TRANSACTION_TYPE + " = ?",
                new String[]{String.valueOf(FINTransaction.getTransactionTypeId())});
        db.close();
    }

    public void deleteAllTransaction() {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_TRANSACTION, null, null);
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean hasObject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_DEVICE + " WHERE " + COLUMN_DEVICE_GUID + " = ?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }

    public boolean checkVendor(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_VENDOR + " WHERE " + COLUMN_VENDOR_GUID + " = ?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }

    public boolean checkVendorItem(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_VENDOR_ITEM + " WHERE " + COLUMN_VENDOR_ITEM_GUID + " =?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }
}
