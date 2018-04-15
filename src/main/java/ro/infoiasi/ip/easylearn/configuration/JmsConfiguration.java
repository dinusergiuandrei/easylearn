package ro.infoiasi.ip.easylearn.configuration;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

// Source: https://spring.io/guides/gs/messaging-jms/
// http://www.baeldung.com/spring-jms
// https://docs.oracle.com/javaee/6/tutorial/doc/bncdq.html

@EnableJms
public class JmsConfiguration {

    @Bean
    public JmsListenerContainerFactory <?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                       DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ApplicationContext applicationContext) {
        return applicationContext.getBean(JmsTemplate.class);
    }
}
