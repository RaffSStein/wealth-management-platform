package raff.stein.customer.service.update.visitor.impl;

import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.model.bo.customer.CustomerGoals;
import raff.stein.customer.service.update.visitor.CustomerVisitor;
import raff.stein.customer.service.update.visitor.TypeKey;

import java.util.List;

@Component
public class CustomerGoalsVisitor implements CustomerVisitor<List<CustomerGoals>> {

    @Override
    public Customer visit(Customer customer, List<CustomerGoals> payload) {
        //TODO
        return customer;
    }

    @Override
    public TypeKey getPayloadType() {
        return TypeKey.of(List.class, CustomerGoals.class);
    }
}
