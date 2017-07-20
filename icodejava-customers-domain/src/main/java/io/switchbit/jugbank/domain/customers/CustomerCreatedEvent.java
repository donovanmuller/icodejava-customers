package io.switchbit.jugbank.domain.customers;

import lombok.Value;

@Value
public class CustomerCreatedEvent {

	private String customerId;
	private String name;
	private String surname;
	private String email;

}
