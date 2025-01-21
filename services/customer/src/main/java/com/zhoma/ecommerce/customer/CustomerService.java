package com.zhoma.ecommerce.customer;


import com.zhoma.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = this.repository.save(mapper.toCustomer(request));
        return customer.getId();
    }


    public void updateCustomer(CustomerRequest request) {
        var updatedCustomer = repository.findById(request.id()).orElseThrow(()
                -> new CustomerNotFoundException(
                String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())
        ));
        mergerCustomer(updatedCustomer, request);
        repository.save(updatedCustomer);


    }



    public List<CustomerResponse> findAllCustomers() {
        return  this.repository.findAll()
                .stream()
                .map(this.mapper::fromCustomer)
                .collect(Collectors.toList());
    }


    public Boolean existsById(String customerId) {
        return this.repository.findById(customerId)
                .isPresent();

       }

    public CustomerResponse findById(String customerId) {
        return this.repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", customerId)));

    }

    public void deleteCustomer(String customerId) {
        this.repository.deleteById(customerId);
    }

    private void mergerCustomer(Customer updatedCustomer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            updatedCustomer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            updatedCustomer.setEmail(request.email());
        }
        if (request.address() != null) {
            updatedCustomer.setAddress(request.address());
        }

    }
}
