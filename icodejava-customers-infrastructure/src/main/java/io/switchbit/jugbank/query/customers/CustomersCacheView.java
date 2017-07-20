package io.switchbit.jugbank.query.customers;

import io.switchbit.jugbank.domain.customers.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ProcessingGroup("CustomersCache")
public class CustomersCacheView {

    private CustomerEmailRepository customerEmailRepository;

    public CustomersCacheView(CustomerEmailRepository customerEmailRepository) {
        this.customerEmailRepository = customerEmailRepository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("Caching customer created event for email: '{}'", event);

        customerEmailRepository
                .save(new CustomerEmail(event.getEmail(), event.getCustomerId()));
    }
}
