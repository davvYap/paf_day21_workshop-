package sg.edu.nus.iss.workshop21.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Order;

import static sg.edu.nus.iss.workshop21.repository.DBQueries.*;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomer(int limit, int offset) {
        List<Customer> customers = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT_ALL_CUSTOMER, limit, offset);

        while (results.next()) {
            customers.add(Customer.createFromResults(results));
        }
        return customers;
    }

    public SqlRowSet getCustomerById(int id) {
        SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT_CUSTOMER_BY_ID, id);
        return results;
    }

    public List<Order> getCustomerOrder(int id) {
        SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT_ORDER_BY_CUSTOMER_ID, id);
        List<Order> orders = new ArrayList<>();

        while (results.next()) {
            orders.add(Order.createFromResults(results));
        }
        return orders;
    }
}
