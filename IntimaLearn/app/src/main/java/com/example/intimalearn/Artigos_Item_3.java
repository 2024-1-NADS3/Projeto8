package com.example.intimalearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Artigos_Item_3 extends AppCompatActivity {

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigos_item3);

        Button button = findViewById(R.id.visitarSite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.amazon.com.br/Senta-que-nem-mo%C3%A7a-descomplicado-ebook/dp/B09BS6BJW7/ref=sr_1_50?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=389MOZT2910BH&dib=eyJ2IjoiMSJ9.bdYFzVQw4Wd5-2NAMricxG5Iv_Olk_sfaUrDgTg9U8wVkcfejfE_EAJjMu7JFJYaaqWTp6tOrLtTPQ22g3pb1Q.BpsqbUcC2h5bKwRqM4C3Djr2Sq9gEXbjzxjtcGGOUl0&dib_tag=se&keywords=SEXUALIDADE&qid=1713974381&s=books&sprefix=sexualidade%2Cstripbooks%2C189&sr=1-50";
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
        Intent intent = new Intent(Artigos_Item_3.this, Artigos_Item_1.class);
        startActivity(intent);
        // Fechar a tela atual (tela splash 2)
        finish();
    }

    private void avancarProximaTela() {
        // Criar uma Intent para avançar para a próxima tela
        Intent intent = new Intent(Artigos_Item_3.this, Artigos_Item_4.class);
        startActivity(intent);
        // Fechar a tela atual (tela splash 2)
        finish();
    }
}