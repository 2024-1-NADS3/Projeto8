package com.example.intimalearn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Artigos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artigos, container, false);
        ImageView imageView = view.findViewById(R.id.item01);
        ImageView imageView1 = view.findViewById(R.id.item02);

        TextView textView = view.findViewById(R.id.recomendado);
        TextView maisVistos = view.findViewById(R.id.maisVistos);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtigosItem01(v);
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtigosItem02(v);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtigosRecomendado(v);
            }
        });

        maisVistos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Artigos_Mais_Vistos(v); }
        });


        return view;
    }

    public void ArtigosItem01(View view) {
        Intent intent = new Intent(getActivity(), Artigos_Item_1.class);
        startActivity(intent);
    }

    public void ArtigosItem02(View view) {
        Intent intent = new Intent(getActivity(), Artigos_Item_2.class);
        startActivity(intent);
    }

    public void ArtigosRecomendado(View view) {
        Intent intent = new Intent(getActivity(), Artigos_Recomendado.class);
        startActivity(intent);
    }

    public void Artigos_Mais_Vistos(View view) {
        Intent intent = new Intent(getActivity(), Artigos_Mais_Vistos.class);
        startActivity(intent);
    }
}