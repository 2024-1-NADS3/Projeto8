package com.example.intimalearn;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EsqueceuSenhaActivity extends AppCompatActivity {

    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        editTextEmail = findViewById(R.id.emailEsqueceuSenha);
    }

    public void TelaRedefinir(View view) {
        Intent intent = new Intent(EsqueceuSenhaActivity.this, RedefinirSenhaActivity.class);
        startActivity(intent);
    }

    public void solicitarRedefinicao(View view) {
        String email = editTextEmail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de email", Toast.LENGTH_SHORT).show();
            return;
        }

        solicitarRedefinicaoNoServidor(email);
    }

    private void solicitarRedefinicaoNoServidor(String email) {
        String url = "https://5gx3hl-3000.csb.app/esqueceuSenha";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonParams.toString().getBytes());

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        runOnUiThread(() -> {
                            Toast.makeText(EsqueceuSenhaActivity.this, "Email de redefinição de senha enviado com sucesso", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(EsqueceuSenhaActivity.this, RedefinirSenhaActivity.class);
                                startActivity(intent);
                                finish(); // Finaliza a atividade atual se necessário
                            }, 3000); // 3000 milissegundos = 3 segundos
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(EsqueceuSenhaActivity.this, "Erro ao enviar email de redefinição", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(EsqueceuSenhaActivity.this, "Erro na solicitação: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
                return null;
            }
        }.execute();
    }
}
