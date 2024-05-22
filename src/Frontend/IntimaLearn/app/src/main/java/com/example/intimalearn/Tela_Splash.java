package com.example.intimalearn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.widget.VideoView;

public class Tela_Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        // Adiar a transição para a próxima tela
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Criar uma Intent para abrir a próxima tela
                Intent intent = new Intent(Tela_Splash.this, Tela_Splash_1.class);
                startActivity(intent);

                // Fechar a tela atual (tela de splash)
                finish();
            }
        }, 3000); // 3000 milissegundos = 3 segundos de atraso
    }
}