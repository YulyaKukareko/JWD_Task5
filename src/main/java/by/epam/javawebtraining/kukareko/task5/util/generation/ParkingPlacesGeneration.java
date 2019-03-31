package by.epam.javawebtraining.kukareko.task5.util.generation;

import by.epam.javawebtraining.kukareko.task5.model.entity.ParkingPlace;
import by.epam.javawebtraining.kukareko.task5.model.entity.ParkingBlocked;

/**
 * @author Yulya Kukareko
 * @version 1.0 30 Mar 2019
 */
public class ParkingPlacesGeneration implements Runnable {

    private ParkingBlocked<ParkingPlace> parking;
    private Thread thread;

    {
        thread = new Thread(this);
    }

    public ParkingPlacesGeneration() {
        this.parking = new ParkingBlocked<>();
        thread.start();
    }

    public ParkingPlacesGeneration(ParkingBlocked<ParkingPlace> parking) {
        this.parking = parking;
        thread.start();
    }

    @Override
    public void run() {
        int count = 1;
        while (count < ParkingBlocked.getCountPlaces()) {
            parking.addResource(new ParkingPlace(count));
            count++;
        }
    }
}
