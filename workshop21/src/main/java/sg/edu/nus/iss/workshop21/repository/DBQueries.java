package sg.edu.nus.iss.workshop21.repository;

public class DBQueries {

    public static final String SELECT_ALL_CUSTOMER = "select id, company, first_name, last_name from customer limit ? offset ?;";

    public static final String SELECT_CUSTOMER_BY_ID = "select id, company, first_name, last_name from customer where id = ?;";

    public static final String SELECT_ORDER_BY_CUSTOMER_ID = "select c.id as customer_id, o.ship_name, o.id as order_id, o.shipping_fees from customer c, orders o where c.id = o.customer_id and customer_id = ?;";
}
