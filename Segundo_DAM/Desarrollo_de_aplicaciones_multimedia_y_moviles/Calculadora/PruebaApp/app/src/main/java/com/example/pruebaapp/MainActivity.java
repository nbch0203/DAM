package com.example.pruebaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.editTextText);
        et2=findViewById(R.id.editTextText2);
        tv1=findViewById(R.id.textView2);

    }

    public void sumar(View view){
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        int num1= Integer.parseInt(valor1);
        int num2= Integer.parseInt(valor2);
        int suma=num1+num2;
        String resu=String.valueOf(suma);
        tv1.setText(resu);
    }
}