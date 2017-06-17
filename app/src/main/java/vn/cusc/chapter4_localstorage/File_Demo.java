package vn.cusc.chapter4_localstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class File_Demo extends AppCompatActivity {
    String fileName;

    int selectCode = 113;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__demo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnSelected) {
            Intent intent = new Intent(File_Demo.this, FileList.class);
            startActivityForResult(intent, selectCode);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == selectCode) {
            String fileName = data.getStringExtra("fileName");

            try {
                // duong dan thi muc
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

                File file = new File(path + "/" + fileName);
                if (file.exists()) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(file), "UTF-16LE"));
                    // doc den het file
                    String noiDung = "";
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        noiDung = noiDung + line + "\n";
                    }

                    ((TextView) findViewById(R.id.tvContent)).setText(noiDung);
                } else {
                    Toast.makeText(File_Demo.this, "Tap tin khong ton tai", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Toast.makeText(File_Demo.this, "Loi:" + ex.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Loi:", ex.toString());
            }
        }
    }

    public void ghi(View view) {
        try {
            fileName = ((EditText) findViewById(R.id.etFileName)).getText().toString();
            // duong dan thi muc
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

            File file = new File(path + "/" + fileName);
            if (!file.exists())
                file.createNewFile();

            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file), "UTF-16LE"));
            String content = ((EditText) findViewById(R.id.etText)).getText().toString();

            writer.write(content);
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            Toast.makeText(File_Demo.this, "Loi:" + ex.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Loi:", ex.toString());
        }
    }

    public void doc(View view) {
        try {
            fileName = ((EditText) findViewById(R.id.etFileName_Read)).getText().toString();
            // duong dan thi muc
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

            File file = new File(path + "/" + fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF-16LE"));
                // doc den het file
                String noiDung = "";
                String line = "";

                while ((line = reader.readLine()) != null) {
                    noiDung = noiDung + line + "\n";
                }

                ((TextView) findViewById(R.id.tvContent)).setText(noiDung);
            } else {
                Toast.makeText(File_Demo.this, "Tap tin khong ton tai", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(File_Demo.this, "Loi:" + ex.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Loi:", ex.toString());
        }
    }
}
