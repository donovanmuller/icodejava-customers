package io.switchbit.jugbank.query.customers;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@RedisHash("customerEmails")
public class CustomerEmail {

	@Id
	private String email;
	private String customerId;
}
