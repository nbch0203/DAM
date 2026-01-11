package com.example.examen_nixon_cruz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

  private int variable=20;

    public int getVariable() {
        return variable;
    }

    public void setVariable(int variable) {
        this.variable = variable;
    }

    String texto1="El proximo tren llega en: ";
    String texto2=" minutos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textview=findViewById(R.id.MainTextview);
        Button  IncrementButton=findViewById(R.id.IncrementButton);
        Button  DecrementButton=findViewById(R.id.DecrementButton);
        Button reset=findViewById(R.id.resetButton);



        IncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVariable(getVariable()+1);
                textview.setText(texto1+getVariable()+texto2);

            }
        });

        DecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVariable(getVariable()-1);
                if (getVariable()>0){
                    textview.setText(texto1+ getVariable()+texto2);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText(texto1+20+texto2);
            }
        });


    }
}