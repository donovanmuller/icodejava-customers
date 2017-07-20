package io.switchbit.jugbank.domain.customers;

public class InvalidCustomerCommandException extends RuntimeException {

    public <T> InvalidCustomerCommandException(T command) {
        super(String.format(
                "Customer command contained invalid/missing information: '%s'", command));
    }
}
