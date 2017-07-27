package com.example.omistaja.crm;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

// Luokka, jonka tehtävänä on kommunikoida tietokannan kanssa

public class dBHandler extends SQLiteOpenHelper {

    private static final int dB_VERSION = 1;
    private static final String DATABASE_NAME = "Customers";
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String KEY_ID = "customer_id";
    private static final String KEY_NAME = "customer_name";
    private static final String KEY_ADDR = "customer_address";
    private static final String KEY_PNUM = "customer_phone";


    public dBHandler(Context context) {
        super(context, DATABASE_NAME, null, dB_VERSION);
    }

    // Luo kanta

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDR + " TEXT," + KEY_PNUM + " TEXT" + ")";
        db.execSQL(CREATE_CUSTOMERS_TABLE);
    }

    // Tiputa vanha taulu, jos sellainen on

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);
    }

    // Lisää asiakas rekisteriin

    public void addCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues customerInfo = new ContentValues();
        customerInfo.put(KEY_NAME, customer.getName());
        customerInfo.put(KEY_ADDR, customer.getAddress());
        customerInfo.put(KEY_PNUM, customer.getPhoneNumber());

        db.insert(TABLE_CUSTOMERS, null, customerInfo);
        db.close();
    }

    // Hae asiakasta id:n perusteella

    public Customer getCustomer(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.query(TABLE_CUSTOMERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_ADDR, KEY_PNUM}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (query != null)
            query.moveToFirst();
        Customer customerInfo = new Customer(Integer.parseInt(query.getString(0)),
                query.getString(1), query.getString(2), query.getString(3));

        return customerInfo;
    }

    // Hae kaikki asiakkaat listaan

    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<Customer>();
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor query = db.rawQuery(selectQuery, null);

        if (query.moveToFirst()) {
            do {

                Customer customerInfo = new Customer();
                customerInfo.setID(Integer.parseInt(query.getString(0)));
                customerInfo.setName(query.getString(1));
                customerInfo.setAddress(query.getString(2));
                customerInfo.setPhoneNumber(query.getString(3));
                customerList.add(customerInfo);

            } while (query.moveToNext());
        }

        return customerList;
    }

    // Etsi asiakasta kaikin mahdollisin muuttujin

    public List<Customer> searchCustomers(int id, String name, String address, String pNum) {

        List<Customer> customerList = new ArrayList<Customer>();
        SQLiteDatabase db = this.getWritableDatabase();

        String WHERE = "" + KEY_ID + "='" + id + "' OR " + KEY_NAME + "='" + name + "' OR " + KEY_ADDR + "='" + address + "' OR " + KEY_PNUM + "='" + pNum + "' ";
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMERS + " WHERE " + WHERE;
        Cursor query = db.rawQuery(selectQuery, null);

        if (query.moveToFirst()) {
            do {

                Customer customerInfo = new Customer();
                customerInfo.setID(Integer.parseInt(query.getString(0)));
                customerInfo.setName(query.getString(1));
                customerInfo.setAddress(query.getString(2));
                customerInfo.setPhoneNumber(query.getString(3));
                customerList.add(customerInfo);

            } while (query.moveToNext());
        }

        return customerList;
    }

    // Poista asiakas kannasta

    public void deleteCustomer(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMERS, "customer_id=?", new String[]{String.valueOf(id)});
        db.close();

    }

    // Tulosta asiakkaiden määrä

    public int getRowCount() {

        String query = "SELECT  * FROM " + TABLE_CUSTOMERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int rowCount = cursor.getCount();
        cursor.close();
        return rowCount;
    }

    // Poista kaikki asiakkaat kannasta

    public void deleteAllCustomers() {

        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CUSTOMERS, null, null);
        db.close();
    }

    // Päivitä asiakkaan tiedot saaduilla parametreilla

    public void updateCustomer(int id, String name, String address, String PhoneNumber) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_NAME, name);
        newValues.put(KEY_ADDR, address);
        newValues.put(KEY_PNUM, PhoneNumber);

        db.update(TABLE_CUSTOMERS, newValues, "customer_id=" + id + "", null);
    }

    // Asiakkaan tallennusta varten haetaan suurin id, jota korotetaan yhdellä, jottei tule sotkua samojen id:n kanssa

    public int getBiggestId() {

        SQLiteDatabase db = this.getReadableDatabase();
        final SQLiteStatement stmt = db.compileStatement("SELECT MAX(" + KEY_ID + ") FROM " + TABLE_CUSTOMERS + "");
        return (int) stmt.simpleQueryForLong();
    }
}
