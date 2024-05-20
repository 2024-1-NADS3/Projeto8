package com.example.intimalearn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;

    String url = "https://5gx3hl-3000.csb.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.emailLogin);
        editTextSenha = findViewById(R.id.senhaLogin);
    }

    public void Cadastrar(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void EsqueceuASenha(View view) {
        Intent intent = new Intent(LoginActivity.this, EsqueceuSenhaActivity.class);
        startActivity(intent);
    }

    public void fazerLogin(View view) {
        // Obtenha os valores dos EditTexts
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        // Verifique se os campos não estão vazios
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        fazerLoginNoServidor(email, senha);
    }

    // Método para fazer login no servidor
    @SuppressLint("StaticFieldLeak")
    private void fazerLoginNoServidor(String email, String senha) {
        String url = "https://5gx3hl-3000.csb.app/login";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
            jsonParams.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<Void, Void, String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonParams.toString().getBytes());

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();
                        Log.d("Login", "Response: " + response.toString());

                        if (response.toString().contains("Login bem-sucedido")) {
                            salvarCredenciaisUsuario(email, senha);
                            String nomeUsuario = obterNomeUsuario(email);
                            String pontosUsuario = obterPontuacaoUsuario(email);
                            salvarNomeUsuario(nomeUsuario);
                            salvarPontuacaoUsuario(pontosUsuario);
                            return nomeUsuario;
                        } else {
                            return null;
                        }
                    } else {
                        Log.e("Login", "Erro na solicitação: Código " + responseCode);
                        return null;
                    }
                } catch (@SuppressLint("StaticFieldLeak") IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String nomeUsuario) {
                if (nomeUsuario != null) {
                    Toast.makeText(LoginActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    // Método para obter o nome do usuário do servidor com base no email
    private String obterNomeUsuario(String email) {
        String url = "https://5gx3hl-3000.csb.app/obterNomeUsuario";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonParams.toString().getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                Log.e("Login", "Erro na solicitação: Código " + responseCode);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String obterPontuacaoUsuario(String email) {
        String url = "https://5gx3hl-3000.csb.app/obterPontuacaoUsuario";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonParams.toString().getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                Log.e("Login", "Erro na solicitação: Código " + responseCode);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para salvar a pontuação do usuário após o login
    private boolean salvarPontuacaoUsuario(String pontuacao) {
        SharedPreferences preferences = getSharedPreferences("suasPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pontuacaoUsuario", pontuacao);
        boolean success = editor.commit(); // Retorna true se a operação de salvamento for bem-sucedida
        if (success) {
            Log.d("PontuacaoUsuario", "Pontuação do usuário salva com sucesso: " + pontuacao);
        } else {
            Log.e("PontuacaoUsuario", "Erro ao salvar a pontuação do usuário");
        }
        return success;
    }




    // Método para salvar o nome do usuário após o login
    private void salvarNomeUsuario(String nome) {
        SharedPreferences preferences = getSharedPreferences("suasPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nomeUsuario", nome);
        editor.apply();
    }

    private void salvarCredenciaisUsuario(String email, String senha) {
        SharedPreferences preferences = getSharedPreferences("CredenciaisUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("senha", senha);
        editor.apply();
    }
}