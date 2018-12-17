package com.nowater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    ArrayList<Device> devices;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Device[] devicesArr = {
                new Typewriter(R.drawable.mimpc_luigi_big, 9, "Luigi", 10),
                new Computer(R.drawable.mimpc_luigi_big, 9, "Luigi", 20)
        };
        devices = new ArrayList<>();
        for (int i = 0; i < devicesArr.length; ++i) {
            devices.add(devicesArr[i]);
        }
        listView = (ListView) findViewById(R.id.Shop_List);
        final CustomArrayAdapter adapter = new CustomArrayAdapter(this, devices);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ShopActivity.this, R.style.DialogTheme)
                        .setTitle("Title")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("Message")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (devices.get(position).price >= GameActivity.pages.amount()) {
                                    Toast.makeText(ShopActivity.this, "bought", Toast.LENGTH_LONG).show();
                                    GameActivity.device = devices.get(position);
                                    devices.remove(position);
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                    GameActivity.pages.sub(devices.get(position).price);
                                } else {
                                    Toast.makeText(ShopActivity.this, "no money", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        listView.setAdapter(adapter);
    }
}

class CustomArrayAdapter extends ArrayAdapter<Device> {
    private final Context context;
    private final ArrayList<Device> devices;

    public CustomArrayAdapter(Context context, ArrayList<Device> devices) {
        super(context, -1);
        this.context = context;
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Device currentDevice = devices.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_device, parent, false);
        TextView deviceNameView = (TextView) rowView.findViewById(R.id.Device_Name);
        deviceNameView.setText(currentDevice.name);
        TextView devicePriceView = (TextView) rowView.findViewById(R.id.Device_Price);
        devicePriceView.setText(context.getString(R.string.device__text_price) + ": " + currentDevice.price);
        TextView deviceStagesView = (TextView) rowView.findViewById(R.id.Device_ClickPower);
        deviceStagesView.setText(context.getString(R.string.device__text_stages) + ": " + currentDevice.stagesAmount);
        return rowView;
    }
}