package io.switchbit.jugbank.command.infrastructure.configuration;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.switchbit.jugbank.domain.customers.Customer;

@Configuration
public class DomainConfiguration {

	@Bean
	public Repository<Customer> customerRepository(EventStore eventStore) {
		return new EventSourcingRepository<>(
				new GenericAggregateFactory<>(Customer.class), eventStore);
	}
}
