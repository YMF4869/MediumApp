package com.yazidmf.mediumapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMSActivity extends AppCompatActivity {

    @BindView(R.id.txtnumber)
    MaterialEditText txtnumber;
    @BindView(R.id.txtisi)
    MaterialEditText txtisi;
    @BindView(R.id.btnsend)
    Button btnsend;
    @BindView(R.id.btnintent)
    Button btnintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnsend, R.id.btnintent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnsend:

                // Kondisi ketika permision belum diizinkan leh pengguna (marsmelow +)
                if (Build.VERSION.SDK_INT >=23 && checkSelfPermission(Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                    // Menampilkan konfirmasi pada user untuk mengijinkan mengakses send_ms
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS
                    , Manifest.permission.SEND_SMS},120);
                } else {

                    try {
                        SmsManager smsManager= SmsManager .getDefault();
                        smsManager.sendTextMessage(txtnumber.getText().toString()
                        , null, txtisi.getText().toString(), null, null);
                        clearText();
                        Toast.makeText(this, "Mengirim Pesan", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Gagal Mengirim Pesan", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnintent:
                // Membuka Aplikasi pesan dari perangkat
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.putExtra("sms_body", txtisi.getText().toString());
                intent.putExtra("address", txtnumber.getText().toString());
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
                break;
        }
    }

    // Penempataan method itu bisa terserah, yang penting masih dalam satu kelas
    private void clearText() {
        // txtnumber.setText("");
        txtisi.setText("");
    }
}
