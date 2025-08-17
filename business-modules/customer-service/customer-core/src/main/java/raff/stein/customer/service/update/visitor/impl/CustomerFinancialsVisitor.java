package raff.stein.customer.service.update.visitor.impl;

import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.model.bo.customer.CustomerFinancials;
import raff.stein.customer.service.update.visitor.CustomerVisitor;
import raff.stein.customer.service.update.visitor.TypeKey;

import java.util.List;

@Component
public class CustomerFinancialsVisitor implements CustomerVisitor<List<CustomerFinancials>> {

    @Override
    public Customer visit(Customer customer, List<CustomerFinancials> payload) {
        // convert, manipulate and save info here
        customer.setCustomerFinancials(payload);
        return customer;
    }

    @Override
    public TypeKey getPayloadType() {
        return TypeKey.of(List.class, CustomerFinancials.class);
    }
}
