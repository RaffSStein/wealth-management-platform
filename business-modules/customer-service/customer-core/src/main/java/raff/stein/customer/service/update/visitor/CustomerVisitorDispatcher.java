package raff.stein.customer.service.update.visitor;

import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.customer.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerVisitorDispatcher {

    private final Map<TypeKey, CustomerVisitor<?>> visitorRegistry = new HashMap<>();

    /**
     * Constructs a CustomerVisitorDispatcher with a list of CustomerVisitor instances.
     * Each visitor is registered by its payload type.
     *
     * @param visitors List of CustomerVisitor instances to register.
     * @throws IllegalStateException if there are duplicate visitors for the same payload type.
     */
    public CustomerVisitorDispatcher(List<CustomerVisitor<?>> visitors) {
        for (CustomerVisitor<?> visitor : visitors) {
            TypeKey key = visitor.getPayloadType();
            if (visitorRegistry.containsKey(key)) {
                throw new IllegalStateException("Duplicate visitor for type: " + key);
            }
            visitorRegistry.put(key, visitor);
        }
    }

    /**
     * Dispatches the payload to the appropriate CustomerVisitor based on the payload type.
     *
     * @param customer The customer to be updated.
     * @param payload  The payload containing the data to update the customer.
     * @throws IllegalArgumentException if the payload is null or no visitor is registered for the payload type.
     */
    public Customer dispatchAndVisit(Customer customer, Object payload) {
        TypeKey key = resolveTypeKey(payload);

        @SuppressWarnings("unchecked")
        CustomerVisitor<Object> visitor = (CustomerVisitor<Object>) visitorRegistry.get(key);

        if (visitor == null) {
            throw new IllegalArgumentException("No visitor registered for payload type: " + key);
        }

        return visitor.visit(customer, payload);
    }

    private TypeKey resolveTypeKey(Object payload) {
        // handle list payloads
        if (payload instanceof List<?> list && !list.isEmpty()) {
            Class<?> parameterType = list.get(0).getClass();
            return TypeKey.of(List.class, parameterType);
        }
        // normal object payloads
        return TypeKey.of(payload.getClass(), null);
    }
}

