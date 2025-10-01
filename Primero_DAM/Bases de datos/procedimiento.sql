DELIMITER //

CREATE PROCEDURE sp_rentas_por_cliente(
    IN p_customer_id INT,
    OUT p_total_rentas INT,
    OUT p_total_pagado DECIMAL(10,2)
)
BEGIN
    -- Este procedimiento calcula el número total de rentas y el monto total pagado por un cliente
    
    -- Contar el número de rentas del cliente
    SELECT COUNT(*) INTO p_total_rentas
    FROM rental
    WHERE customer_id = p_customer_id;
    
    -- Calcular el monto total pagado por el cliente
    SELECT IFNULL(SUM(amount), 0 INTO p_total_pagado
    FROM payment
    WHERE customer_id = p_customer_id;
    
    -- Mostrar información adicional del cliente (solo para referencia)
    SELECT 
        c.customer_id,
        CONCAT(c.first_name, ' ', c.last_name) AS nombre_cliente,
        p_total_rentas AS total_rentas,
        p_total_pagado AS total_pagado,
        a.address AS direccion,
        ci.city AS ciudad,
        co.country AS pais
    FROM customer c
    JOIN address a ON c.address_id = a.address_id
    JOIN city ci ON a.city_id = ci.city_id
    JOIN country co ON ci.country_id = co.country_id
    WHERE c.customer_id = p_customer_id;
    
END //

DELIMITER ;


call sp_rentas_por_cliente(2);