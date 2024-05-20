package com.example.intimalearn;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceControl;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.inicio) {
                    loadFragment(new Inicio(), false);

                } else if (itemId == R.id.trilha) {
                    loadFragment(new Trilha(), false);


                } else if (itemId == R.id.artigos){
                    loadFragment(new Artigos(), false);

                } else if (itemId == R.id.recomendado){
                    loadFragment(new Artigos(), false);

                } else if (itemId == R.id.maisVistos){
                    loadFragment(new Artigos(), false);

                } else {
                    loadFragment(new Perfil(),false);

                }
                return true;
            }
        });

        loadFragment(new Inicio(), true);
    }

    private void loadFragment(Fragment fragment, boolean isAppInicialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (isAppInicialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }
}