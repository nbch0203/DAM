package com.example.ejercicio2;
// Nixon Bolivar Cruz Hidalgo
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.StringBuilder;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No crear tablas por defecto. El usuario puede ejecutar SQL desde la UI.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No-op para este ejemplo.
    }

    public String ejecutarSQL(String sql) {
        if (sql == null) return "Sentencia vacía";
        String trimmed = sql.trim().toLowerCase();
        try {
            if (trimmed.startsWith("select")) {
                Cursor cursor = getReadableDatabase().rawQuery(sql, null);
                return formatCursor(cursor);
            } else {
                getWritableDatabase().execSQL(sql);
                return "Sentencia ejecutada correctamente";
            }
        } catch (Exception e) {
            return "Error al ejecutar SQL: " + e.getMessage();
        }
    }

    private String formatCursor(Cursor cursor) {
        if (cursor == null) return "Resultado vacío";
        StringBuilder sb = new StringBuilder();
        try {
            int columnCount = cursor.getColumnCount();
            // Encabezados
            for (int i = 0; i < columnCount; i++) {
                sb.append(cursor.getColumnName(i));
                if (i < columnCount - 1) sb.append(" | ");
            }
            sb.append("\n");
            // Filas
            while (cursor.moveToNext()) {
                for (int i = 0; i < columnCount; i++) {
                    String value = cursor.getString(i);
                    sb.append(value != null ? value : "NULL");
                    if (i < columnCount - 1) sb.append(" | ");
                }
                sb.append("\n");
            }
            return sb.toString();
        } finally {
            cursor.close();
        }
    }
}
