package hibridos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InicializarBaseDatos {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        
        // 1. Cargar el driver de SQLite
        try {
            // Asegúrate de que el driver esté en el classpath
            Class.forName("org.sqlite.JDBC"); 
            
            // 2. Conectar (crea el archivo 'inventario.db' si no existe)
            conn = DriverManager.getConnection("jdbc:sqlite:inventario.db");
            System.out.println("Base de datos 'inventario.db' conectada/creada exitosamente.");

            stmt = conn.createStatement();
            
            // 3. Borrar tabla si existe (para limpieza en caso de ejecuciones anteriores)
            String sqlDrop = "DROP TABLE IF EXISTS PRODUCTOS;";
            stmt.executeUpdate(sqlDrop);
            
            // 4. Crear la tabla PRODUCTOS
            String sqlCreate = "CREATE TABLE PRODUCTOS " +
                               "(ID INTEGER PRIMARY KEY, " +
                               " NOMBRE TEXT NOT NULL, " +
                               " STOCK INTEGER)";
            stmt.executeUpdate(sqlCreate);
            
            // 5. Insertar datos de prueba
            String sqlInsert1 = "INSERT INTO PRODUCTOS (ID, NOMBRE, STOCK) VALUES (1, 'Teclado Mecánico', 50);";
            String sqlInsert2 = "INSERT INTO PRODUCTOS (ID, NOMBRE, STOCK) VALUES (2, 'Mouse Óptico', 120);";
            String sqlInsert3 = "INSERT INTO PRODUCTOS (ID, NOMBRE, STOCK) VALUES (3, 'Monitor 24\"', 30);";
            
            stmt.executeUpdate(sqlInsert1);
            stmt.executeUpdate(sqlInsert2);
            stmt.executeUpdate(sqlInsert3);
            
            System.out.println("Tabla PRODUCTOS creada y datos insertados.");

        } catch ( Exception e ) {
            System.err.println( "Error de BD: " + e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                // Manejo de error de cierre
            }
        }
    }
}
