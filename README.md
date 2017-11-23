spring-amqp-utils
===========

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.avides.spring/spring-amqp-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.avides.spring/spring-amqp-utils)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/221cdc226d2a4724947e929fa20bbc0b)](https://www.codacy.com/app/avides-builds/spring-amqp-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=avides/spring-amqp-utils&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/avides/spring-amqp-utils/badge.svg?branch=master)](https://coveralls.io/github/avides/spring-amqp-utils?branch=master)
[![Build Status](https://travis-ci.org/avides/spring-amqp-utils.svg?branch=master)](https://travis-ci.org/avides/spring-amqp-utils)

#### Maven
```xml
<dependency>
    <groupId>com.avides.spring</groupId>
    <artifactId>spring-amqp-utils</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```
#### Available methods
```java
AmpqUtils.buildDurableQueueWithDlx(String queueName, AmqpAdmin adminThatShouldDeclare)
AmpqUtils.buildDurableQueueWithDlx(String queueName, AmqpAdmin adminThatShouldDeclare, AmqpAdmin... furtherAdminsThatShouldDeclare)

AmpqUtils.buildDurableQueueWithDlxArguments(String queueName, AmqpAdmin adminThatShouldDeclare)
AmpqUtils.buildDurableQueueWithDlxArguments(String queueName, AmqpAdmin adminThatShouldDeclare, AmqpAdmin... furtherAdminsThatShouldDeclare)

AmpqUtils.buildDurableDlxQueueFor(Queue queue)
AmpqUtils.buildDurableDlxQueueFor(String queueName, AmqpAdmin adminThatShouldDeclare)
AmpqUtils.buildDurableDlxQueueFor(String queueName, AmqpAdmin adminThatShouldDeclare, AmqpAdmin... furtherAdminsThatShouldDeclare)

AmpqUtils.buildNonDurableQueueWithDlx(String queueName, AmqpAdmin adminThatShouldDeclare)
AmpqUtils.buildNonDurableQueueWithDlx(String queueName, AmqpAdmin adminThatShouldDeclare, AmqpAdmin... furtherAdminsThatShouldDeclare)

AmpqUtils.buildNonDurableQueueWithDlxArguments(String queueName, AmqpAdmin adminThatShouldDeclare)
AmpqUtils.buildNonDurableQueueWithDlxArguments(String queueName, AmqpAdmin adminThatShouldDeclare, AmqpAdmin... furtherAdminsThatShouldDeclare)

AmpqUtils.buildNonDurableDlxQueueFor(Queue queue)
AmpqUtils.buildNonDurableDlxQueueFor(String queueName, AmqpAdmin adminThatShouldDeclare)
AmpqUtils.buildNonDurableDlxQueueFor(String queueName, AmqpAdmin adminThatShouldDeclare, AmqpAdmin... furtherAdminsThatShouldDeclare)

AmqpUtils.buildDlxQueueFor(Queue queue)
```
#### Examples
##### Possibility 1
```java
@Configuration
@EnableRabbit
public class RabbitConfiguration
{
    @Bean
    public Exchange exchange()
    {
        return new TopicExchange("exchange.name");
    }

    @Bean
    public Queue queue(AmqpAdmin amqpAdmin)
    {
        return AmqpUtils.buildDurableQueueWithDlx("queue.name", amqpAdmin);
    }
    
    @Bean
    public Binding binding(AmqpAdmin amqpAdmin)
    {
        return bind(queue(amqpAdmin)).to(exchange()).with("routing.key").noargs();
    }
}
```

##### Possibility 2
If you want to have access to the DLX-Queue-Bean
```java
@Configuration
@EnableRabbit
public class RabbitConfiguration
{
    @Bean
    public Exchange exchange()
    {
        return new TopicExchange("exchange.name");
    }

    @Bean
    public Queue queue(AmqpAdmin amqpAdmin)
    {
        return AmqpUtils.buildDurableQueueWithDlxArguments("queue.name", amqpAdmin);
    }
    
    @Bean
    public Queue dlxQueue(AmqpAdmin amqpAdmin)
    {
        return AmqpUtils.buildDlxQueueFor(queue(amqpAdmin));
    }
    
    @Bean
    public Binding binding(AmqpAdmin amqpAdmin)
    {
        return bind(queue(amqpAdmin)).to(exchange()).with("routing.key").noargs();
    }
}
```

##### Possibility 3
The same as possibility 2, but referncing the Queue and the DLX-Queue via the queue-name
```java
@Configuration
@EnableRabbit
public class RabbitConfiguration
{
    @Bean
    public Exchange exchange()
    {
        return new TopicExchange("exchange.name");
    }

    @Bean
    public Queue queue(AmqpAdmin amqpAdmin)
    {
        return AmqpUtils.buildDurableQueueWithDlxArguments("queue.name", amqpAdmin);
    }
    
    @Bean
    public Queue dlxQueue(AmqpAdmin amqpAdmin)
    {
        return AmqpUtils.buildDurableDlxQueueFor("queue.name", amqpAdmin);
    }
    
    @Bean
    public Binding binding(AmqpAdmin amqpAdmin)
    {
        return bind(queue(amqpAdmin)).to(exchange()).with("routing.key").noargs();
    }
}
```

In all given examples, it is also possible to give more than one RabbitAdmin/AmqpAdmin that should declare the queues. Also all examples can be made with non-durable-queues (use the AmqpUtils.buildNonDurable...-methods)
