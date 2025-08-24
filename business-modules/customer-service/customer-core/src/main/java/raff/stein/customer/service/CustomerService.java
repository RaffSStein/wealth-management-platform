package raff.stein.customer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.model.entity.customer.mapper.CustomerToCustomerEntityMapper;
import raff.stein.customer.repository.customer.CustomerRepository;
import raff.stein.customer.service.onboarding.OnboardingService;
import raff.stein.customer.service.onboarding.handler.OnboardingStepContext;
import raff.stein.customer.service.update.visitor.CustomerVisitorDispatcher;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final OnboardingService onboardingService;
    private final CustomerVisitorDispatcher customerVisitorDispatcher;

    private static final CustomerToCustomerEntityMapper customerToCustomerEntityMapper = CustomerToCustomerEntityMapper.MAPPER;

    @Transactional
    public Customer initCustomer(Customer customer) {
        log.debug("Initializing customer: [{}]", customer);
        // Save the customer entity to the database
        CustomerEntity savedCustomerEntity = customerRepository.save(customerToCustomerEntityMapper.toCustomerEntity(customer));
        // Initiate the onboarding process for the saved customer
        onboardingService.proceedToStep(
                OnboardingStep.INIT,
                OnboardingStepContext.builder()
                        .customerId(savedCustomerEntity.getId())
                        .build());
        return customerToCustomerEntityMapper.toCustomer(savedCustomerEntity);
    }

    @Transactional
    public Customer updateCustomer(UUID customerId, Object customerAttributesToUpdate) {
        log.debug("Updating fields [{}] for customer ID: [{}]", customerAttributesToUpdate, customerId);
        // Lookup the customer entity by ID
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));
        // Convert the customer entity to a business object
        Customer customer = customerToCustomerEntityMapper.toCustomer(customerEntity);
        // Update customer attributes via a visitor pattern
        return customerVisitorDispatcher.dispatchAndVisit(customer, customerAttributesToUpdate);
    }

    public void proceedToOnboardingStep(
            UUID customerId,
            OnboardingStep onboardingStep) {
        log.debug("Proceeding to onboarding step [{}] for customer ID: [{}]", onboardingStep, customerId);
        // Dispatch the step to the appropriate handler
        onboardingService.proceedToStep(
                onboardingStep,
                OnboardingStepContext.builder()
                        .customerId(customerId)
                        .build());
    }
}
