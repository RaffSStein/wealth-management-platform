package raff.stein.customer.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.CustomerDTO;
import raff.stein.customer.model.Customer;

@Mapper(config = CustomerControllerCommonMapperConfig.class)
public interface CustomerDTOToCustomerMapper {

    CustomerDTOToCustomerMapper MAPPER = Mappers.getMapper(CustomerDTOToCustomerMapper.class);

    CustomerDTO toCustomerDTO(Customer customer);

    Customer toCustomer(CustomerDTO customerDTO);
}
