package by.epam.javawebtraining.kukareko.task5.util.generation;

import by.epam.javawebtraining.kukareko.task5.model.entity.Parking;
import by.epam.javawebtraining.kukareko.task5.model.entity.ParkingPlace;

/**
 * @author Yulya Kukareko
 * @version 1.0 30 Mar 2019
 */
public class ParkingPlacesGeneration implements Runnable {

    private Parking<ParkingPlace> parking;
    private Thread thread;

    {
        thread = new Thread(this);
    }

    public ParkingPlacesGeneration() {
        this.parking = new Parking<>();
        thread.start();
    }

    public ParkingPlacesGeneration(Parking<ParkingPlace> parking) {
        this.parking = parking;
        thread.start();
    }

    @Override
    public void run() {
        int count = 1;
        while (count < Parking.getCountPlaces()) {
            parking.addResource(new ParkingPlace(count));
            count++;
        }
    }
}
