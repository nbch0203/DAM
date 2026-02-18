package com.example.ejercicio3;
// Nixon Bolivar Cruz Hidalgo
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context contexto, String nombreBD) {
        super(contexto, nombreBD, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        try {
            String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS productos (codigo INTEGER PRIMARY KEY, nombre TEXT NOT NULL)";
            bd.execSQL(sqlCrearTabla);

            bd.execSQL("INSERT INTO productos (codigo, nombre) VALUES (1, 'Producto A')");
            bd.execSQL("INSERT INTO productos (codigo, nombre) VALUES (2, 'Producto B')");
            bd.execSQL("INSERT INTO productos (codigo, nombre) VALUES (3, 'Producto C')");
            bd.execSQL("INSERT INTO productos (codigo, nombre) VALUES (4, 'Producto D')");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la base de datos: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        try {
            if (versionAnterior == 1 && versionNueva == 2) {
                bd.execSQL("ALTER TABLE productos ADD COLUMN descripcion TEXT DEFAULT ''");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la base de datos: " + e.getMessage());
        }
    }

    public String ejecutarSQL(String sentenciaSQL) {
        try {
            SQLiteDatabase bd = getWritableDatabase();
            bd.execSQL(sentenciaSQL);
            return "SQL ejecutado correctamente";
        } catch (Exception e) {
            return "Error al ejecutar SQL: " + e.getMessage();
        }
    }

    public String ejecutarConsulta(String consulta) {
        Cursor cursor = null;
        try {
            SQLiteDatabase bd = getReadableDatabase();
            cursor = bd.rawQuery(consulta, null);

            StringBuilder resultado = new StringBuilder();

            // Obtener información sobre las columnas
            int numeroColumnas = cursor.getColumnCount();
            String[] nombresColumnas = cursor.getColumnNames();

            // Construir encabezados
            for (int i = 0; i < numeroColumnas; i++) {
                resultado.append(nombresColumnas[i]);
                if (i < numeroColumnas - 1) {
                    resultado.append(" | ");
                }
            }
            resultado.append("\n");
            resultado.append("-".repeat(50)).append("\n");

            // Procesar filas de resultados
            int numeroFilas = 0;
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < numeroColumnas; i++) {
                        String valorCelda;
                        int tipoDato = cursor.getType(i);

                        switch (tipoDato) {
                            case Cursor.FIELD_TYPE_NULL:
                                valorCelda = "NULL";
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                valorCelda = String.valueOf(cursor.getLong(i));
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                valorCelda = String.valueOf(cursor.getDouble(i));
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                valorCelda = cursor.getString(i);
                                break;
                            case Cursor.FIELD_TYPE_BLOB:
                                valorCelda = "[BLOB]";
                                break;
                            default:
                                valorCelda = "[DESCONOCIDO]";
                        }

                        resultado.append(valorCelda);
                        if (i < numeroColumnas - 1) {
                            resultado.append(" | ");
                        }
                    }
                    resultado.append("\n");
                    numeroFilas++;
                } while (cursor.moveToNext());

                resultado.append("-".repeat(50)).append("\n");
                resultado.append("Total de filas: ").append(numeroFilas).append("\n");
            } else {
                resultado.append("No hay resultados\n");
            }

            return resultado.toString();
        } catch (Exception e) {
            return "Error al ejecutar consulta: " + e.getMessage();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
