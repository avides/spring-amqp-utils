package com.avides.spring.amqp.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;

public class AmqpUtilsTest
{
    private Object amqpAdmin1 = "amqpAdmin1";
    private Object amqpAdmin2 = "amqpAdmin2";
    private Object amqpAdmin3 = "amqpAdmin3";

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableQueueWithDlx()
    {
        Queue queue = AmqpUtils.buildDurableQueueWithDlx("anyQueueName", amqpAdmin1);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableQueueWithDlxAndMoreAdmins()
    {
        Queue queue = AmqpUtils.buildDurableQueueWithDlx("anyQueueName", amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableQueueWithDlxArguments()
    {
        Queue queue = AmqpUtils.buildDurableQueueWithDlxArguments("anyQueueName", amqpAdmin1);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableQueueWithDlxArgumentsAndMoreAdmins()
    {
        Queue queue = AmqpUtils.buildDurableQueueWithDlxArguments("anyQueueName", amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableDlxQueueForQueue()
    {
        Queue queue = QueueBuilder.durable("anyQueueName").build();
        queue.setAdminsThatShouldDeclare(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        Queue dlxQueue = AmqpUtils.buildDurableDlxQueueFor(queue);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isTrue();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableDlxQueueForQueueName()
    {
        Queue dlxQueue = AmqpUtils.buildDurableDlxQueueFor("anyQueueName", amqpAdmin1);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isTrue();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDurableDlxQueueForQueueNameWithMoreAdmins()
    {
        Queue dlxQueue = AmqpUtils.buildDurableDlxQueueFor("anyQueueName", amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isTrue();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableQueueWithDlx()
    {
        Queue queue = AmqpUtils.buildNonDurableQueueWithDlx("anyQueueName", amqpAdmin1);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isFalse();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableQueueWithDlxAndMoreAdmins()
    {
        Queue queue = AmqpUtils.buildNonDurableQueueWithDlx("anyQueueName", amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isFalse();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableQueueWithDlxArguments()
    {
        Queue queue = AmqpUtils.buildNonDurableQueueWithDlxArguments("anyQueueName", amqpAdmin1);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isFalse();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableQueueWithDlxArgumentsAndMoreAdmins()
    {
        Queue queue = AmqpUtils.buildNonDurableQueueWithDlxArguments("anyQueueName", amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getName()).isEqualTo("anyQueueName");
        assertThat(queue.isDurable()).isFalse();
        assertThat(queue.isAutoDelete()).isFalse();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) queue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(queue.getArguments()).hasSize(2).containsEntry(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .containsEntry(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, "anyQueueName.dlx");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableDlxQueueForQueue()
    {
        Queue queue = QueueBuilder.durable("anyQueueName").build();
        queue.setAdminsThatShouldDeclare(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        Queue dlxQueue = AmqpUtils.buildNonDurableDlxQueueFor(queue);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isFalse();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableDlxQueueForQueueName()
    {
        Queue dlxQueue = AmqpUtils.buildNonDurableDlxQueueFor("anyQueueName", amqpAdmin1);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isFalse();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildNonDurableDlxQueueForQueueNameWithMoreAdmins()
    {
        Queue dlxQueue = AmqpUtils.buildNonDurableDlxQueueFor("anyQueueName", amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isFalse();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDlxQueueForDurableQueue()
    {
        Queue queue = QueueBuilder.durable("anyQueueName").build();
        queue.setAdminsThatShouldDeclare(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        Queue dlxQueue = AmqpUtils.buildDlxQueueFor(queue);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isTrue();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildDlxQueueForNonDurableQueue()
    {
        Queue queue = QueueBuilder.nonDurable("anyQueueName").build();
        queue.setAdminsThatShouldDeclare(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        Queue dlxQueue = AmqpUtils.buildDlxQueueFor(queue);
        assertThat(dlxQueue.getName()).isEqualTo("anyQueueName.dlx");
        assertThat(dlxQueue.isDurable()).isFalse();
        assertThat(dlxQueue.isAutoDelete()).isFalse();
        assertThat(dlxQueue.isExclusive()).isFalse();
        assertThat(dlxQueue.isIgnoreDeclarationExceptions()).isFalse();
        assertThat((Collection<Object>) dlxQueue.getDeclaringAdmins()).containsOnly(amqpAdmin1, amqpAdmin2, amqpAdmin3);
        assertThat(dlxQueue.getArguments()).hasSize(1).containsEntry(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY);
    }
}