package vn.cusc.chapter4_localstorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import vn.cusc.chapter4_localstorage.db.Customer;
import vn.cusc.chapter4_localstorage.db.MyDB;

public class Update extends AppCompatActivity {

    MyDB customerDB;
    EditText etTen, etEmail;
    TextView tvID;

    int id;
    Customer cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tvID = (TextView) findViewById(R.id.tvID);
        etTen = (EditText) findViewById(R.id.etTen);
        etEmail = (EditText) findViewById(R.id.etEmail);

        Intent ds = getIntent();
        if (ds != null) {
            customerDB = new MyDB(Update.this);
            id = ds.getIntExtra("id", 0);
            cus = customerDB.find(id);

            tvID.setText(id + "");
            etTen.setText(cus.getFullname());
            etEmail.setText(cus.getEmail());
        }
    }

    public void update(View view) {
        customerDB.update(id, etTen.getText().toString(), etEmail.getText().toString(), "");
        setResult(RESULT_OK);
        finish();
    }

    public void danhsach(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
