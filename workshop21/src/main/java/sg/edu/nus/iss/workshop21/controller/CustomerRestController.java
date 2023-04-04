package sg.edu.nus.iss.workshop21.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Order;
import sg.edu.nus.iss.workshop21.repository.CustomerRepository;

@RestController
@RequestMapping(path = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<String> getAllCustomers(@RequestParam(required = false, defaultValue = "5") String limit,
            @RequestParam(required = false, defaultValue = "0") String offset) {
        // if (Objects.isNull(limit))
        // limit = "5";
        // if (Objects.isNull(offset))
        // limit = "0";
        List<Customer> customers = customerRepository.getAllCustomer(Integer.parseInt(limit), Integer.parseInt(offset));
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Customer customer : customers) {
            jsonArrayBuilder.add(customer.toJSON());
        }

        JsonArray jsArr = jsonArrayBuilder.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsArr.toString());

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable String id) {
        SqlRowSet result = customerRepository.getCustomerById(Integer.parseInt(id));
        Optional<Customer> customer = Optional.empty();
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                            .add("error message", "Cannot find customer with id %s".formatted(id))
                            .build()
                            .toString());
        }
        if (result.first()) {
            customer = Optional.of(Customer.createFromResults(result));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer.get().toJSON().toString());
    }

    @GetMapping(path = "{id}/orders")
    public ResponseEntity<String> getCustomerOrdersById(@PathVariable String id) {

        if (getCustomerById(id).getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                            .add("error message", "Cannot find customer and orders with id %s".formatted(id))
                            .build()
                            .toString());
        }

        List<Order> orders = customerRepository.getCustomerOrder(Integer.parseInt(id));
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Order order : orders) {
            jsonArrayBuilder.add(order.toJSON());
        }

        JsonArray jsArr = jsonArrayBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsArr.toString());

    }

}
