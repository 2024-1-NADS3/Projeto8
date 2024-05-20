package com.example.intimalearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Artigos_Item_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigos_item1);

        Button button = findViewById(R.id.visitarSite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.amazon.com.br/Bem-resolvida-Sinta-se-confiante-realizada/dp/8595086451/ref=asc_df_8595086451/?tag=googleshopp00-20&linkCode=df0&hvadid=405648340497&hvpos=&hvnetw=g&hvrand=5048995245083564118&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1001773&hvtargid=pla-848362000941&psc=1&mcid=ce3f80d1f39c367d857f7c2e60d79d67";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}