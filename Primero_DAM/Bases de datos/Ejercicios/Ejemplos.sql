
create user 'usu'@'localhost' identified by 
'password';

create role ejemplo;

grant select on sakila.rental 
to ejemplo;


grant ejemplo to 
'usu'@'localhost';
REVOKE INSERT, UPDATE, DELETE ON prestashop.* FROM 'user'@'localhost';

SELECT * FROM mysql.user;

drop user 'usu'@'localhost';
drop role 'ejemplo';


delimiter //
create event evento1
on schedule every 1 minute
starts current_timestamp()
do
begin 

declare v_select timestamp;
set v_select =(select return_date from rental);

if datediff(now(),v_select)<=2 then

update rental set return_date='2025-05-26 17:00:00';
end if;

end//