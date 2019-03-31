package by.epam.javawebtraining.kukareko.task5.model.logic;

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

    private static final int COUNT_PLACES = 6;
    private final Semaphore semaphore;
    private Queue<T> resource;

    public Parking() {
        this.semaphore = new Semaphore(COUNT_PLACES, true);
        resource = new LinkedList<>();
    }

    public Parking(Queue<T> resources) {
        this.semaphore = new Semaphore(COUNT_PLACES, true);
        this.resource = new LinkedList<>();
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

        }
        throw new Exception("превышено время ожидания");
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
        return "by.epam.javawebtraining.kukareko.task5.model.logic.Parking{" +
                "semaphore=" + semaphore +
                ", resource=" + resource +
                '}';
    }
}
