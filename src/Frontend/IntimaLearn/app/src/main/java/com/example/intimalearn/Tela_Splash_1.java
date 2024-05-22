package com.example.intimalearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_Splash_1 extends AppCompatActivity {


    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash1);

        // Configurar o GestureDetector
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    // Deslize na horizontal
                    if (diffX < 0) {
                        // Deslize para a direita
                        abrirProximaTela();
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

    private void abrirProximaTela() {
        // Criar uma Intent para abrir a próxima tela
        Intent intent = new Intent(Tela_Splash_1.this, Tela_Splash_2.class); // Substitua "SuaProximaTela" pelo nome da sua próxima atividade
        startActivity(intent);

        // Fechar a tela atual (tela splash 1)
        finish();
    }
}