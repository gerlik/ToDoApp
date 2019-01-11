package com.example.gerli.mytodoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingActivity extends Activity {

    MyCustomAdapter dataAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2shoppinglist);

        //Generate list View from ArrayList
        displayListView();

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Item> itemList = new ArrayList<Item>();
        Item item = new Item("Piim",false);
        itemList.add(item);
        item = new Item("Kanafilee",true);
        itemList.add(item);
        item = new Item("Jäätis",false);
        itemList.add(item);
        item = new Item("Sai",true);
        itemList.add(item);
        item = new Item("Kit Kat",true);
        itemList.add(item);
        item = new Item("Koeratoit",false);
        itemList.add(item);
        item = new Item("Juust",false);
        itemList.add(item);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.tab2shoppinglist, itemList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

    }

    private class MyCustomAdapter extends ArrayAdapter<Item> {

        private ArrayList<Item> itemList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Item> itemList) {
            super(context, textViewResourceId, itemList);
            this.itemList = new ArrayList<Item>();
            this.itemList.addAll(itemList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.tab2shoppinglist, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Item item = (Item) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        item.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Item item = itemList.get(position);
            holder.name.setText(item.getName());
            holder.name.setChecked(item.isSelected());
            holder.name.setTag(item);

            return convertView;

        }

    }

}
