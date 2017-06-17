package vn.cusc.chapter4_localstorage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean login = true;

    EditText etTenDN, etMK;
    TextView tvTenDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTenDN = (EditText) findViewById(R.id.etTenDN);
        etMK = (EditText) findViewById(R.id.etMK);

        tvTenDangNhap = (TextView) findViewById(R.id.tvTenDangNhap);

        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        login = preferences.getBoolean("DaDangNhap", false);
        if(login == false)
        {
            // chua dang nhap
            findViewById(R.id.lnChuaDangNhap).setVisibility(View.VISIBLE);
            findViewById(R.id.lnDaDangNhap).setVisibility(View.GONE);
        }
        else
        {
            // da dang nhap
            findViewById(R.id.lnChuaDangNhap).setVisibility(View.GONE);
            findViewById(R.id.lnDaDangNhap).setVisibility(View.VISIBLE);
            tvTenDangNhap.setText(preferences.getString("ten",""));
        }
    }

    public void dangnhap(View view) {
        if(etTenDN.getText().toString().equalsIgnoreCase("admin")
                && etMK.getText().toString().equals("admin"))
        {
            // chung thuc dung
            editor = preferences.edit();
            // ghi du lieu vao pres
            editor.putBoolean("DaDangNhap", true);
            editor.putString("ten", etTenDN.getText().toString());
            editor.commit();
            findViewById(R.id.lnChuaDangNhap).setVisibility(View.GONE);
            findViewById(R.id.lnDaDangNhap).setVisibility(View.VISIBLE);

            tvTenDangNhap.setText(preferences.getString("ten",""));
        }
        else
        {
            // chung thuc sai
            Toast.makeText(MainActivity.this, "Ten dang nhap va mat khau la admin", Toast.LENGTH_SHORT).show();
        }
    }

    public void thoat(View view) {
        // thoat chuong trinh
        //System.exit(0);

        // chua dang nhap
        findViewById(R.id.lnChuaDangNhap).setVisibility(View.VISIBLE);
        findViewById(R.id.lnDaDangNhap).setVisibility(View.GONE);
        // chung thuc dung
        editor = preferences.edit();
        // ghi du lieu vao pres
        editor.putBoolean("DaDangNhap", false);
        editor.commit();
    }
}
