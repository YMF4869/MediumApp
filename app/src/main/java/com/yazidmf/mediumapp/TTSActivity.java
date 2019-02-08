package com.yazidmf.mediumapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//Todo implement berfungsi untuk mewarisi methoddari kelas lain

public class TTSActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    @BindView(R.id.txttext)
    EditText txttext;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;

    // Kita buat Atribute
    private android.speech.tts.TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);

        // Masukan komponen TTS
        textToSpeech = new android.speech.tts.TextToSpeech(this, this);
    }


    @Override
    public void onInit(int i) {
        // Jika bis mengakses komponen TTS
        if (i == TextToSpeech.SUCCESS){
            // Atur ke Bahasa Indonesia
            Locale indo = new Locale("spa", "INA");
            // Variable untuk memasukan Bahasa ke dalam TTS
            int result = textToSpeech.setLanguage(indo);

            // Jika Bahasa tidak tersedia atau Perangkat tidak mendukung
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Bahasa tidak ditemukan", Toast.LENGTH_SHORT).show();
            } else {
                // Tampilkan Tombol
                btnSpeech.setEnabled(true);
                // Metode mengambil data teks dari EditText
                mulaiBicara();
            }
        } else {
            // Jika komponen tidak terjangkau
            Toast.makeText(this, "Inisialisasi Gagal", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        mulaiBicara();
    }

    private void mulaiBicara() {
        // Variable penampung Teks
        String teks = txttext.getText().toString();
        // Proses TTS
        textToSpeech.speak(teks,TextToSpeech.QUEUE_FLUSH, null);
    }
}
