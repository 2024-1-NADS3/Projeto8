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

public class Trilha_13_Tela_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trilha13_tela3);
        enviarResultado();
    }

    public void InicioTrilha(View view) {
        desbloquearFase14();
        Intent intent = new Intent(Trilha_13_Tela_3.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void enviarResultado() {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
    }

    // Método para desbloquear a segunda fase
    private void desbloquearFase14() {
        SharedPreferences prefs14 = getSharedPreferences("MyPrefs15", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs14.edit();
        editor.putBoolean("Fase14Unlocked3", true);
        editor.apply();
    }
}
