package io.switchbit.jugbank.query.customers;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class CustomerResource extends ResourceSupport {

	private String customerId;
	private String name;
	private String surname;
}
