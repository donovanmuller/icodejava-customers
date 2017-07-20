package io.switchbit.jugbank.query.customers;

import io.switchbit.jugbank.domain.customers.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerView {

    private CustomerEntityRepository customerEntityRepository;

    public CustomerView(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("Handling event: {}", event);

        customerEntityRepository.save(new CustomerEntity(event.getCustomerId(),
                event.getName(), event.getSurname()));
    }

}
