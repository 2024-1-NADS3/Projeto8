package com.example.intimalearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Perfil extends Fragment {

    private TextView textViewNomeUsuario;
    private TextView textViewPontosUsuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ImageView imageView = view.findViewById(R.id.imageView23);

        textViewNomeUsuario = view.findViewById(R.id.nomeUsuario);
        textViewPontosUsuario = view.findViewById(R.id.pontoUsuario);

        String nomeUsuario = recuperarNomeUsuario();
        if (nomeUsuario != null) {
            textViewNomeUsuario.setText(nomeUsuario);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletarContaView(v);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizarPontuacaoUsuario();
    }

    private void atualizarPontuacaoUsuario() {
        String email = recuperarEmailUsuario();
        if (email != null) {
            obterPontuacaoUsuario(email);
        }
    }

    private String recuperarEmailUsuario() {
        SharedPreferences preferences = requireContext().getSharedPreferences("CredenciaisUsuario", Context.MODE_PRIVATE);
        return preferences.getString("email", null);
    }

    private void obterPontuacaoUsuario(String email) {
        String url = "https://5gx3hl-3000.csb.app/obterPontuacaoUsuario";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<Void, Void, String>() {
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
                        return response.toString();
                    } else {
                        Log.e("Perfil", "Erro na solicitação: Código " + responseCode);
                        return null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String pontosUsuario) {
                if (pontosUsuario != null) {
                    textViewPontosUsuario.setText(pontosUsuario);
                    salvarPontuacaoUsuario(pontosUsuario);
                } else {
                    Toast.makeText(getContext(), "Erro ao obter a pontuação do usuário", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private boolean salvarPontuacaoUsuario(String pontuacao) {
        SharedPreferences preferences = requireContext().getSharedPreferences("suasPreferencias", Context.MODE_PRIVATE);
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

    private String recuperarNomeUsuario() {
        SharedPreferences preferences = requireContext().getSharedPreferences("suasPreferencias", Context.MODE_PRIVATE);
        return preferences.getString("nomeUsuario", null);
    }

    public void DeletarContaView(View view) {
        Intent intent = new Intent(getActivity(), DeletarConta.class);
        startActivity(intent);
    }
}
