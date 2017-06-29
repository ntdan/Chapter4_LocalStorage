package vn.cusc.chapter4_localstorage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ntdan on 6/27/2017.
 */
public class MyDB extends SQLiteOpenHelper {


    public MyDB(Context context) {
        super(context, "shop.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE \"customer\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"fullname\" VARCHAR, \"email\" VARCHAR, \"image\" VARCHAR);\n";//+
        //  "INSERT INTO \"customer\" VALUES(1,'Nguyen Van Mit','nvmit@gmail.com','mit.png');\n" +
        //  "INSERT INTO \"customer\" VALUES(2,'Tran Van Cam','tvcam@gmail.com','cam.png');";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long add(String fullname, String email, String image) {
        ContentValues newRow = new ContentValues();
        newRow.put("fullname", fullname);
        newRow.put("email", email);
        newRow.put("image", image);

        long newID = getWritableDatabase().insert("customer", null, newRow);
        return newID;
    }

    public boolean update(int id, String fullname, String email, String image) {
        ContentValues newRow = new ContentValues();
        newRow.put("fullname", fullname);
        newRow.put("email", email);
        newRow.put("image", image);
        newRow.put("id", id);

        //long newID = getWritableDatabase().update("customer", newRow,"id=? and fullname=?", new String[]{id+"", fullname});
        long updateID = getWritableDatabase().update("customer", newRow, "id=?", new String[]{id + ""});
        if (updateID > 0)
            return true;
        else
            return false;
        //return updateID > 0 ? true : false;
    }

    public boolean delete(int id) {
        long deleteID = getWritableDatabase().delete("customer", "id=?", new String[]{id + ""});
        return deleteID > 0 ? true : false;
    }

    public ArrayList<Customer> list() {
        ArrayList<Customer> list = new ArrayList<>();
        Cursor cursor;
        cursor = getReadableDatabase().rawQuery("select id, fullname, email, image from customer", null);

        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setId(cursor.getInt(0));
                customer.setFullname(cursor.getString(1));
                customer.setEmail(cursor.getString(2));
                customer.setImage(cursor.getString(3));

                list.add(customer);
            } while (cursor.moveToNext());

            return list;
        } else {
            return null;
        }
    }
}