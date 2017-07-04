package vn.cusc.chapter4_localstorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import vn.cusc.chapter4_localstorage.db.MyDB;

public class Them extends AppCompatActivity {

    MyDB customerDB;
    EditText etTen, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        customerDB = new MyDB(Them.this);

        etTen = (EditText) findViewById(R.id.etTen);
        etEmail = (EditText) findViewById(R.id.etEmail);
    }

    public void them(View view) {
        customerDB.add(etTen.getText().toString(), etEmail.getText().toString(), "");
        Intent lst = new Intent(Them.this, SQLite_Demo.class);
        startActivity(lst);
        finish();
    }

    public void danhsach(View view) {
        Intent lst = new Intent(Them.this, SQLite_Demo.class);
        startActivity(lst);
        finish();
    }
}
