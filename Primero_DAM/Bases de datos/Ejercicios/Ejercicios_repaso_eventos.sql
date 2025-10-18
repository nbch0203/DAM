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

update rental set rental_return=now() where rental_id=1;
drop event evento1;
select * from rental;


delimiter //
create event evento2
on schedule every 1 day
starts current_timestamp()
do
begin 

declare v_select int;
set v_select=(select );

end//