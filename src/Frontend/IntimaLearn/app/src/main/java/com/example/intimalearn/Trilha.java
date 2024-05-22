package com.example.intimalearn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import static android.content.Context.MODE_PRIVATE;

public class Trilha extends Fragment {

    private Button buttonFase1, buttonFase2, buttonFase3, buttonFase4, buttonFase5, buttonFase6,
    buttonFase7, buttonFase8, buttonFase9, buttonFase10, buttonFase11, buttonFase12, buttonFase13,
    buttonFase14, buttonFase15, buttonFase16, buttonFase17, buttonFase18;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trilha, container, false);

        buttonFase1 = view.findViewById(R.id.buttonFase1);
        buttonFase2 = view.findViewById(R.id.buttonFase2);
        buttonFase3 = view.findViewById(R.id.buttonFase3);
        buttonFase4 = view.findViewById(R.id.buttonFase4);
        buttonFase5 = view.findViewById(R.id.buttonFase5);
        buttonFase6 = view.findViewById(R.id.buttonFase6);
        buttonFase7 = view.findViewById(R.id.buttonFase7);
        buttonFase8 = view.findViewById(R.id.buttonFase8);
        buttonFase9 = view.findViewById(R.id.buttonFase9);
        buttonFase10 = view.findViewById(R.id.buttonFase10);
        buttonFase11 = view.findViewById(R.id.buttonFase11);
        buttonFase12 = view.findViewById(R.id.buttonFase12);
        buttonFase13 = view.findViewById(R.id.buttonFase13);
        buttonFase14 = view.findViewById(R.id.buttonFase14);
        buttonFase15 = view.findViewById(R.id.buttonFase15);
        buttonFase16 = view.findViewById(R.id.buttonFase16);
        buttonFase17 = view.findViewById(R.id.buttonFase17);
        buttonFase18 = view.findViewById(R.id.buttonFase18);

        // Desabilitar o botão 2 inicialmente
        buttonFase2.setEnabled(false);
        buttonFase2.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 2 está desbloqueada
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs3", MODE_PRIVATE);
        boolean isFase2Unlocked = prefs.getBoolean("Fase2Unlocked3", false);

        // Habilitar o botão 2 se a fase 2 estiver desbloqueada
        if (isFase2Unlocked) {
            buttonFase2.setEnabled(true);
            buttonFase2.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 3 inicialmente
        buttonFase3.setEnabled(false);
        buttonFase3.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 3 está desbloqueada
        SharedPreferences prefs1 = getActivity().getSharedPreferences("MyPrefs4", MODE_PRIVATE);
        boolean isFase3Unlocked = prefs1.getBoolean("Fase3Unlocked3", false);

        // Habilitar o botão 3 se a fase 3 estiver desbloqueada
        if (isFase3Unlocked) {
            buttonFase3.setEnabled(true);
            buttonFase3.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 4 inicialmente
        buttonFase4.setEnabled(false);
        buttonFase4.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 4 está desbloqueada
        SharedPreferences prefs4 = getActivity().getSharedPreferences("MyPrefs5", MODE_PRIVATE);
        boolean isFase4Unlocked = prefs4.getBoolean("Fase4Unlocked3", false);

        // Habilitar o botão 4 se a fase 4 estiver desbloqueada
        if (isFase4Unlocked) {
            buttonFase4.setEnabled(true);
            buttonFase4.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }

        // Desabilitar o botão 5 inicialmente
        buttonFase5.setEnabled(false);
        buttonFase5.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 5 está desbloqueada
        SharedPreferences prefs5 = getActivity().getSharedPreferences("MyPrefs6", MODE_PRIVATE);
        boolean isFase5Unlocked = prefs5.getBoolean("Fase5Unlocked3", false);

        // Habilitar o botão 5 se a fase 5 estiver desbloqueada
        if (isFase5Unlocked) {
            buttonFase5.setEnabled(true);
            buttonFase5.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 6 inicialmente
        buttonFase6.setEnabled(false);
        buttonFase6.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 6 está desbloqueada
        SharedPreferences prefs6 = getActivity().getSharedPreferences("MyPrefs7", MODE_PRIVATE);
        boolean isFase6Unlocked = prefs6.getBoolean("Fase6Unlocked3", false);

        // Habilitar o botão 6 se a fase 6 estiver desbloqueada
        if (isFase6Unlocked) {
            buttonFase6.setEnabled(true);
            buttonFase6.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 7 inicialmente
        buttonFase7.setEnabled(false);
        buttonFase7.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 7 está desbloqueada
        SharedPreferences prefs7 = getActivity().getSharedPreferences("MyPrefs8", MODE_PRIVATE);
        boolean isFase7Unlocked = prefs7.getBoolean("Fase7Unlocked3", false);

        // Habilitar o botão 7 se a fase 7 estiver desbloqueada
        if (isFase7Unlocked) {
            buttonFase7.setEnabled(true);
            buttonFase7.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 8 inicialmente
        buttonFase8.setEnabled(false);
        buttonFase8.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 8 está desbloqueada
        SharedPreferences prefs8 = getActivity().getSharedPreferences("MyPrefs9", MODE_PRIVATE);
        boolean isFase8Unlocked = prefs8.getBoolean("Fase8Unlocked3", false);

        // Habilitar o botão 8 se a fase 8 estiver desbloqueada
        if (isFase8Unlocked) {
            buttonFase8.setEnabled(true);
            buttonFase8.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }

        // Desabilitar o botão 9 inicialmente
        buttonFase9.setEnabled(false);
        buttonFase9.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 9 está desbloqueada
        SharedPreferences prefs9 = getActivity().getSharedPreferences("MyPrefs10", MODE_PRIVATE);
        boolean isFase9Unlocked = prefs9.getBoolean("Fase9Unlocked3", false);

        // Habilitar o botão 9 se a fase 9 estiver desbloqueada
        if (isFase9Unlocked) {
            buttonFase9.setEnabled(true);
            buttonFase9.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }

        // Desabilitar o botão 10 inicialmente
        buttonFase10.setEnabled(false);
        buttonFase10.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 10 está desbloqueada
        SharedPreferences prefs10 = getActivity().getSharedPreferences("MyPrefs11", MODE_PRIVATE);
        boolean isFase10Unlocked = prefs10.getBoolean("Fase10Unlocked3", false);

        // Habilitar o botão 10 se a fase 10 estiver desbloqueada
        if (isFase10Unlocked) {
            buttonFase10.setEnabled(true);
            buttonFase10.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }

        // Desabilitar o botão 11 inicialmente
        buttonFase11.setEnabled(false);
        buttonFase11.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 11 está desbloqueada
        SharedPreferences prefs11 = getActivity().getSharedPreferences("MyPrefs12", MODE_PRIVATE);
        boolean isFase11Unlocked = prefs11.getBoolean("Fase11Unlocked3", false);

        // Habilitar o botão 11 se a fase 11 estiver desbloqueada
        if (isFase11Unlocked) {
            buttonFase11.setEnabled(true);
            buttonFase11.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 12 inicialmente
        buttonFase12.setEnabled(false);
        buttonFase12.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 12 está desbloqueada
        SharedPreferences prefs12 = getActivity().getSharedPreferences("MyPrefs13", MODE_PRIVATE);
        boolean isFase12Unlocked = prefs12.getBoolean("Fase12Unlocked3", false);

        // Habilitar o botão 12 se a fase 12 estiver desbloqueada
        if (isFase12Unlocked) {
            buttonFase12.setEnabled(true);
            buttonFase12.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 13 inicialmente
        buttonFase13.setEnabled(false);
        buttonFase13.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 13 está desbloqueada
        SharedPreferences prefs13 = getActivity().getSharedPreferences("MyPrefs14", MODE_PRIVATE);
        boolean isFase13Unlocked = prefs13.getBoolean("Fase13Unlocked3", false);

        // Habilitar o botão 13 se a fase 13 estiver desbloqueada
        if (isFase13Unlocked) {
            buttonFase13.setEnabled(true);
            buttonFase13.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 14 inicialmente
        buttonFase14.setEnabled(false);
        buttonFase14.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 14 está desbloqueada
        SharedPreferences prefs14 = getActivity().getSharedPreferences("MyPrefs15", MODE_PRIVATE);
        boolean isFase14Unlocked = prefs14.getBoolean("Fase14Unlocked3", false);

        // Habilitar o botão 14 se a fase 14 estiver desbloqueada
        if (isFase14Unlocked) {
            buttonFase14.setEnabled(true);
            buttonFase14.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 15 inicialmente
        buttonFase15.setEnabled(false);
        buttonFase15.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 15 está desbloqueada
        SharedPreferences prefs15 = getActivity().getSharedPreferences("MyPrefs16", MODE_PRIVATE);
        boolean isFase15Unlocked = prefs15.getBoolean("Fase15Unlocked3", false);

        // Habilitar o botão 15 se a fase 15 estiver desbloqueada
        if (isFase15Unlocked) {
            buttonFase15.setEnabled(true);
            buttonFase15.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 15 inicialmente
        buttonFase16.setEnabled(false);
        buttonFase16.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 16 está desbloqueada
        SharedPreferences prefs16 = getActivity().getSharedPreferences("MyPrefs17", MODE_PRIVATE);
        boolean isFase16Unlocked = prefs16.getBoolean("Fase16Unlocked3", false);

        // Habilitar o botão 16 se a fase 16 estiver desbloqueada
        if (isFase16Unlocked) {
            buttonFase16.setEnabled(true);
            buttonFase16.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }

        // Desabilitar o botão 17 inicialmente
        buttonFase17.setEnabled(false);
        buttonFase17.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 17 está desbloqueada
        SharedPreferences prefs17 = getActivity().getSharedPreferences("MyPrefs18", MODE_PRIVATE);
        boolean isFase17Unlocked = prefs17.getBoolean("Fase17Unlocked3", false);

        // Habilitar o botão 17 se a fase 17 estiver desbloqueada
        if (isFase17Unlocked) {
            buttonFase17.setEnabled(true);
            buttonFase17.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        // Desabilitar o botão 18 inicialmente
        buttonFase18.setEnabled(false);
        buttonFase18.setVisibility(View.INVISIBLE); // Definir como invisível

        // Verifica se a fase 18 está desbloqueada
        SharedPreferences prefs18 = getActivity().getSharedPreferences("MyPrefs19", MODE_PRIVATE);
        boolean isFase18Unlocked = prefs18.getBoolean("Fase18Unlocked3", false);

        // Habilitar o botão 18 se a fase 18 estiver desbloqueada
        if (isFase18Unlocked) {
            buttonFase18.setEnabled(true);
            buttonFase18.setVisibility(View.VISIBLE); // Tornar o botão visível se estiver desbloqueado
        }


        buttonFase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_1_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_2_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_3_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_4_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_5_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_6_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_7_Tela_0.class);
                startActivity(intent);
            }
        });


        buttonFase8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_8_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_9_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_10_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_11_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_12_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_13_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_14_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_15_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_16_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_17_Tela_0.class);
                startActivity(intent);
            }
        });

        buttonFase18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trilha_18_Tela_0.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
