package raff.stein.customer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.customer.model.Customer;
import raff.stein.customer.model.entity.customer.CustomerEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingEntity;
import raff.stein.customer.model.entity.customer.CustomerOnboardingStepEntity;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStatus;
import raff.stein.customer.model.entity.customer.enumeration.OnboardingStep;
import raff.stein.customer.model.entity.customer.mapper.CustomerToCustomerEntityMapper;
import raff.stein.customer.repository.CustomerOnboardingRepository;
import raff.stein.customer.repository.CustomerOnboardingStepRepository;
import raff.stein.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOnboardingRepository customerOnboardingRepository;
    private final CustomerOnboardingStepRepository customerOnboardingStepRepository;

    private static final CustomerToCustomerEntityMapper customerToCustomerEntityMapper = CustomerToCustomerEntityMapper.MAPPER;

    @Transactional
    public Customer initCustomer(Customer customer) {
        log.debug("Initializing customer: [{}]", customer);
        // Save the customer entity to the database
        CustomerEntity savedCustomerEntity = customerRepository.save(customerToCustomerEntityMapper.toCustomerEntity(customer));
        // Create a new onboarding entity for the customer
        final CustomerOnboardingEntity onboardingEntity = CustomerOnboardingEntity.builder()
                .customer(savedCustomerEntity)
                .onboardingStatus(OnboardingStatus.IN_PROGRESS)
                .reason("Onboarding process initiated")
                .isValid(true)
                .build();
        customerOnboardingRepository.save(onboardingEntity);
        // create the first step of the onboarding process
        final CustomerOnboardingStepEntity firstStep = CustomerOnboardingStepEntity.builder()
                .step(OnboardingStep.INIT)
                .status("DONE")
                .reason("Onboarding process initiated")
                .customerOnboarding(onboardingEntity)
                .build();
        customerOnboardingStepRepository.save(firstStep);
        return customerToCustomerEntityMapper.toCustomer(savedCustomerEntity);
    }
}
