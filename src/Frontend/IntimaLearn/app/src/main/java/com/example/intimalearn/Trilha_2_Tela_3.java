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

public class Trilha_2_Tela_3 extends AppCompatActivity {

    private TextView textViewPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trilha2_tela3);
        enviarResultado();

        // Recuperar os pontos das SharedPreferences
        int pontos = recuperarPontos();

        // Enviar os pontos para o servidor
        enviarPontosParaServidor(pontos);
    }

    public void InicioTrilha(View view) {
        desbloquearFase3();
        Intent intent = new Intent(Trilha_2_Tela_3.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void enviarResultado() {
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
    }

    // Método para recuperar os pontos das SharedPreferences
    private int recuperarPontos() {
        SharedPreferences preferences = getSharedPreferences("Pontuacao", MODE_PRIVATE);
        return preferences.getInt("pontos", 0); // Retorna 0 se não houver pontos salvos
    }

    private String[] recuperarCredenciaisUsuario() {
        SharedPreferences preferences = getSharedPreferences("CredenciaisUsuario", MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String senha = preferences.getString("senha", "");
        return new String[]{email, senha};
    }

    // Método para desbloquear a segunda fase
    private void desbloquearFase3() {
        SharedPreferences prefs1 = getSharedPreferences("MyPrefs4", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs1.edit();
        editor.putBoolean("Fase3Unlocked3", true);
        editor.apply();
    }


    // Método para enviar os pontos para o servidor
    private void enviarPontosParaServidor(int pontos) {
        pontos = recuperarPontos();
        // Recuperar as credenciais do usuário das preferências compartilhadas
        String[] credenciais = recuperarCredenciaisUsuario();
        String email = credenciais[0];
        String senha = credenciais[1];

        // URL do servidor
        String urlServidor = "https://5gx3hl-3000.csb.app/enviarPontos";

        // Criar um objeto JSON com os dados (email, senha e pontos)
        JSONObject jsonDados = new JSONObject();
        try {
            jsonDados.put("email", email);
            jsonDados.put("senha", senha);
            jsonDados.put("pontuacao", pontos);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        // Enviar os dados para o servidor em uma AsyncTask
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    // Abrir uma conexão HTTP para a URL do servidor
                    URL url = new URL(urlServidor);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);

                    // Escrever os dados JSON na conexão
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(jsonDados.toString());
                    writer.flush();

                    // Verificar o código de resposta do servidor
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Se a resposta for OK, os pontos foram enviados com sucesso
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    } else {
                        // Se a resposta não for OK, houve um erro ao enviar os pontos
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }

                    // Fechar a conexão
                    writer.close();
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                }
                return null;
            }
        }.execute();
    }
}
