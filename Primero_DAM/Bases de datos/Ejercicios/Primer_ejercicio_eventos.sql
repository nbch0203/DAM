create table customer_backup_events (
customer_id int not null,
store_id int not null,
first_name varchar(45),
last_name varchar(45),
email varchar(50),
address_id int not null,
active int not null,
create_date datetime,
last_update datetime,
num_rentals int,
sum_payment decimal(10,2),
foreign key (address_id) references address(adress_id)
);

select * from customer;
/*insert into customer_backup_events (
customer_id,store_id,
first_name,last_name,
email,address_id,
active,create_date ,
last_update,num_rentals ,
sum_payment ) values (
(select customer_id from customer group by customer_id),(select store_id from customer group by customer_id),
(select first_name from customer group by customer_id),(select last_name from customer group by customer_id),
(select email from customer),(select address_id from customer group by customer_id),
(select active from customer group by customer_id),(select create_date from customer group by customer_id),
(select last_update from customer group by customer_id),(select num_rentals from customer group by customer_id),
(select sum_payment from customer group by customer_id)
);*/


/*drop table if exists customer_backup_events;
 create table if not exists customer_backup_events as select * from customer;
 select * from customer_backup_events;
*/
delimiter //
create event evento_copi_customer
on schedule every 1 minute
starts current_timestamp
do
begin

delete from customer where customer_id=(select customer_id from customer where customer_id=
(select max(customer_id) from customer_backup_events));
end//

drop event evento_copi_customer;

select * from customer_backup_events;

select * from customer;

 