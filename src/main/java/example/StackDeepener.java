package example;

import java.util.concurrent.Callable;

/**
 * This adds unnecessary stack eleents by
 * recursively calling runAtStackLevel
 */
public class StackDeepener {

    public <T,U> void runAtStackLevel(final Callable<Void> callable, final int depth)
            throws Exception {

        if(depth > 0) {
            runAtStackLevel(callable, depth - 1);
        } else {
            callable.call();
        }

    }
}
