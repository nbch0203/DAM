package com.example.basesdedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Datos datos = new Datos(this, "Instituto", null, 2);
        datos.getWritableDatabase();
        datos.ejecutaSQL();
    }

    public static class Datos extends SQLiteOpenHelper {
        private static final String TABLE_ALUMNOS = "Alumnos";
        private static final String CREATE_ALUMNOS =
                "CREATE TABLE " + TABLE_ALUMNOS + "(" +
                        "dni TEXT PRIMARY KEY," +
                        "nombre TEXT" +
                        ")";

        public Datos(@Nullable Context context, @Nullable String name,
                     @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_ALUMNOS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < 2 && newVersion >= 2) {
                db.execSQL("ALTER TABLE " + TABLE_ALUMNOS + " ADD COLUMN email TEXT");
            }
        }

        public void ejecutaSQL() {
            SQLiteDatabase db = this.getWritableDatabase();
            String dni = "11111111A";
            db.execSQL("INSERT INTO Alumnos(dni,nombre) VALUES ('" + dni + "','Alfonso')");
            dni = "11111111B";
            db.execSQL("INSERT INTO Alumnos(dni,nombre) VALUES ('" + dni + "','Alfonsa')");
            db.execSQL("DELETE FROM Alumnos WHERE dni='11111111B'");
            db.execSQL("UPDATE Alumnos SET nombre='Pedro' WHERE dni='22222222B'");
            db.execSQL("CREATE TABLE Profesores(codigo INT PRIMARY KEY, nombre VARCHAR(50))");
        }

    }

}