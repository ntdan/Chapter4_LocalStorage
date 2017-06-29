package vn.cusc.chapter4_localstorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import vn.cusc.chapter4_localstorage.db.Customer;
import vn.cusc.chapter4_localstorage.db.MyDB;

public class SQLite_Demo extends AppCompatActivity {

    MyDB customerDB;
    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite__demo);

        customerDB = new MyDB(SQLite_Demo.this);

        //customers = customerDB.list();
        //Toast.makeText(SQLite_Demo.this, "Customer: " + customers.size(), Toast.LENGTH_SHORT).show();

        customerDB.add("ABC", "ABC", "ABC");
        customers = customerDB.list();
        Toast.makeText(SQLite_Demo.this, "Customer: " + customers.size(), Toast.LENGTH_SHORT).show();
    }
}
