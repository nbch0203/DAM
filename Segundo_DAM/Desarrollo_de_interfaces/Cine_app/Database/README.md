# Base de Datos para Cine_app

Este directorio contiene los scripts SQL necesarios para configurar y testear la aplicación de cine.

## Archivos incluidos

1. **cinema_database.sql** - Script principal que crea toda la estructura de la base de datos y datos de prueba
2. **testing_queries.sql** - Consultas útiles y procedimientos almacenados para testing

## Requisitos

- SQL Server 2016 o superior
- SQL Server Management Studio (SSMS) o Azure Data Studio

## Instrucciones de instalación

### Paso 1: Crear la base de datos

1. Abre SQL Server Management Studio (SSMS)
2. Conéctate a tu instancia de SQL Server
3. Abre el archivo `cinema_database.sql`
4. Ejecuta el script completo (F5 o botón "Execute")

El script creará:
- La base de datos `CineDB`
- 7 tablas principales
- Datos de prueba

### Paso 2: Configurar la cadena de conexión

Actualiza tu archivo `.env` o configuración con la cadena de conexión:

```
DB_CONNECTION_STRING=Server=localhost;Database=CineDB;Integrated Security=True;TrustServerCertificate=True;
```

O si usas SQL Server Authentication:

```
DB_CONNECTION_STRING=Server=localhost;Database=CineDB;User Id=tu_usuario;Password=tu_password;TrustServerCertificate=True;
```

## Estructura de la base de datos

### Tablas

#### 1. Usuarios
Almacena información de los usuarios registrados.
- Id (PK)
- Nombre
- Apellidos
- Email (único)
- Password
- Telefono
- FechaRegistro
- Activo

#### 2. Peliculas
Catálogo de películas disponibles.
- Id (PK)
- Titulo
- Descripcion
- Director
- Duracion (minutos)
- Genero
- FechaEstreno
- ImagenUrl
- Calificacion
- Activa

#### 3. Salas
Configuración de las salas de cine.
- Id (PK)
- Nombre
- Filas
- ColumnasPerFila

#### 4. Sesiones
Horarios de proyección de películas.
- Id (PK)
- PeliculaId (FK)
- SalaId (FK)
- FechaHora
- Precio
- Activa

#### 5. Butacas
Asientos individuales en cada sala.
- Id (PK)
- SalaId (FK)
- Fila
- Columna
- Tipo (Normal, VIP, Discapacitado)
- Activa

#### 6. Reservas
Reservas realizadas por los usuarios.
- Id (PK)
- UsuarioId (FK)
- SesionId (FK)
- FechaReserva
- Total
- Estado (Pendiente, Confirmada, Cancelada)
- CodigoReserva

#### 7. ReservaButacas
Relación entre reservas y butacas específicas.
- Id (PK)
- ReservaId (FK)
- ButacaId (FK)
- SesionId (FK)

## Datos de prueba incluidos

### Usuarios (4)
- juan.perez@email.com
- maria.lopez@email.com
- carlos.gonzalez@email.com
- ana.rodriguez@email.com

**Contraseña para todos:** `password123`

### Películas (10)
1. Oppenheimer
2. Barbie
3. Dune: Parte Dos
4. Guardianes de la Galaxia Vol. 3
5. Spider-Man: Across the Spider-Verse
6. The Super Mario Bros. Movie
7. Inception
8. The Dark Knight
9. Interstellar
10. Avatar: The Way of Water

### Salas (4)
- Sala 1 - Premium (8x10 = 80 butacas)
- Sala 2 - Estándar (10x12 = 120 butacas)
- Sala 3 - VIP (6x8 = 48 butacas)
- Sala 4 - IMAX (12x15 = 180 butacas)

**Total de butacas:** 428

### Sesiones
- 40+ sesiones distribuidas en los próximos 4 días
- Horarios variados desde las 15:00 hasta las 22:00
- Precios entre 8.50€ y 15.00€

### Reservas (3 de ejemplo)
Algunas reservas ya creadas para testing

## Consultas útiles para testing

Después de ejecutar `cinema_database.sql`, puedes ejecutar `testing_queries.sql` que incluye:

### Consultas básicas
- Ver todas las películas
- Ver todas las salas con capacidad
- Ver sesiones del día
- Ver sesiones de una película específica

### Consultas de disponibilidad
- Ver butacas ocupadas en una sesión
- Ver disponibilidad de butacas por sesión
- Verificar si una butaca está disponible

### Consultas de reservas
- Ver todas las reservas
- Ver reservas de un usuario
- Ver detalle completo de una reserva

### Estadísticas
- Películas más reservadas
- Ocupación por sala
- Ingresos por día

### Procedimientos almacenados

#### sp_VerificarDisponibilidadButaca
Verifica si una butaca está disponible para una sesión.

```sql
EXEC sp_VerificarDisponibilidadButaca @ButacaId = 1, @SesionId = 1;
```

#### sp_ObtenerButacasDisponibles
Obtiene todas las butacas disponibles para una sesión.

```sql
EXEC sp_ObtenerButacasDisponibles @SesionId = 1;
```

#### sp_CrearReserva
Crea una nueva reserva con las butacas especificadas.

```sql
EXEC sp_CrearReserva 
    @UsuarioId = 1, 
    @SesionId = 1, 
    @ButacasIds = '1,2,3', 
    @Total = 37.50;
```

## Verificación de la instalación

Ejecuta estas consultas para verificar que todo se instaló correctamente:

```sql
-- Contar registros en cada tabla
SELECT 'Usuarios' as Tabla, COUNT(*) as Total FROM Usuarios
UNION ALL
SELECT 'Peliculas', COUNT(*) FROM Peliculas
UNION ALL
SELECT 'Salas', COUNT(*) FROM Salas
UNION ALL
SELECT 'Butacas', COUNT(*) FROM Butacas
UNION ALL
SELECT 'Sesiones', COUNT(*) FROM Sesiones
UNION ALL
SELECT 'Reservas', COUNT(*) FROM Reservas
UNION ALL
SELECT 'ReservaButacas', COUNT(*) FROM ReservaButacas;

-- Ver sesiones de hoy
SELECT 
    p.Titulo,
    sa.Nombre as Sala,
    FORMAT(s.FechaHora, 'HH:mm') as Hora,
    s.Precio
FROM Sesiones s
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
WHERE CAST(s.FechaHora AS DATE) = CAST(GETDATE() AS DATE)
ORDER BY s.FechaHora;
```

## Escenarios de testing recomendados

### 1. Testing de cartelera
- Verifica que se muestren todas las películas activas
- Comprueba que las imágenes se carguen correctamente
- Valida que la información (género, duración, director) sea correcta

### 2. Testing de sesiones
- Selecciona diferentes películas y verifica sus sesiones
- Prueba con diferentes fechas (hoy, mañana, pasado mañana)
- Valida que los precios se muestren correctamente

### 3. Testing de selección de butacas
- Verifica que las butacas ocupadas no se puedan seleccionar
- Prueba seleccionar múltiples butacas
- Valida los diferentes tipos de butacas (Normal, VIP, Discapacitado)

### 4. Testing de reservas
- Crea una reserva completa
- Verifica que se genere el código de reserva
- Valida que las butacas queden marcadas como ocupadas
- Comprueba que el total se calcule correctamente

## Mantenimiento

### Limpiar reservas antiguas
```sql
DELETE FROM ReservaButacas 
WHERE SesionId IN (
    SELECT Id FROM Sesiones 
    WHERE FechaHora < DATEADD(DAY, -30, GETDATE())
);

DELETE FROM Reservas 
WHERE SesionId IN (
    SELECT Id FROM Sesiones 
    WHERE FechaHora < DATEADD(DAY, -30, GETDATE())
);

DELETE FROM Sesiones 
WHERE FechaHora < DATEADD(DAY, -30, GETDATE());
```

### Agregar más sesiones
```sql
-- Ejemplo: Agregar sesiones para una película en una fecha específica
INSERT INTO Sesiones (PeliculaId, SalaId, FechaHora, Precio, Activa)
VALUES 
(1, 4, '2024-12-25 16:00:00', 12.50, 1),
(1, 4, '2024-12-25 19:00:00', 14.00, 1),
(1, 4, '2024-12-25 22:00:00', 14.00, 1);
```

### Agregar nueva película
```sql
INSERT INTO Peliculas (Titulo, Descripcion, Director, Duracion, Genero, FechaEstreno, ImagenUrl, Calificacion, Activa)
VALUES 
('Nueva Película', 'Descripción de la película', 'Director', 120, 'Género', GETDATE(), 'url_imagen', 8.0, 1);
```

## Solución de problemas

### Error: Database already exists
Si ya existe la base de datos, elimínala primero:
```sql
USE master;
GO
DROP DATABASE CineDB;
GO
```

### Error: Cannot insert duplicate key
Esto puede ocurrir si ejecutas el script múltiples veces. Elimina y recrea la base de datos.

### Error de conexión desde la aplicación
Verifica:
1. Que SQL Server esté ejecutándose
2. Que la cadena de conexión sea correcta
3. Que el usuario tenga permisos en la base de datos
4. Que el firewall permita la conexión

## Soporte

Para más información sobre la aplicación, consulta la documentación principal del proyecto.
