package io.switchbit.jugbank.command.infrastructure.configuration;

import javax.sql.DataSource;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.PostgresEventTableFactory;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.eventhandling.scheduling.java.SimpleEventSchedulerFactoryBean;
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class AxonCommandConfiguration {

    @Autowired
    public void configureCommandBus(AxonConfiguration axonConfiguration) {
        ((SimpleCommandBus) axonConfiguration.getComponent(CommandBus.class))
                .registerDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Bean
    public EventStorageEngine eventStorageEngine(DataSource dataSource,
            PlatformTransactionManager transactionManager) {
        JdbcEventStorageEngine eventStorageEngine = new JdbcEventStorageEngine(
                new SpringDataSourceConnectionProvider(dataSource),
                new SpringTransactionManager(transactionManager)) {

        };
        eventStorageEngine.createSchema(PostgresEventTableFactory.INSTANCE);
        return eventStorageEngine;
    }

    @Bean
    public SimpleEventSchedulerFactoryBean eventScheduler(EventBus eventBus,
            PlatformTransactionManager transactionManager) {
        SimpleEventSchedulerFactoryBean eventSchedulerFactory = new SimpleEventSchedulerFactoryBean();
        eventSchedulerFactory.setEventBus(eventBus);
        eventSchedulerFactory.setTransactionManager(transactionManager);
        return eventSchedulerFactory;
    }
}
