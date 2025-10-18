-- Creación de Usuarios

-- Crear un usuario llamado analista1 con acceso desde localhost.

create user 'analista1'@'localhost' identified by 'contraseña';

-- Crear un usuario lector_sakila con permiso solo para leer (SELECT) la base de datos sakila.

create user 'lector_sakila'@'localhost' identified by 'contraseña';
grant select on sakila.* to 'lector_sakila'@'localhost';

drop user if exists 'analista1'@'%';

-- Crear un usuario admin_sakila con todos los privilegios sobre sakila desde cualquier host ('%').

CREATE USER 'admin_sakila'@'%' IDENTIFIED BY 'tu_contraseña_segura';
GRANT ALL PRIVILEGES ON sakila.* TO 'admin_sakila'@'%';

-- Crear un usuario empleado_tienda con permiso solo sobre las tablas inventory, rental y payment.

create user 'empleado_tienda'@'localhost' identified by 'contraseña';
GRANT SELECT, INSERT, UPDATE ON sakila.inventory TO 'empleado_tienda'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sakila.rental TO 'empleado_tienda'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sakila.payment TO 'empleado_tienda'@'localhost';

-- Asignación de Privilegios

-- Otorgar privilegios de INSERT, UPDATE y DELETE sobre la tabla rental al usuario empleado_tienda.

grant insert,update,delete on sakila.rental to 'empleado_tienda'@'localhost';

-- Revocar todos los privilegios del usuario empleado_tienda.

revoke all privileges,grant option from 'empleado_tienda'@'localhost';
-- REVOKE ALL PRIVILEGES FROM 'empleado_tienda'@'localhost';
SHOW GRANTS FOR 'empleado_tienda'@'localhost';

-- Modificar al usuario analista1 para que también tenga acceso remoto ('analista1'@'%').
CREATE USER 'analista1'@'%' IDENTIFIED BY 'contraseña';
-- ALTER USER 'analista1'@'%' IDENTIFIED BY 'contraseña';

-- Permitir que lector_sakila solo pueda consultar las tablas film y actor, pero no otras.
REVOKE SELECT ON sakila.* FROM 'lector_sakila'@'localhost';

grant select on sakila.film to 'lector_sakila'@'localhost';
grant select on sakila.actor to 'lector_sakila'@'localhost';

-- Roles (MySQL 8.0+)

-- Crear un rol llamado rol_lectura con acceso de solo lectura a toda la base sakila.

create role if not exists rol_lectura ;
grant select on sakila.* to rol_lectura;

-- Asignar el rol rol_lectura a los usuarios lector1, lector2 y lector3.
create user if not exists 'lector1'@'lcoalhost' identified by 'contraseña';
create user if not exists 'lector2'@'lcoalhost' identified by 'contraseña'; 
create user if not exists 'lector3'@'lcoalhost' identified by 'contraseña'; 

grant 'rol_lectura' to 'lector1'@'lcoalhost';
grant 'rol_lectura' to 'lector2'@'lcoalhost';
grant 'rol_lectura' to 'lector3'@'lcoalhost';

-- Crear un rol rol_ventas que permita acceso a payment, customer, rental.

revoke all privileges,grant option from 'rol_ventas';
create role if not exists 'rol_ventas';
grant  select on sakila.payment to 'rol_ventas';
grant  select on sakila.customer to 'rol_ventas';
grant  select on sakila.rental to 'rol_ventas';

-- Revocar el rol rol_lectura de lector2.

revoke 'rol_lectura' from 'lector2'@'localhost';
SHOW GRANTS FOR 'lector2'@'localhost';

-- Hacer que el rol rol_ventas se active por defecto al iniciar sesión.

alter user '';

-- Comprobación y Seguridad

-- Listar todos los usuarios creados en el servidor.

SELECT user,host FROM mysql.user;

-- Ver los privilegios que tiene lector_sakila.
SHOW GRANTS FOR 'lector_sakila'@'localhost';


-- Eliminar al usuario empleado_tienda del sistema.

drop user 'empleado_tienda'@'localhost' ;

-- Cambiar la contraseña del usuario admin_sakila.

ALTER USER 'admin_sakila'@'%' IDENTIFIED BY 'nueva_contraseña';

-- Crear un usuario invitado sin contraseña y luego asignarle una usando ALTER USER.

CREATE USER 'invitado'@'localhost' IDENTIFIED WITH mysql_native_password BY '';
ALTER USER 'invitado'@'localhost' IDENTIFIED BY 'contraseña_segura';

-- Mostrar todos los roles y usuarios asociados en el sistema.

SELECT user, host FROM mysql.user WHERE account_locked = 'Y';

-- Crear un usuario auditor que solo pueda hacer SELECT en todas las tablas excepto payment.

CREATE USER if not exists 'auditor'@'localhost' IDENTIFIED BY 'contraseña_segura';
REVOKE SELECT ON sakila.* FROM 'auditor'@'localhost';

-- Primero, revocar el SELECT global (por si se otorgó antes)

REVOKE SELECT ON sakila.* FROM 'auditor'@'localhost';

-- Ahora, otorgar SELECT tabla por tabla (excepto 'payment')

GRANT SELECT ON sakila.actor TO 'auditor'@'localhost';
GRANT SELECT ON sakila.address TO 'auditor'@'localhost';
GRANT SELECT ON sakila.category TO 'auditor'@'localhost';
GRANT SELECT ON sakila.city TO 'auditor'@'localhost';
GRANT SELECT ON sakila.country TO 'auditor'@'localhost';
GRANT SELECT ON sakila.customer TO 'auditor'@'localhost';
GRANT SELECT ON sakila.film TO 'auditor'@'localhost';
GRANT SELECT ON sakila.film_actor TO 'auditor'@'localhost';
GRANT SELECT ON sakila.film_category TO 'auditor'@'localhost';
GRANT SELECT ON sakila.inventory TO 'auditor'@'localhost';
GRANT SELECT ON sakila.language TO 'auditor'@'localhost';
GRANT SELECT ON sakila.rental TO 'auditor'@'localhost';
GRANT SELECT ON sakila.staff TO 'auditor'@'localhost';
GRANT SELECT ON sakila.store TO 'auditor'@'localhost';

