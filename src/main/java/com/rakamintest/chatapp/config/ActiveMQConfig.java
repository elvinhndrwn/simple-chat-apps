package com.rakamintest.chatapp.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class ActiveMQConfig {
    @Bean
    public ActiveMQConnectionFactory listenerActiveMQConnectionFactory() {
        var activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setTrustAllPackages(true);
        activeMQConnectionFactory.setBrokerURL("http://127.0.0.1:616161");

        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(listenerActiveMQConnectionFactory());
        factory.setConcurrency("10-100");

        return factory;
    }

    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("http://127.0.0.1:616161");
        activeMQConnectionFactory.setSendTimeout(1000);
        activeMQConnectionFactory.setMaxThreadPoolSize(5);
        activeMQConnectionFactory.setTrustAllPackages(true);

        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(senderActiveMQConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(cachingConnectionFactory());
    }
}
