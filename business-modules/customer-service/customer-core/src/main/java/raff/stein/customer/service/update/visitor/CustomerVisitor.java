package raff.stein.customer.service.update.visitor;

import raff.stein.customer.model.bo.customer.Customer;

public interface CustomerVisitor <T> {

    Customer visit(Customer customer, T payload);

    TypeKey getPayloadType();
}
