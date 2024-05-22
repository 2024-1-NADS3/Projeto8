package com.example.intimalearn;

import android.os.Bundle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeletarConta extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_conta);

        editTextEmail = findViewById(R.id.emailEsqueceuSenha2);
        editTextSenha = findViewById(R.id.senhaDeletarConta);

        findViewById(R.id.solicitarRedefinicao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarConta();
            }
        });
    }

    // Método para deletar a conta
    public void deletarConta() {
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();
        SharedPreferences preferences = getSharedPreferences("suasPreferencias", Context.MODE_PRIVATE);

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        // URL para deletar a conta
        String url = "https://5gx3hl-3000.csb.app/deletarUsuario";

        // JSON com email e senha
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
            jsonParams.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // AsyncTask para fazer a solicitação ao servidor
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("DELETE");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonParams.toString().getBytes());

                    return connection.getResponseCode();
                } catch (IOException e) {
                    e.printStackTrace();
                    return -1; // Erro de conexão
                }
            }

            @Override
            protected void onPostExecute(Integer responseCode) {
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("email");
                    editor.remove("senha");
                    editor.apply();

                    Intent intent = new Intent(DeletarConta.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.execute();
    }
}