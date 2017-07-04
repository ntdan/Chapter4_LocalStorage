package vn.cusc.chapter4_localstorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.cusc.chapter4_localstorage.db.Customer;
import vn.cusc.chapter4_localstorage.db.CustomerAdapter;
import vn.cusc.chapter4_localstorage.db.MyDB;

public class SQLite_Demo extends AppCompatActivity {

    MyDB customerDB;
    ArrayList<Customer> customers = new ArrayList<>();
    CustomerAdapter adp;
    ListView lst;
    private int updateCode = 113;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite__demo);

        customerDB = new MyDB(SQLite_Demo.this);
        //customers = customerDB.list();
        //Toast.makeText(SQLite_Demo.this, "Customer: " + customers.size(), Toast.LENGTH_SHORT).show();
        //customerDB.add("Nguyen Van Mit", "nvmit@gmail.com", "ABC");
        customers = customerDB.list();

        lst = (ListView) findViewById(R.id.lstCustomer);
        adp = new CustomerAdapter(customers, SQLite_Demo.this);
        if (customers != null) {
            lst.setAdapter(adp);
            registerForContextMenu(lst);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnXoa) {
            View view = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).targetView;
            String strId = ((TextView) view.findViewById(R.id.tvID)).getText().toString();
            int id = Integer.parseInt(strId);

            customerDB.delete(id);

            /*// xoa pt hien tai tren ung dung
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i).getId() == id)
                {
                    customers.remove(i);
                    adp.delete(i);
                    break;
                }
            }*/

            loadData();
        }
        if (item.getItemId() == R.id.mnSua) {
            View view = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).targetView;
            String strId = ((TextView) view.findViewById(R.id.tvID)).getText().toString();
            int id = Integer.parseInt(strId);

            Intent edit = new Intent(SQLite_Demo.this, Update.class);
            edit.putExtra("id", id);
            startActivityForResult(edit, updateCode);
        }
        return true;
    }

    private void loadData() {
        customers = customerDB.list();
        if (customers != null) {
            adp = new CustomerAdapter(customers, SQLite_Demo.this);
            lst.setAdapter(adp);
            registerForContextMenu(lst);
        } else {
            lst.setAdapter(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == updateCode) {
                loadData();
            }
        } else {
            Toast.makeText(SQLite_Demo.this, "Thao tac cap nhat bi huy", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.item_opr, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sqlopr, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnThem) {
            Intent add = new Intent(SQLite_Demo.this, Them.class);
            startActivity(add);
            finish();
        }
        return true;
    }
}
