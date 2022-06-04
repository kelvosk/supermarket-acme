package br.com.customer.service;

import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);
}
