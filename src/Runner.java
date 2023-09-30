import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

import static java.util.stream.IntStream.range;

public final class Runner {
    public static void main(final String... args) {
        final int resourcesCount = 3;
        final CountDownLatch latch = new CountDownLatch(resourcesCount);

        final Thread[] loadingThreads = createResourceThreads(
                ResourceLoaderFactory::new,
                resourcesCount,
                latch
        );

        final int handlingThreadsCount = 4;
        final Thread[] handlingThreads = createResourceThreads(
                ResourceHandlerFactory::new,
                handlingThreadsCount,
                latch
        );

        ThreadUtil.startThreads(loadingThreads);
        ThreadUtil.startThreads(handlingThreads);
    }

    private static Thread[] createResourceThreads(final Supplier<ResourceTaskFactory> taskFactorySupplier,
                                                  final int threadsCount,
                                                  final CountDownLatch latch) {
        final ResourceTaskFactory taskFactory = taskFactorySupplier.get();
        return range(0, threadsCount)
                .mapToObj(i -> taskFactory.create(latch))
                .map(Thread::new)
                .toArray(Thread[]::new);
    }
}