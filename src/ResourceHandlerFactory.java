import java.util.concurrent.CountDownLatch;

public final class ResourceHandlerFactory extends ResourceTaskFactory {

    @Override
    protected ResourceHandler create(final long id, final CountDownLatch latch) {
        return new ResourceHandler(id, latch);
    }
}
