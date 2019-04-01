package by.epam.javawebtraining.kukareko.task5.model.entity;

import by.epam.javawebtraining.kukareko.task5.model.exception.logical.TimeoutExceededException;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class Parking<T> {

    public static final Logger LOGGER;
    private static final int COUNT_PLACES = 6;
    private final Semaphore semaphore;
    private Queue<T> resource;

    static {
        LOGGER = Logger.getLogger("ParkingLogger");
    }

    public Parking() {
        this.semaphore = new Semaphore(COUNT_PLACES, true);
        resource = new LinkedList<>();
    }

    public Parking(Queue<T> resources) {
        this.semaphore = new Semaphore(COUNT_PLACES, true);
        this.resource = resources;
    }

    public static int getCountPlaces() {
        return COUNT_PLACES;
    }

    public void addResource(T newResource) {
        resource.add(newResource);
    }

    public T getResource(long maxWaitMillis) throws Exception {
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                return resource.poll();
            }
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }
        throw new TimeoutExceededException();
    }

    public void returnResources(T res) {
        resource.add(res);
        semaphore.release();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking<?> parking = (Parking<?>) o;
        return Objects.equals(semaphore, parking.semaphore) &&
                Objects.equals(resource, parking.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semaphore, resource);
    }

    @Override
    public String toString() {
        return "by.epam.javawebtraining.kukareko.task5.model.entity.Parking{" +
                "semaphore=" + semaphore +
                ", resource=" + resource +
                '}';
    }
}
