package com.example.intimalearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Artigos_Item_8 extends AppCompatActivity {

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigos_item8);

        Button button = findViewById(R.id.visitarSite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://open.spotify.com/show/46c6ShdmWlpGdcehKT5vmy?si=G3RBex87S-a1MqJJqHtw4w&dl_branch=1&nd=1&dlsi=1108664d8d244041";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        // Configurar o GestureDetector
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    // Deslize na horizontal
                    if (diffX > 0) {
                        // Deslize para a esquerda
                        voltarTelaAnterior();
                    } else {
                        // Deslize para a direita
                        avancarProximaTela();
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Passar o evento de toque para o GestureDetector
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void voltarTelaAnterior() {
        // Criar uma Intent para voltar para a tela anterior
        Intent intent = new Intent(Artigos_Item_8.this, Artigos_Item_7.class);
        startActivity(intent);
        // Fechar a tela atual (tela splash 2)
        finish();
    }

    private void avancarProximaTela() {
        // Criar uma Intent para avançar para a próxima tela
        Intent intent = new Intent(Artigos_Item_8.this, MainActivity.class);
        startActivity(intent);
        // Fechar a tela atual (tela splash 2)
        finish();
    }
}