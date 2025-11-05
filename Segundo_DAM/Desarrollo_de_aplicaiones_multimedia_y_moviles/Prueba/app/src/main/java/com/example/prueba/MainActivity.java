package com.example.prueba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button reset = findViewById(R.id.btnReset);
        reset.setOnClickListener(this);

        /*
         Antes la clase implementaba View.OnClickListener y se usaba "reset.setOnClickListener(this);"
         Ahora usamos una CLASE ANÓNIMA para registrar directamente el listener en el botón.
         Esto evita tener que implementar el metodo onClick() fuera de aquí.
        */
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView myText = findViewById(R.id.myText);
                myText.setText("¡Acabo de Resetear el Txt!");
            }
        });



    }


    /*
    VENTAJAS
     - El código del listener queda localizado justo donde se usa.
     - Es más rápido de escribir si el botón solo se utiliza una vez.


     INCONVENIENTES
     - No se puede reutilizar el mismo listener para varios botones.
     - Si hay muchos botones, el código puede volverse menos claro.
*/

    @Override
    public void onClick(View v) {
        TextView mytext= findViewById(R.id.myText);
        mytext.setText("¡Acabo de Resetar el Txt!");
    }


}