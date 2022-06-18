package br.com.customer.service;

import br.com.clients.fraud.request.NotificationPayload;
import br.com.clients.fraud.response.InternalResponseFraud;
import br.com.clients.fraud.service.ClientFraudService;
import br.com.customer.config.ConvertUtils;
import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;
import br.com.customer.repository.CustomerRepository;
import br.com.rabbitmq.RabbitMQMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements  CustomerService {

    private final CustomerRepository customerRepository;
    private final ConvertUtils convertUtils;
    private final ClientFraudService clientFraudService;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public CustomerServiceImpl(CustomerRepository customerRepository, ConvertUtils convertUtils, ClientFraudService clientFraudService, RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.convertUtils = convertUtils;
        this.clientFraudService = clientFraudService;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

    @Override
    public String createCustomer(CustomerRequest customerRequest) {

        var internalResponseFraud = this.clientFraudService.isFraud(customerRequest.getCpf());

        if (internalResponseFraud != null && internalResponseFraud.getFraud()) {

            log.info(String.format("This document %s to this customer is one fraud ", customerRequest.getCpf()));

            // send message to queue
            var notificationPayload = NotificationPayload
                    .builder()
                    .customer_email(customerRequest.getEmail())
                    .sender(customerRequest.getCustomer_name())
                    .customer_cpf(customerRequest.getCpf())
                    .message("Client:" + internalResponseFraud.getCustomerName() + "with CPF:" + internalResponseFraud.getCpf() + "is a fraud")
                    .build();

            this.rabbitMQMessageProducer.publish(
                    notificationPayload,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );

            return ("The customer: " + internalResponseFraud.getCustomerName() + " is a fraud, cpf :" + internalResponseFraud.getCpf());

        } else {
            log.info("Calling the method to create customer {}", customerRequest);
            var customerEntity =
                    (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);

            var entity = this.customerRepository.save(customerEntity);
            log.info(String.format("calling fraud service to customerId {}", entity.getCustomerCpf()));

            this.convertUtils.convertEntityToResponse(entity, CustomerResponse.class);

            return "Customer created with success";
        }
    }
}
