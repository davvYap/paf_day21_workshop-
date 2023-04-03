package sg.edu.nus.iss.workshop21.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    private int id;
    private String shipName;
    private Double shippingFees;
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Double getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(Double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", shipName=" + shipName + ", shippingFees=" + shippingFees + ", customer="
                + customer + "]";
    }

    public static Order createFromResults(SqlRowSet result) {
        Order order = new Order();
        Customer c = new Customer();
        c.setId(result.getInt("customer_id"));
        order.setId(result.getInt("order_id"));
        order.setShipName(result.getString("ship_name"));
        order.setShippingFees(result.getDouble("shipping_fees"));
        order.setCustomer(c);
        return order;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("orderId", this.getId())
                .add("shipName", this.getShipName())
                .add("shippingFees", this.getShippingFees())
                .add("customerId", this.getCustomer().getId())
                .build();
    }

}
