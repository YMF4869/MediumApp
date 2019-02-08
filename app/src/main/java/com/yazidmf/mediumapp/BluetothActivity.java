package com.yazidmf.mediumapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BluetothActivity extends AppCompatActivity {

    @BindView(R.id.btnon)
    Button btnon;
    @BindView(R.id.btnoff)
    Button btnoff;
    @BindView(R.id.btnvisible)
    Button btnvisible;
    @BindView(R.id.btnpaired)
    Button btnpaired;
    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;
    @BindView(R.id.listv)
    ListView listv;

    //Prorerti
    // Komponen pengatur Bluetooth
    BluetoothAdapter bluetoothAdapter;
    // Komponen penampung data device yang device yang pernah terhubung
    Set<BluetoothDevice> paired_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetoth);
        ButterKnife.bind(this);

        // Masukan Data Bluetooth kedalam Variable BluetoothAdapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    // Perintah Blueetooth on
    public void pon(View view) {
        // Cek Bluetooth On
        if (!bluetoothAdapter.isEnabled()) {
            // Jika Bluetooth nonaktif, aktifkan Bluetooth
            // Minta pengaturan Intent
            Intent hidupkan = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // Memanggil pengaturan bluetooth
            startActivityForResult(hidupkan, 0);
            Toast.makeText(this, "Mengaktifkan Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth sudah Aktif", Toast.LENGTH_SHORT).show();
        }
    }

    // Perintah Bluetooth off
    public void pof(View view) {
        bluetoothAdapter.disable();
        Toast.makeText(this,"Menonaktifkan Bluetooth", Toast.LENGTH_SHORT).show();
    }

    // Perintah Bluetooth Visible
    public void pvis(View view) {
        // Kirim Permintaan kepada Bluetooth Adapter untuk membuat perangkat terlihat di peranhgkat lain
        Intent tampil = new Intent(bluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        // Menampilkan pengaturan Bluetooth
        startActivityForResult(tampil, 0);
    }

    //Perintah menampilkan Paired
    public void plist(View view) {
        // Dapatkan data perangkat yang sudah pernah terhubung dari pengaturan Bluetooth Adapter
        paired_device = bluetoothAdapter.getBondedDevices();
        // Variable Penampung data ubtuk ListView
        ArrayList list_perangkat = new ArrayList();

        // Ambil satu per satu data perangkat dan masukan ke dalam list_perangkat
        for (BluetoothDevice bt : paired_device) {
            // Masukan data ke list_perangkat
            list_perangkat.add(bt.getName());
            // Masukan ke dalam adapter listview
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_perangkat);
            // Pasang adapter ke komponen listview
            listv.setAdapter(adapter);
        }
    }
}
