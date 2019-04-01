package by.epam.javawebtraining.kukareko.task5.model.entity;

import by.epam.javawebtraining.kukareko.task5.model.exception.logical.TimeoutExceededException;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yulya Kukareko
 * @version 1.0 31 Mar 2019
 */
public class Parking<T> {

    public static final Logger LOGGER;
    private static final int COUNT_PLACES = 6;

    private Queue<T> resource;

    static {
        LOGGER = Logger.getLogger("ParkingLogger");
    }

    public Parking() {
        resource = new LinkedList<>();
    }

    public Parking(Queue<T> resources) {
        this.resource = resources;
    }

    public static int getCountPlaces() {
        return COUNT_PLACES;
    }

    public void addResource(T newResource) {
        resource.add(newResource);
    }

    public synchronized T getResource(long maxWaitMillis) throws Exception {
        try {
            if (resource.size() != 0) {
                return resource.poll();
            } else {
                wait(maxWaitMillis);
                if(resource.size() != 0){
                    return resource.poll();
                }
            }
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }
        throw new TimeoutExceededException();
    }

    public synchronized void returnResources(T res) {
        resource.add(res);
    }
}
