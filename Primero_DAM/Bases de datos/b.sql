use bbdd;

INSERT INTO cliente (nombre, email, telefono) VALUES
('Juan Pérez', 'juan.perez@example.com', '555-1234'),
('María López', 'maria.lopez@example.com', '555-5678'),
('Carlos García', 'carlos.garcia@example.com', '555-8765'),
('Ana Torres', 'ana.torres@example.com', '555-4321'),
('Luis Fernández', 'luis.fernandez@example.com', '555-6789'),
('Laura Martínez', 'laura.martinez@example.com', '555-9876'),
('José Ramírez', 'jose.ramirez@example.com', '555-3456');

INSERT INTO pedido (cliente_id, fecha, total) VALUES
(1, '2023-10-01', 150.00),
(2, '2023-10-02', 200.00),
(3, '2023-10-03', 250.00),
(4, '2023-10-04', 300.00),
(5, '2023-10-05', 350.00),
(6, '2023-10-06', 400.00),
(7, '2023-10-07', 450.00);