package raff.stein.customer.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.CustomerApi;
import org.openapitools.model.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.customer.controller.mapper.CustomerDTOToCustomerMapper;
import raff.stein.customer.model.bo.customer.Customer;
import raff.stein.customer.service.CustomerService;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;
    private static final CustomerDTOToCustomerMapper customerDTOToCustomerMapper = CustomerDTOToCustomerMapper.MAPPER;

    @Override
    public ResponseEntity<CustomerDTO> initCustomer(CustomerDTO customerDTO) {
        Customer customerInput = customerDTOToCustomerMapper.toCustomer(customerDTO);
        Customer createdCustomer = customerService.initCustomer(customerInput);
        CustomerDTO responseCustomerDTO = customerDTOToCustomerMapper.toCustomerDTO(createdCustomer);
        return ResponseEntity.ok(responseCustomerDTO);
    }
}
