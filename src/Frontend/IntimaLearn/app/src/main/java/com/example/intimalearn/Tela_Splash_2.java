package com.example.intimalearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;

public class Tela_Splash_2 extends AppCompatActivity {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash2);

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
        Intent intent = new Intent(Tela_Splash_2.this, Tela_Splash_1.class);
        startActivity(intent);
        // Fechar a tela atual (tela splash 2)
        finish();
    }

    private void avancarProximaTela() {
        // Criar uma Intent para avançar para a próxima tela
        Intent intent = new Intent(Tela_Splash_2.this, Tela_Splash_3.class);
        startActivity(intent);
        // Fechar a tela atual (tela splash 2)
        finish();
    }
}