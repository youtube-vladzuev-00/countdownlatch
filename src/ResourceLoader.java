import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class ResourceLoader extends ResourceTask {
    private final long secondDuration;

    public ResourceLoader(final long id, final CountDownLatch latch, final long secondDuration) {
        super(id, latch);
        this.secondDuration = secondDuration;
    }


    @Override
    protected void run(final CountDownLatch latch) {
        try {
            out.printf("%s is loading some resource\n", this);
            SECONDS.sleep(this.secondDuration);
            out.printf("Some resource was loaded by %s\n", this);
            latch.countDown();
        } catch (final InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }
}
