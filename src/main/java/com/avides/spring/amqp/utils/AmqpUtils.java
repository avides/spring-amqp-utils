package com.avides.spring.amqp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;

/**
 * Utility-class for simple amqp-funcionality. So far it is useful for creating
 * DLX-{@link Queue}s
 *
 * @author Martin Schumacher
 * @since 1.0.0.RELEASE
 */
public abstract class AmqpUtils
{
    private static final String DLX_NAME_SUFFIX = ".dlx";

    private AmqpUtils()
    {
        // private constructor to hide the public one
    }

    /**
     * Builds a durable {@link Queue} with the given queueName and necessary
     * DLX-arguments and a belonging durable DLX-{@link Queue} for it
     *
     * @param queueName
     *            the name for the {@link Queue} and the DLX-{@link Queue} (the
     *            latter one is suffixed with '.dlx')
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} that has a belonging DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableQueueWithDlx(String queueName, Object adminThatShouldDeclare)
    {
        return buildDurableQueueWithDlx(queueName, adminThatShouldDeclare, new Object[0]);
    }

    /**
     * Builds a durable {@link Queue} with the given queueName and necessary
     * DLX-arguments and a belonging durable DLX-{@link Queue} for it
     *
     * @param queueName
     *            the name for the {@link Queue} and the DLX-{@link Queue} (the
     *            latter one is suffixed with '.dlx')
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @param furtherAdminsThatShouldDeclare
     *            further {@link AmqpAdmin}s that should declare the
     *            {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} that has a belonging DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableQueueWithDlx(String queueName, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        return buildQueueWithDlx(queueName, true, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
    }

    /**
     * Builds a durable {@link Queue} with the given name that has necessary
     * arguments to use a DLX-{@link Queue}, which must be created separately
     *
     * @param queueName
     *            the name for the {@link Queue}
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} with necessary DLX-arguments
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableQueueWithDlxArguments(String queueName, Object adminThatShouldDeclare)
    {
        return buildDurableQueueWithDlxArguments(queueName, adminThatShouldDeclare, new Object[0]);
    }

    /**
     * Builds a durable {@link Queue} with the given name that has necessary
     * arguments to use a DLX-{@link Queue}, which must be created separately
     *
     * @param queueName
     *            the name for the {@link Queue}
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @param furtherAdminsThatShouldDeclare
     *            further {@link AmqpAdmin}s that should declare the
     *            {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} with necessary DLX-arguments
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableQueueWithDlxArguments(String queueName, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        return buildQueueWithDlxArguments(queueName, true, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
    }

    /**
     * Builds a durable DLX-{@link Queue} for the given {@link Queue}. The name
     * of the created DLX-{@link Queue} is the name of the given {@link Queue}
     * suffixed with '.dlx'
     *
     * @param queue
     *            the {@link Queue} to build a DLX-{@link Queue} for
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableDlxQueueFor(Queue queue)
    {
        return buildDurableDlxQueueFor(queue.getName(), null, queue.getDeclaringAdmins().toArray());
    }

    /**
     * Builds a durable DLX-{@link Queue} for the given queueName. The name of
     * the created DLX-{@link Queue} is the given queueName suffixed with '.dlx'
     *
     * @param queueName
     *            the name of the {@link Queue} to build a DLX-{@link Queue} for
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the DLX-{@link Queue}
     *            (see {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableDlxQueueFor(String queueName, Object adminThatShouldDeclare)
    {
        return buildDurableDlxQueueFor(queueName, adminThatShouldDeclare, new Object[0]);
    }

    /**
     * Builds a durable DLX-{@link Queue} for the given queueName. The name of
     * the created DLX-{@link Queue} is the given queueName suffixed with '.dlx'
     *
     * @param queueName
     *            the name of the {@link Queue} to build a DLX-{@link Queue} for
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the DLX-{@link Queue}
     *            (see {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @param furtherAdminsThatShouldDeclare
     *            further {@link AmqpAdmin}s that should declare the
     *            DLX-{@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDurableDlxQueueFor(String queueName, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        return buildDlxQueueFor(queueName, true, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
    }

    /**
     * Builds a non-durable {@link Queue} with the given queueName and necessary
     * DLX-arguments and a belonging non-durable DLX-{@link Queue} for it
     *
     * @param queueName
     *            the name for the {@link Queue} and the DLX-{@link Queue} (the
     *            latter one is suffixed with '.dlx')
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} that has a belonging DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableQueueWithDlx(String queueName, Object adminThatShouldDeclare)
    {
        return buildNonDurableQueueWithDlx(queueName, adminThatShouldDeclare, new Object[0]);
    }

    /**
     * Builds a non-durable {@link Queue} with the given queueName and necessary
     * DLX-arguments and a belonging non-durable DLX-{@link Queue} for it
     *
     * @param queueName
     *            the name for the {@link Queue} and the DLX-{@link Queue} (the
     *            latter one is suffixed with '.dlx')
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @param furtherAdminsThatShouldDeclare
     *            further {@link AmqpAdmin}s that should declare the
     *            {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} that has a belonging DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableQueueWithDlx(String queueName, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        return buildQueueWithDlx(queueName, false, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
    }

    /**
     * Builds a non-durable {@link Queue} with the given name that has necessary
     * arguments to use a DLX-{@link Queue}, which must be created separately
     *
     * @param queueName
     *            the name for the {@link Queue}
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} with necessary DLX-arguments
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableQueueWithDlxArguments(String queueName, Object adminThatShouldDeclare)
    {
        return buildNonDurableQueueWithDlxArguments(queueName, adminThatShouldDeclare, new Object[0]);
    }

    /**
     * Builds a non-durable {@link Queue} with the given name that has necessary
     * arguments to use a DLX-{@link Queue}, which must be created separately
     *
     * @param queueName
     *            the name for the {@link Queue}
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @param furtherAdminsThatShouldDeclare
     *            further {@link AmqpAdmin}s that should declare the
     *            {@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the {@link Queue} with necessary DLX-arguments
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableQueueWithDlxArguments(String queueName, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        return buildQueueWithDlxArguments(queueName, false, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
    }

    /**
     * Builds a non-durable DLX-{@link Queue} for the given {@link Queue}. The
     * name of the created DLX-{@link Queue} is the name of the given
     * {@link Queue} suffixed with '.dlx'
     *
     * @param queue
     *            the {@link Queue} to build a DLX-{@link Queue} for
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableDlxQueueFor(Queue queue)
    {
        return buildNonDurableDlxQueueFor(queue.getName(), null, queue.getDeclaringAdmins().toArray());
    }

    /**
     * Builds a non-durable DLX-{@link Queue} for the given queueName. The name
     * of the created DLX-{@link Queue} is the given queueName suffixed with
     * '.dlx'
     *
     * @param queueName
     *            the name of the {@link Queue} to build a DLX-{@link Queue} for
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the DLX-{@link Queue}
     *            (see {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableDlxQueueFor(String queueName, Object adminThatShouldDeclare)
    {
        return buildNonDurableDlxQueueFor(queueName, adminThatShouldDeclare, new Object[0]);
    }

    /**
     * Builds a non-durable DLX-{@link Queue} for the given queueName. The name
     * of the created DLX-{@link Queue} is the given queueName suffixed with
     * '.dlx'
     *
     * @param queueName
     *            the name of the {@link Queue} to build a DLX-{@link Queue} for
     * @param adminThatShouldDeclare
     *            {@link AmqpAdmin} that should declare the DLX-{@link Queue}
     *            (see {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @param furtherAdminsThatShouldDeclare
     *            further {@link AmqpAdmin}s that should declare the
     *            DLX-{@link Queue} (see
     *            {@link Queue#setAdminsThatShouldDeclare(Object...)})
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildNonDurableDlxQueueFor(String queueName, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        return buildDlxQueueFor(queueName, false, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
    }

    /**
     * Builds a DLX-{@link Queue} for the given {@link Queue}. If the given
     * {@link Queue} is durable, the DLX-{@link Queue} will also be durable, If
     * the given {@link Queue} is non-durable, the DLX-{@link Queue} will also
     * be non-durable. The name of the created DLX-{@link Queue} is the name of
     * the given {@link Queue} suffixed with '.dlx'
     *
     * @param queue
     *            the {@link Queue} to build a DLX-{@link Queue} for
     * @return the DLX-{@link Queue}
     *
     * @since 1.0.0.RELEASE
     */
    public static Queue buildDlxQueueFor(Queue queue)
    {
        return queue.isDurable() ? buildDurableDlxQueueFor(queue) : buildNonDurableDlxQueueFor(queue);
    }

    private static Queue buildQueueWithDlx(String queueName, boolean durable, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        Queue queue = buildQueueWithDlxArguments(queueName, durable, adminThatShouldDeclare, furtherAdminsThatShouldDeclare);
        buildDlxQueueFor(queue);
        return queue;
    }

    private static Queue buildQueueWithDlxArguments(String queueName, boolean durable, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        QueueBuilder queueBuilder = durable ? QueueBuilder.durable(queueName) : QueueBuilder.nonDurable(queueName);
        Queue queue = queueBuilder.withArgument(QueueArguments.X_DEAD_LETTER_EXCHANGE, "")
            .withArgument(QueueArguments.X_DEAD_LETTER_ROUTING_KEY, queueName + DLX_NAME_SUFFIX).build();
        queue.setAdminsThatShouldDeclare(merge(adminThatShouldDeclare, furtherAdminsThatShouldDeclare).toArray());
        return queue;
    }

    private static Queue buildDlxQueueFor(String queueName, boolean durable, Object adminThatShouldDeclare, Object... furtherAdminsThatShouldDeclare)
    {
        String name = queueName + DLX_NAME_SUFFIX;
        QueueBuilder queueBuilder = durable ? QueueBuilder.durable(name) : QueueBuilder.nonDurable(name);
        Queue dlxQueue = queueBuilder.withArgument(QueueArguments.X_QUEUE_MODE, QueueArguments.LAZY).build();
        dlxQueue.setAdminsThatShouldDeclare(merge(adminThatShouldDeclare, furtherAdminsThatShouldDeclare).toArray());
        return dlxQueue;
    }

    private static <T> List<T> merge(T value, T[] array)
    {
        List<T> list = new ArrayList<>(Arrays.asList(array));
        if (value != null)
        {
            list.add(value);
        }
        return list;
    }
}