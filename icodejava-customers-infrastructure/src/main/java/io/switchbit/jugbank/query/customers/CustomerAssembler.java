package io.switchbit.jugbank.query.customers;

import io.switchbit.jugbank.command.infrastructure.customers.web.rest.CustomerCommandController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssembler
        extends ResourceAssemblerSupport<CustomerEntity, CustomerResource> {

    public CustomerAssembler() {
        super(CustomerCommandController.class, CustomerResource.class);
    }

    @Override
    public CustomerResource toResource(CustomerEntity entity) {
        CustomerResource resource = createResourceWithId(entity.getCustomerId(), entity);
        resource.setCustomerId(entity.getCustomerId());
        resource.setName(entity.getName());
        resource.setSurname(entity.getSurname());
        return resource;
    }
}
