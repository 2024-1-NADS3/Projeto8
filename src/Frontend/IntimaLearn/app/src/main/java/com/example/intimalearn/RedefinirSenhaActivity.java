package com.example.intimalearn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RedefinirSenhaActivity extends AppCompatActivity {

    private EditText editTextToken;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        editTextToken = findViewById(R.id.tokenRedefinicao);
        editTextSenha = findViewById(R.id.emailEsqueceuSenha3);
    }

    public void redefinirSenha(View view) {
        String token = editTextToken.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (token.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
            redefinirSenhaNoServidor(token, senha);
    }

    private void redefinirSenhaNoServidor(String token, String senha) {
        String url = "https://5gx3hl-3000.csb.app/redefinirSenha";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("token", token);
            jsonParams.put("senha", senha);
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
                        runOnUiThread(() -> Toast.makeText(RedefinirSenhaActivity.this, "Senha redefinida com sucesso", Toast.LENGTH_SHORT).show());
                    } else {
                        runOnUiThread(() -> Toast.makeText(RedefinirSenhaActivity.this, "Erro ao redefinir senha", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(RedefinirSenhaActivity.this, "Erro na solicitação: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
                return null;
            }
        }.execute();
    }
}
