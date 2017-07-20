package io.switchbit.jugbank.domain.customers;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private FixtureConfiguration<Customer> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(Customer.class);
    }

    @Test
    public void create_customer_successfully() {
        fixture.givenNoPriorActivity()
                .when(new CreateCustomerCommand("1",
                        "Donovan",
                        "Muller",
                        "donovan.muller@gmail.com"))
                .expectSuccessfulHandlerExecution().expectEvents(
                new CustomerCreatedEvent(
                        "1",
                        "Donovan",
                        "Muller",
                        "donovan.muller@gmail.com"));
    }
}
