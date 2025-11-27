-- ============================================
-- CONSULTAS ÚTILES PARA TESTING - Cine_app
-- ============================================

USE CineDB;
GO

-- ============================================
-- 1. CONSULTAS BÁSICAS
-- ============================================

-- Ver todas las películas con su información
SELECT 
    Id,
    Titulo,
    Genero,
    Duracion,
    Director,
    Calificacion,
    FechaEstreno,
    Activa
FROM Peliculas
ORDER BY Titulo;
GO

-- Ver todas las salas con capacidad
SELECT 
    s.Id,
    s.Nombre,
    s.Filas,
    s.ColumnasPerFila,
    (s.Filas * s.ColumnasPerFila) as CapacidadTotal,
    COUNT(b.Id) as ButacasCreadas
FROM Salas s
LEFT JOIN Butacas b ON s.Id = b.SalaId
GROUP BY s.Id, s.Nombre, s.Filas, s.ColumnasPerFila;
GO

-- ============================================
-- 2. CONSULTAS DE SESIONES
-- ============================================

-- Ver sesiones para HOY
SELECT 
    s.Id as SesionId,
    p.Titulo as Pelicula,
    sa.Nombre as Sala,
    CONVERT(VARCHAR, s.FechaHora, 103) as Fecha,
    CONVERT(VARCHAR, s.FechaHora, 108) as Hora,
    s.Precio,
    sa.Filas * sa.ColumnasPerFila as CapacidadTotal,
    ISNULL((SELECT COUNT(*) FROM ReservaButacas rb WHERE rb.SesionId = s.Id), 0) as ButacasReservadas,
    (sa.Filas * sa.ColumnasPerFila) - ISNULL((SELECT COUNT(*) FROM ReservaButacas rb WHERE rb.SesionId = s.Id), 0) as ButacasDisponibles
FROM Sesiones s
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
WHERE CAST(s.FechaHora AS DATE) = CAST(GETDATE() AS DATE)
  AND s.Activa = 1
ORDER BY s.FechaHora;
GO

-- Ver todas las sesiones de una película específica (cambiar el ID)
DECLARE @PeliculaId INT = 1; -- Cambiar por el ID de la película

SELECT 
    s.Id as SesionId,
    p.Titulo as Pelicula,
    sa.Nombre as Sala,
    s.FechaHora,
    s.Precio,
    sa.Filas * sa.ColumnasPerFila as CapacidadTotal,
    ISNULL((SELECT COUNT(*) FROM ReservaButacas rb WHERE rb.SesionId = s.Id), 0) as ButacasReservadas
FROM Sesiones s
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
WHERE s.PeliculaId = @PeliculaId
  AND s.Activa = 1
  AND s.FechaHora >= GETDATE()
ORDER BY s.FechaHora;
GO

-- Ver sesiones por fecha específica
DECLARE @Fecha DATE = GETDATE(); -- Cambiar la fecha según necesites

SELECT 
    s.Id as SesionId,
    p.Titulo as Pelicula,
    p.Genero,
    sa.Nombre as Sala,
    FORMAT(s.FechaHora, 'HH:mm') as Hora,
    s.Precio,
    (sa.Filas * sa.ColumnasPerFila) - ISNULL((SELECT COUNT(*) FROM ReservaButacas rb WHERE rb.SesionId = s.Id), 0) as ButacasDisponibles
FROM Sesiones s
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
WHERE CAST(s.FechaHora AS DATE) = @Fecha
  AND s.Activa = 1
ORDER BY p.Titulo, s.FechaHora;
GO

-- ============================================
-- 3. CONSULTAS DE BUTACAS
-- ============================================

-- Ver butacas de una sala específica
DECLARE @SalaId INT = 1; -- Cambiar por el ID de la sala

SELECT 
    Fila,
    Columna,
    CHAR(64 + Fila) + CAST(Columna AS VARCHAR) as Identificador,
    Tipo,
    Activa
FROM Butacas
WHERE SalaId = @SalaId
ORDER BY Fila, Columna;
GO

-- Ver butacas ocupadas en una sesión específica
DECLARE @SesionId INT = 1; -- Cambiar por el ID de la sesión

SELECT 
    b.Id,
    b.Fila,
    b.Columna,
    CHAR(64 + b.Fila) + CAST(b.Columna AS VARCHAR) as Identificador,
    b.Tipo,
    r.CodigoReserva,
    u.Nombre + ' ' + u.Apellidos as Cliente
FROM ReservaButacas rb
INNER JOIN Butacas b ON rb.ButacaId = b.Id
INNER JOIN Reservas r ON rb.ReservaId = r.Id
INNER JOIN Usuarios u ON r.UsuarioId = u.Id
WHERE rb.SesionId = @SesionId
ORDER BY b.Fila, b.Columna;
GO

-- Ver disponibilidad de butacas por sesión
SELECT 
    s.Id as SesionId,
    p.Titulo as Pelicula,
    sa.Nombre as Sala,
    s.FechaHora,
    sa.Filas * sa.ColumnasPerFila as TotalButacas,
    COUNT(rb.Id) as ButacasReservadas,
    (sa.Filas * sa.ColumnasPerFila) - COUNT(rb.Id) as ButacasDisponibles,
    CAST((COUNT(rb.Id) * 100.0 / (sa.Filas * sa.ColumnasPerFila)) AS DECIMAL(5,2)) as PorcentajeOcupacion
FROM Sesiones s
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
LEFT JOIN ReservaButacas rb ON s.Id = rb.SesionId
WHERE s.FechaHora >= GETDATE()
  AND s.Activa = 1
GROUP BY s.Id, p.Titulo, sa.Nombre, s.FechaHora, sa.Filas, sa.ColumnasPerFila
ORDER BY s.FechaHora;
GO

-- ============================================
-- 4. CONSULTAS DE RESERVAS
-- ============================================

-- Ver todas las reservas con detalle
SELECT 
    r.Id,
    r.CodigoReserva,
    u.Nombre + ' ' + u.Apellidos as Cliente,
    u.Email,
    p.Titulo as Pelicula,
    sa.Nombre as Sala,
    FORMAT(s.FechaHora, 'dd/MM/yyyy HH:mm') as FechaHoraSesion,
    r.FechaReserva,
    COUNT(rb.Id) as NumButacas,
    r.Total,
    r.Estado
FROM Reservas r
INNER JOIN Usuarios u ON r.UsuarioId = u.Id
INNER JOIN Sesiones s ON r.SesionId = s.Id
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
LEFT JOIN ReservaButacas rb ON r.Id = rb.ReservaId
GROUP BY r.Id, r.CodigoReserva, u.Nombre, u.Apellidos, u.Email, p.Titulo, 
         sa.Nombre, s.FechaHora, r.FechaReserva, r.Total, r.Estado
ORDER BY r.FechaReserva DESC;
GO

-- Ver reservas de un usuario específico
DECLARE @UsuarioId INT = 1; -- Cambiar por el ID del usuario

SELECT 
    r.CodigoReserva,
    p.Titulo as Pelicula,
    s.FechaHora as FechaHoraSesion,
    sa.Nombre as Sala,
    COUNT(rb.Id) as NumButacas,
    r.Total,
    r.Estado
FROM Reservas r
INNER JOIN Sesiones s ON r.SesionId = s.Id
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
LEFT JOIN ReservaButacas rb ON r.Id = rb.ReservaId
WHERE r.UsuarioId = @UsuarioId
GROUP BY r.CodigoReserva, p.Titulo, s.FechaHora, sa.Nombre, r.Total, r.Estado
ORDER BY s.FechaHora DESC;
GO

-- Ver detalle completo de una reserva específica
DECLARE @ReservaId INT = 1; -- Cambiar por el ID de la reserva

SELECT 
    r.CodigoReserva,
    u.Nombre + ' ' + u.Apellidos as Cliente,
    u.Email,
    u.Telefono,
    p.Titulo as Pelicula,
    p.Genero,
    sa.Nombre as Sala,
    FORMAT(s.FechaHora, 'dd/MM/yyyy') as Fecha,
    FORMAT(s.FechaHora, 'HH:mm') as Hora,
    r.FechaReserva,
    STRING_AGG(CHAR(64 + b.Fila) + CAST(b.Columna AS VARCHAR), ', ') as Butacas,
    COUNT(rb.Id) as NumButacas,
    r.Total,
    r.Estado
FROM Reservas r
INNER JOIN Usuarios u ON r.UsuarioId = u.Id
INNER JOIN Sesiones s ON r.SesionId = s.Id
INNER JOIN Peliculas p ON s.PeliculaId = p.Id
INNER JOIN Salas sa ON s.SalaId = sa.Id
LEFT JOIN ReservaButacas rb ON r.Id = rb.ReservaId
LEFT JOIN Butacas b ON rb.ButacaId = b.Id
WHERE r.Id = @ReservaId
GROUP BY r.CodigoReserva, u.Nombre, u.Apellidos, u.Email, u.Telefono, p.Titulo, 
         p.Genero, sa.Nombre, s.FechaHora, r.FechaReserva, r.Total, r.Estado;
GO

-- ============================================
-- 5. ESTADÍSTICAS
-- ============================================

-- Películas más reservadas
SELECT TOP 10
    p.Titulo,
    p.Genero,
    COUNT(DISTINCT r.Id) as NumReservas,
    COUNT(rb.Id) as TotalButacasVendidas,
    SUM(r.Total) as RecaudacionTotal
FROM Peliculas p
INNER JOIN Sesiones s ON p.Id = s.PeliculaId
INNER JOIN Reservas r ON s.Id = r.SesionId
INNER JOIN ReservaButacas rb ON r.Id = rb.ReservaId
WHERE r.Estado = 'Confirmada'
GROUP BY p.Titulo, p.Genero
ORDER BY TotalButacasVendidas DESC;
GO

-- Ocupación por sala
SELECT 
    sa.Nombre as Sala,
    sa.Filas * sa.ColumnasPerFila as CapacidadTotal,
    COUNT(DISTINCT s.Id) as NumSesiones,
    COUNT(rb.Id) as TotalButacasVendidas,
    CAST((COUNT(rb.Id) * 100.0 / (COUNT(DISTINCT s.Id) * sa.Filas * sa.ColumnasPerFila)) AS DECIMAL(5,2)) as PorcentajeOcupacionPromedio
FROM Salas sa
LEFT JOIN Sesiones s ON sa.Id = s.SalaId AND s.FechaHora >= DATEADD(DAY, -7, GETDATE())
LEFT JOIN ReservaButacas rb ON s.Id = rb.SesionId
GROUP BY sa.Nombre, sa.Filas, sa.ColumnasPerFila
ORDER BY sa.Nombre;
GO

-- Ingresos por día
SELECT 
    CAST(s.FechaHora AS DATE) as Fecha,
    COUNT(DISTINCT r.Id) as NumReservas,
    COUNT(rb.Id) as ButacasVendidas,
    SUM(r.Total) as TotalIngresos
FROM Sesiones s
INNER JOIN Reservas r ON s.Id = r.SesionId
INNER JOIN ReservaButacas rb ON r.Id = rb.ReservaId
WHERE r.Estado = 'Confirmada'
  AND s.FechaHora >= DATEADD(DAY, -30, GETDATE())
GROUP BY CAST(s.FechaHora AS DATE)
ORDER BY Fecha DESC;
GO

-- ============================================
-- 6. PROCEDIMIENTOS PARA TESTING
-- ============================================

-- Verificar si una butaca está disponible para una sesión
CREATE OR ALTER PROCEDURE sp_VerificarDisponibilidadButaca
    @ButacaId INT,
    @SesionId INT
AS
BEGIN
    SELECT 
        CASE 
            WHEN EXISTS (
                SELECT 1 FROM ReservaButacas 
                WHERE ButacaId = @ButacaId AND SesionId = @SesionId
            ) THEN 'OCUPADA'
            ELSE 'DISPONIBLE'
        END as Estado;
END
GO

-- Obtener butacas disponibles para una sesión
CREATE OR ALTER PROCEDURE sp_ObtenerButacasDisponibles
    @SesionId INT
AS
BEGIN
    SELECT 
        b.Id,
        b.Fila,
        b.Columna,
        CHAR(64 + b.Fila) + CAST(b.Columna AS VARCHAR) as Identificador,
        b.Tipo,
        CASE 
            WHEN rb.Id IS NULL THEN 'DISPONIBLE'
            ELSE 'OCUPADA'
        END as Estado
    FROM Butacas b
    INNER JOIN Sesiones s ON b.SalaId = s.SalaId
    LEFT JOIN ReservaButacas rb ON b.Id = rb.ButacaId AND rb.SesionId = @SesionId
    WHERE s.Id = @SesionId
      AND b.Activa = 1
    ORDER BY b.Fila, b.Columna;
END
GO

-- Crear una reserva nueva
CREATE OR ALTER PROCEDURE sp_CrearReserva
    @UsuarioId INT,
    @SesionId INT,
    @ButacasIds NVARCHAR(MAX), -- IDs separados por coma: '1,2,3'
    @Total DECIMAL(10,2)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;
        
        DECLARE @ReservaId INT;
        DECLARE @CodigoReserva NVARCHAR(50);
        
        -- Generar código de reserva único
        SET @CodigoReserva = 'RES-' + FORMAT(GETDATE(), 'yyyy') + '-' + 
                             RIGHT('00000' + CAST(NEXT VALUE FOR seq_ReservaNumero AS VARCHAR), 5);
        
        -- Crear la reserva
        INSERT INTO Reservas (UsuarioId, SesionId, FechaReserva, Total, Estado, CodigoReserva)
        VALUES (@UsuarioId, @SesionId, GETDATE(), @Total, 'Pendiente', @CodigoReserva);
        
        SET @ReservaId = SCOPE_IDENTITY();
        
        -- Insertar las butacas reservadas
        INSERT INTO ReservaButacas (ReservaId, ButacaId, SesionId)
        SELECT @ReservaId, value, @SesionId
        FROM STRING_SPLIT(@ButacasIds, ',');
        
        COMMIT TRANSACTION;
        
        SELECT @ReservaId as ReservaId, @CodigoReserva as CodigoReserva;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END
GO

-- Crear secuencia para códigos de reserva
IF NOT EXISTS (SELECT * FROM sys.sequences WHERE name = 'seq_ReservaNumero')
    CREATE SEQUENCE seq_ReservaNumero START WITH 1 INCREMENT BY 1;
GO

PRINT '============================================';
PRINT 'Consultas y procedimientos de testing creados';
PRINT '============================================';
PRINT 'Usa estas consultas para verificar el funcionamiento';
PRINT 'de tu aplicación Cine_app';
PRINT '============================================';
