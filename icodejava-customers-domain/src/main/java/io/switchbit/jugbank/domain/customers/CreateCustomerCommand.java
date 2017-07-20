package io.switchbit.jugbank.domain.customers;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Email;

import lombok.Value;

@Value
public class CreateCustomerCommand {

	@TargetAggregateIdentifier
	private String id;

	private String name;
	private String surname;

	@Email
	private String email;
}
