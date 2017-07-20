package io.switchbit.jugbank.query.customers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
public class CustomerEntity implements Persistable<String> {

    private String customerId;
    private String name;
    private String surname;

    private transient boolean persisted;

    public CustomerEntity(String customerId, String name, String surname) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getId() {
        return customerId;
    }

    @Override
    public boolean isNew() {
        return !persisted;
    }
}
