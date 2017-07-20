package io.switchbit.jugbank.command.infrastructure.customers.web.rest;

import io.switchbit.jugbank.domain.customers.CreateCustomerCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/customers")
@ExposesResourceFor(CustomerResource.class)
public class CustomerCommandController {

    private CommandGateway commandGateway;

    public CustomerCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<CustomerResource> create(
            @RequestBody @Valid CustomerResource customer) {
        String result = commandGateway.sendAndWait(
                new CreateCustomerCommand(customer.getCustomerId(), customer.getName(),
                        customer.getSurname(), customer.getEmail()),
                1, TimeUnit.SECONDS);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result).toUri();

        return ResponseEntity.created(location).build();
    }
}
