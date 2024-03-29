package com.yazidmf.mediumapp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WIFIActivity extends AppCompatActivity {

    @BindView(R.id.imageview2)
    ImageView imageview2;
    @BindView(R.id.swon)
    Switch swon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);

        // Ketika di Switch
        swon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kondisi ketika isChecked adalah benar (true/hidup)
                if (isChecked) {
                    setWifi (true);
                    Toast.makeText(WIFIActivity.this, "Mengaktifkan Wifi ", Toast.LENGTH_SHORT).show();
                } else {
                    setWifi (false);
                    Toast.makeText(WIFIActivity.this, "Menonakitfkan Wifi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setWifi(boolean b) {
        // Masukan layanan Wifi ke dalam Variable
        WifiManager wifiManager = (WifiManager) this.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);

        // Kondisi ketika data pada variable b adalah true dan Wifi sedang non aktif
        if(b == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }else if (b == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }
}
