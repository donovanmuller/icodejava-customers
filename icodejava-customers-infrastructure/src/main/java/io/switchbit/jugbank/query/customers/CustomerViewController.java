package io.switchbit.jugbank.query.customers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/customers")
@ExposesResourceFor(CustomerResource.class)
public class CustomerViewController {

    private CustomerEntityRepository customerRepository;
    private CustomerAssembler assembler;
    private CustomerEmailRepository customerEmailRepository;

    public CustomerViewController(CustomerEntityRepository customerRepository,
                                  CustomerAssembler assembler,
                                  CustomerEmailRepository customerEmailRepository) {
        this.customerRepository = customerRepository;
        this.assembler = assembler;
        this.customerEmailRepository = customerEmailRepository;
    }

    @GetMapping
    public Resources<CustomerResource> list() {
        List<CustomerResource> customers = customerRepository.findAll().stream()
                .map(assembler::toResource).collect(Collectors.toList());
        return new Resources<>(customers,
                linkTo(methodOn(CustomerViewController.class).list()).withRel("self"));
    }

    @GetMapping("/{customerId}")
    public CustomerResource get(@PathVariable String customerId) {
        return assembler.toResource(customerRepository.findOne(customerId));
    }

    @GetMapping("/emails")
    public CustomerEmail getByEmail(@RequestParam String email) {
        CustomerEmail byEmail = customerEmailRepository.findOne(email);
        return byEmail;
    }
}
