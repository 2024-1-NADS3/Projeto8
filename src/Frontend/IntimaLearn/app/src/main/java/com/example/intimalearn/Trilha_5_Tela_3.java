package com.example.intimalearn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Trilha_5_Tela_3 extends AppCompatActivity {

    private TextView textViewPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trilha5_tela3);
        enviarResultado();
    }

    public void InicioTrilha(View view) {
        desbloquearFase6();
        Intent intent = new Intent(Trilha_5_Tela_3.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void enviarResultado() {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
    }


    // Método para desbloquear a segunda fase
    private void desbloquearFase6() {
        SharedPreferences prefs6 = getSharedPreferences("MyPrefs7", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs6.edit();
        editor.putBoolean("Fase6Unlocked3", true);
        editor.apply();
    }
}
