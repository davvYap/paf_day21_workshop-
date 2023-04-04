# paf_day21_workshop
# Queries to manipulate DB:

// task 1  - 2
create table customer(
id INT NOT NULL PRIMARY KEY,
company VARCHAR(50),
last_name VARCHAR(50),
first_name VARCHAR(50),
email VARCHAR(50),
job_title VARCHAR(50),
business_phone VARCHAR(50),
home_phone VARCHAR(50),
mobile_phone VARCHAR(50),
address VARCHAR(50),
state_province VARCHAR(50)
);

insert into customer(id,company,last_name,first_name,email,job_title,business_phone,home_phone,mobile_phone,address,state_province)
values
(1,"ABC Company","John","Wick","johnw@gmail.com","assassin","88889999","99998888","77778888","212, London Street","London"),
(2,"ABC Company","Dep","Nick","nick@gmail.com","manager","21220000","99998888","77778888","99, China Town","London"),
(3,"ABC Company","Davv","Yp","dypp@gmail.com","software engineer","88883333","33992288","77778888","59, Wallstreet","London"),
(4,"ABC Company","Tomas","Hong","tomas@gmail.com","software developer","88889999","99998888","77778888","88, Wallstreet","London"),
(5,"ABC Company","Yunice","Tean","yunice@gmail.com","lecturer","88889999","99998888","77778888","818, Cambridge","London");


select id, company, first_name, last_name from customer limit 2 , 1;
select id, company, first_name, last_name from customer limit 2 offset 1;

select id, company, first_name, last_name from customer where id = 4;


// task 3
create table orders(
id INT NOT NULL PRIMARY KEY,
order_date DATE,
shipping_date DATE,
ship_name VARCHAR(50),
shipping_fees DECIMAL(10,2),
customer_id INT,
FOREIGN KEY(customer_id) REFERENCES customer(id)
);

insert into orders 
values
(1,'2022-01-03','2022-03-01','Sunny','9.99',1),
(2,'2022-01-04','2022-03-02','Moon','10.99',3),
(3,'2022-01-05','2022-03-03','Dream','11.00',4),
(4,'2022-01-06','2022-03-04','Wonder','5.99',5),
(5,'2022-01-07','2022-03-05','Titanic','3.99',2);


select c.id as customer_id, o.ship_name, o.id as order_id, o.shipping_fees
select o.id as order_id, o.ship_name, o.shipping_fees, c.id as customer_id
from customer c, orders o
where c.id = o.customer_id
and customer_id = 1;
