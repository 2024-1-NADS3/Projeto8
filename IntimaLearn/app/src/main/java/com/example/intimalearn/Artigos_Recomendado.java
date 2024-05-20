package com.example.intimalearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Artigos_Recomendado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigos_recomendado);

        // Encontrar os TextViews e ImageViews por seus IDs
        TextView textView1 = findViewById(R.id.recente);
        TextView textView2 = findViewById(R.id.maisVistos);
        ImageView imageView1 = findViewById(R.id.item01);
        ImageView imageView2 = findViewById(R.id.item02);

        // Adicionar OnClickListener para o textView1
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para a próxima tela
                Intent intent = new Intent(Artigos_Recomendado.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Adicionar OnClickListener para o textView2
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para a próxima tela
                Intent intent = new Intent(Artigos_Recomendado.this, Artigos_Mais_Vistos.class);
                startActivity(intent);
            }
        });

        // Adicionar OnClickListener para o imageView1
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para a próxima tela
                Intent intent = new Intent(Artigos_Recomendado.this, Artigos_Item_1.class);
                startActivity(intent);
            }
        });

        // Adicionar OnClickListener para o imageView2
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para a próxima tela
                Intent intent = new Intent(Artigos_Recomendado.this, Artigos_Item_2.class);
                startActivity(intent);
            }
        });
    }
}