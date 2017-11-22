package com.avides.spring.amqp.utils;

public abstract class QueueArguments
{
    // argument-keys:
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    public static final String X_QUEUE_MODE = "x-queue-mode";

    // argument-values:
    public static final String LAZY = "lazy";

    private QueueArguments()
    {
        // private constructor to hide the public one
    }
}