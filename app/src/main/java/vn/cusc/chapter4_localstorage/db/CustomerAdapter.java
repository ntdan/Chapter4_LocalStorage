package vn.cusc.chapter4_localstorage.db;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.cusc.chapter4_localstorage.R;

/**
 * Created by ntdan on 7/4/2017.
 */
public class CustomerAdapter extends BaseAdapter {
    List<Customer> list;
    Context context;

    public CustomerAdapter(List<Customer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public void delete(int position) {
        list.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item item;

        if (convertView == null) {
            item = new Item();

            convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.customer_item, parent, false);
            item.ten = (TextView) convertView.findViewById(R.id.tvTen);
            item.email = (TextView) convertView.findViewById(R.id.tvEmail);
            item.id = (TextView) convertView.findViewById(R.id.tvID);

            convertView.setTag(item);
        } else {
            item = (Item) convertView.getTag();
        }

        item.ten.setText(list.get(position).getFullname());
        item.email.setText(list.get(position).getEmail());
        item.id.setText(list.get(position).getId() + "");

        convertView.setLongClickable(true);

        return convertView;
    }

    class Item {
        TextView ten, email, id;
    }
}
