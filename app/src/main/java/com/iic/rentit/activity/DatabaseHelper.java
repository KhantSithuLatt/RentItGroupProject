package com.iic.rentit.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.iic.rentit.domain.UserDomain;
import com.iic.rentit.domain.carDomain;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DATABASE_NAME = "CarDatabase";
    private static final String CarDetailTable = "CarDetails";
    private static final String COLUMN_CAR_ID = "id";
    private static final String COLUMN_ORDERNO = "orderNo";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_ENGINE = "engine";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_KILO = "kilo";
    private static final String COLUMN_ACCELERATION = "Acc";
    private static final String COLUMN_SPEED = "speed";
    private static final String COLUMN_GEAR = "gear";


    //User and Admin tables
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ADMIN = "admin_table";

    //Columns for user and admin tables
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String COLUMN_ADDRESS = "Address";
    private static final String COLUMN_NRC_NUMBER = "NRCNumber";
    private static final String COLUMN_LICENSE_NUMBER = "LicenseNumber";

    //Admin columns
    private static final String COLUMN_ADMIN_ID = "admin_id";
    private static final String COLUMN_ADMIN_USERNAME = "admin_username";
    private static final String COLUMN_ADMIN_PASSWORD = "admin_password";

    //order table and Columns
    private static final String TABLE_ORDER = "order_table";

    // Columns for the order table
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_CAR_NAME = "car_name";
    private static final String COLUMN_CAR_PRICE = "car_price";


    //Admin Table
    private static final String CREATE_ADMIN_TABLE =
            "CREATE TABLE " + TABLE_ADMIN + "(" +
                    COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ADMIN_USERNAME + " TEXT, " +
                    COLUMN_ADMIN_PASSWORD + " TEXT" +
                    ")";

    //User Table
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT,"  +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_NRC_NUMBER + " TEXT, " +
                    COLUMN_LICENSE_NUMBER + " TEXT" +
                    ")";

    //CarDetail Table
    private static final String createCarDetailTable = "CREATE TABLE " + CarDetailTable + " (" +
            COLUMN_ORDERNO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CAR_ID + " INTEGER, " +
            COLUMN_IMAGE + " BLOB, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_PRICE + " INTEGER, " +
            COLUMN_ENGINE + " TEXT, " +
            COLUMN_TYPE + " TEXT, " +
            COLUMN_KILO + " INTEGER, " +
            COLUMN_ACCELERATION + " INTEGER, " +
            COLUMN_SPEED + " INTEGER, " +
            COLUMN_GEAR + " TEXT" +
            ");";

    //ORDER TABLE
    private static final String CREATE_ORDER_TABLE =
            "CREATE TABLE " + TABLE_ORDER + "(" +
                    COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_ID + " INTEGER, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_CAR_ID + " INTEGER, " +
                    COLUMN_CAR_NAME + " TEXT, " +
                    COLUMN_CAR_PRICE + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "), " +
                    "FOREIGN KEY(" + COLUMN_CAR_ID + ") REFERENCES " + CarDetailTable + "(" + COLUMN_CAR_ID + ")" +
                    ");";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createCarDetailTable);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + CarDetailTable);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }

    public boolean insertDetails(Bitmap image, String name, String description, double price, String engine, String type, double kilo, double acce, double speed, String gear) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, bitmapToByteArray(image));
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRICE, price);
        contentValues.put(COLUMN_ENGINE, engine);
        contentValues.put(COLUMN_TYPE, type);
        contentValues.put(COLUMN_KILO, kilo);
        contentValues.put(COLUMN_ACCELERATION, acce);
        contentValues.put(COLUMN_SPEED, speed);
        contentValues.put(COLUMN_GEAR, gear);


        long result = db.insert(CarDetailTable, null, contentValues);
        return result != -1;
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }



    public ArrayList<carDomain> getCarDomain() {
        ArrayList<carDomain> carAdd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + COLUMN_ORDERNO + "," + COLUMN_IMAGE + "," + COLUMN_NAME + "," +
                COLUMN_DESCRIPTION + "," + COLUMN_PRICE + "," + COLUMN_ENGINE + "," + COLUMN_TYPE + "," + COLUMN_KILO +
                "," + COLUMN_ACCELERATION + "," + COLUMN_SPEED + "," + COLUMN_GEAR +  // Add new columns
                " FROM " + CarDetailTable, null);
        if (cursor.getCount() > 0) {
            try {
                while (cursor.moveToNext()) {
                    carDomain domain = new carDomain();
                    domain.setOrderno(cursor.getString(0));
                    byte[] imageByte = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    domain.setImage(bitmap);
                    domain.setTitle(cursor.getString(2));
                    domain.setDescription(cursor.getString(3));
                    domain.setPrice(cursor.getString(4));
                    domain.setEngine(cursor.getString(5));
                    domain.setType(cursor.getString(6));
                    domain.setKilo(cursor.getString(7));
                    domain.setAcc(cursor.getString(8)); // Set acceleration
                    domain.setSpeed(cursor.getString(9)); // Set speed
                    domain.setGear(cursor.getString(10)); // Set gear
                    carAdd.add(domain);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cursor.close(); // Close cursor after use
        database.close();
        return carAdd;
    }

    //get user details on mathcing username
    @SuppressLint("Range")
    public static UserDomain getUserDetailsByUsername(Context context, String username) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context); // Assuming a no-argument constructor exists
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        // Your existing code
    String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        UserDomain user = null;

        if (cursor.moveToFirst()) {
            user = new UserDomain();
            user.setUserid(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            user.setNrcNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NRC_NUMBER)));
            user.setLiscenNumber(cursor.getString(cursor.getColumnIndex(COLUMN_LICENSE_NUMBER)));
        }

        cursor.close();
        return user;
    }



    public boolean insertDataUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkUserName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUserNamePassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean insertDataAdmin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ADMIN_USERNAME, username);
        contentValues.put(COLUMN_ADMIN_PASSWORD, password);
        long result = db.insert(TABLE_ADMIN, null, contentValues);
        return result != -1;
    }

    public boolean checkUserNameAdmin(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN, null, COLUMN_ADMIN_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUserNamePasswordAdmin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN, null, COLUMN_ADMIN_USERNAME + "=? AND " + COLUMN_ADMIN_PASSWORD + "=?", new String[]{username, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean insertUserProfileData(String Username, String phoneNumber, String address, String NRCNumber, String licenseNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PHONE_NUMBER, phoneNumber);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_NRC_NUMBER, NRCNumber);
        contentValues.put(COLUMN_LICENSE_NUMBER, licenseNumber);
        long result = db.update(TABLE_USERS, contentValues, COLUMN_USERNAME + "=?", new String[]{Username});
        return result != -1;
    }

    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    //Order helper methods
    public void addOrder(int userId, String username, int carId, String carName, double carPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_CAR_ID, carId);
        values.put(COLUMN_CAR_NAME, carName);
        values.put(COLUMN_CAR_PRICE, carPrice);
        // Inserting Row
        db.insert(TABLE_ORDER, null, values);
        // Closing database connection
        db.close();
    }


    public List<String> ViewOrders() {
        List<String> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ORDER, null);
        if (cursor.moveToFirst()) {
            do {
                String order = "Order ID: " + cursor.getInt(0) +
                        ", User ID: " + cursor.getInt(1) +
                        ", Username: " + cursor.getString(2) +
                        ", Car ID: " + cursor.getInt(3) +
                        ", Car Name: " + cursor.getString(4) +
                        ", Car Price: " + cursor.getInt(5);
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orderList;
    }





}
