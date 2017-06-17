package vn.cusc.chapter4_localstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileList extends AppCompatActivity {

    String[] files;
    ArrayList<String> fStrings = new ArrayList<>();

    ListView lst;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        lst = (ListView) findViewById(R.id.listView);

        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".txt")) {
                        fStrings.add(name);
                        return true;
                    }
                    return true;
                }
            });

            files = new String[fStrings.size()];
            for (int i = 0; i < fStrings.size(); i++) {
                files[i] = fStrings.get(i);
            }

            ArrayAdapter<String> adp = new ArrayAdapter<String>(FileList.this,
                    android.R.layout.simple_list_item_1, files);

            lst.setAdapter(adp);

            lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("fileName", files[position]);
                    setResult(RESULT_OK, intent);
                    finish();
                    return true;
                }
            });
        } catch (Exception ex) {

        }
    }
}
