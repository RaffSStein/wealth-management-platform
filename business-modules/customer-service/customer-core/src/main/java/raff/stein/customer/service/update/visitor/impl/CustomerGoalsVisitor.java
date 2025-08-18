package raff.stein.customer.service.update.visitor.impl;

import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.model.bo.customer.CustomerGoals;
import raff.stein.customer.service.update.visitor.CustomerVisitor;
import raff.stein.customer.service.update.visitor.TypeKey;

import java.util.Collection;

@Component
public class CustomerGoalsVisitor implements CustomerVisitor<Collection<CustomerGoals>> {

    @Override
    public Customer visit(Customer customer, Collection<CustomerGoals> payload) {
        //TODO
        return customer;
    }

    @Override
    public TypeKey getPayloadType() {
        return TypeKey.of(Collection.class, CustomerGoals.class, null);
    }
}
