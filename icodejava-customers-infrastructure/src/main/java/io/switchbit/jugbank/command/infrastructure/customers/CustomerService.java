package io.switchbit.jugbank.command.infrastructure.customers;

import io.switchbit.jugbank.domain.customers.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    @EventHandler
    public void handle(CustomerCreatedEvent event) {
        log.info("Received a Customer Created Event: '{}'", event);
    }
}
