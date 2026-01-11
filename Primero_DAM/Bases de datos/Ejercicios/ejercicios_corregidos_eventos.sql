-- Ejercicio 1 ·············································3 
-- SE CORRIJIÓ EN CLASE


-- Ejercicio 2 ·············································3 
drop EVENT DAILY_PAYMENT_PENALTY;
CREATE EVENT IF NOT EXISTS daily_payment_penalty
ON SCHEDULE EVERY 1 DAY
DO
  UPDATE payment p
  JOIN rental r ON p.rental_id = r.rental_id
  JOIN inventory i ON r.inventory_id = i.inventory_id
  JOIN film f ON i.film_id = f.film_id
  SET p.amount = p.amount + 400.00  -- Sumar los 2€ de penalización
  WHERE r.return_date IS NULL
    AND DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY) < CURDATE();
    
   
   
   
   
   
   
   
   
   -- Ejercicio 3 ·············································
   
   CREATE TABLE IF NOT EXISTS rewards (
    reward_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id smallint UNSIGNED,
    week_start DATE,
    week_end DATE,
    amount_spent DECIMAL(10,2),
    reward_points DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
select * from rewards;


drop event weekly_rewards;
CREATE EVENT IF NOT EXISTS weekly_rewards
ON SCHEDULE EVERY 1 WEEK -- STARTS CURRENT_DATE + INTERVAL (7 - DAYOFWEEK(CURRENT_DATE)) DAY
DO
  INSERT INTO rewards (customer_id, week_start, week_end, amount_spent, reward_points)
  SELECT p.customer_id,
         DATE_SUB(CURDATE(), INTERVAL 7 DAY),
         CURDATE(),
         SUM(p.amount),
         ROUND(SUM(p.amount) * 0.10, 2)
  FROM payment p
  WHERE p.payment_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()
  GROUP BY p.customer_id;
 
 
 
-- Ejercicio 4 ·············································3 
 
 ALTER TABLE customer ADD COLUMN vip_status BOOLEAN DEFAULT FALSE;


drop event update_vip_status;
CREATE EVENT IF NOT EXISTS update_vip_status
ON SCHEDULE EVERY 1 MONTH
DO
  UPDATE customer c
  JOIN (
    SELECT customer_id, SUM(amount) AS total_spent
    FROM payment
    WHERE MONTH(payment_date) = MONTH(CURDATE() - INTERVAL 1 MONTH)
      AND YEAR(payment_date) = YEAR(CURDATE() - INTERVAL 1 MONTH)
    GROUP BY customer_id
  ) p ON c.customer_id = p.customer_id
  SET c.vip_status = IF(p.total_spent > 100, TRUE, FALSE);

 