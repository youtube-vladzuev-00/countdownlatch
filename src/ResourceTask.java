import java.util.concurrent.CountDownLatch;

public abstract class ResourceTask implements Runnable {
    private final long id;
    private final CountDownLatch latch;

    public ResourceTask(final long id, final CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public final void run() {
        this.run(this.latch);
    }

    protected abstract void run(final CountDownLatch latch);

    @Override
    public final String toString() {
        return this.getClass().getName() + "[id = " + this.id + "]";
    }
}
