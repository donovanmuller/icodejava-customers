package io.switchbit.jugbank.domain.customers;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Customer {

    @AggregateIdentifier
    private String id;

    private String name;
    private String surname;
    private String email;

    Customer() {
        // for axon
    }

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        if (!StringUtils.isNoneEmpty(command.getName(), command.getSurname(),
                command.getEmail())) {
            throw new InvalidCustomerCommandException(command);
        }

        apply(new CustomerCreatedEvent(command.getId(), command.getName(),
                command.getSurname(), command.getEmail()));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.id = event.getCustomerId();
        this.name = event.getName();
        this.surname = event.getSurname();
        this.email = event.getEmail();
    }
}
