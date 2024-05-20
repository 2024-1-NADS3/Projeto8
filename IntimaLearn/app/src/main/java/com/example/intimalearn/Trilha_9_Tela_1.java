package com.example.intimalearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Trilha_9_Tela_1 extends AppCompatActivity {

    int lessSaturatedGreen = Color.rgb(119, 213, 143); // Verde menos saturado
    int lessSaturatedRed = Color.rgb(254, 99, 99); // Vermelho menos saturado
    private int pontos = 0; // Variável para armazenar os pontos do usuário

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trilha9_tela1);

        pontos = recuperarPontos();

        // Configurar listeners de clique para os botões representando as opções
        Button opcao01 = findViewById(R.id.opcao01);
        Button opcao02 = findViewById(R.id.opcao02);
        Button opcao03 = findViewById(R.id.opcao03);
        Button opcao04 = findViewById(R.id.opcao04);

        opcao02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pontos += 5;
                opcao02.setBackgroundTintList(ColorStateList.valueOf(lessSaturatedGreen)); // Define o botão como verde menos saturado
                redirecionarParaProximaTela();
            }
        });
        opcao01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao01.setBackgroundTintList(ColorStateList.valueOf(lessSaturatedRed)); // Define o botão como verde menos saturado

                pontos = 0;
                redirecionarParaProximaTela();
            }
        });
        opcao03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao03.setBackgroundTintList(ColorStateList.valueOf(lessSaturatedRed)); // Define o botão como verde menos saturado

                pontos = 0;
                redirecionarParaProximaTela();
            }
        });
        opcao04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao04.setBackgroundTintList(ColorStateList.valueOf(lessSaturatedRed)); // Define o botão como verde menos saturado

                pontos = 0;
                redirecionarParaProximaTela();
            }
        });
    }

    private int recuperarPontos() {
        SharedPreferences preferences = getSharedPreferences("Pontuacao", MODE_PRIVATE);
        return preferences.getInt("pontos", 0);
    }

    private void redirecionarParaProximaTela() {
        salvarPontos();
        Intent intent = new Intent(this, Trilha_9_Tela_2.class);
        startActivity(intent);
    }
    private void salvarPontos() {
        SharedPreferences preferences = getSharedPreferences("Pontuacao", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("pontos", pontos);
        editor.apply();
    }
}