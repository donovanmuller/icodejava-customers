package io.switchbit.jugbank.query.configuration;

import org.axonframework.config.EventHandlingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class AxonConfiguration {

	// TODO uncomment to replay events, populating the CustomersCache
//	@Autowired
	public void configure(EventHandlingConfiguration configuration) {
		configuration.registerTrackingProcessor("CustomersCache");
	}
}
