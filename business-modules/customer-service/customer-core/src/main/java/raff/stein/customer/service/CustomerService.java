package raff.stein.customer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.customer.model.Customer;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.customer.model.entity.customer.mapper.CustomerToCustomerEntityMapper;
import raff.stein.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final OnboardingService onboardingService;

    private static final CustomerToCustomerEntityMapper customerToCustomerEntityMapper = CustomerToCustomerEntityMapper.MAPPER;

    @Transactional
    public Customer initCustomer(Customer customer) {
        log.debug("Initializing customer: [{}]", customer);
        // Save the customer entity to the database
        CustomerEntity savedCustomerEntity = customerRepository.save(customerToCustomerEntityMapper.toCustomerEntity(customer));
        // Create a new onboarding entity for the customer
        onboardingService.startOnboardingProcess(savedCustomerEntity);
        return customerToCustomerEntityMapper.toCustomer(savedCustomerEntity);
    }
}
