package am.complaints;

import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${axon.amqp.exchange}")
    private String address;

    @Bean
    public Serializer serializer() {
        return new JacksonSerializer();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange(address).build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(address).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin amqpAdmin) {
        amqpAdmin.declareExchange(exchange());
        amqpAdmin.declareQueue(queue());
        amqpAdmin.declareBinding(binding());
    }
}
