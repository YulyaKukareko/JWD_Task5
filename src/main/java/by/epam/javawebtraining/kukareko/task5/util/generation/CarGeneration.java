package by.epam.javawebtraining.kukareko.task5.util.generation;

import by.epam.javawebtraining.kukareko.task5.model.entity.Car;
import by.epam.javawebtraining.kukareko.task5.model.entity.ParkingPlace;
import by.epam.javawebtraining.kukareko.task5.model.logic.Parking;

import java.util.Random;

/**
 * @author Yulya Kukareko
 * @version 1.0 30 Mar 2019
 */
public class CarGeneration implements Runnable {

    private Parking<ParkingPlace> parking;
    private Thread thread;
    private static Random random;
    private static final int INTERVAL_GENERATION = 100;

    {
        thread = new Thread(this);
        random = new Random();
    }

    public CarGeneration() {
        parking = new Parking<>();
        thread.start();
    }

    public CarGeneration(Parking<ParkingPlace> parking) {
        this.parking = parking;
        thread.start();
    }

    @Override
    public void run() {
        int number = 1;
        while (true) {
            new Car(number, parking);
            try {
                Thread.sleep(random.nextInt(INTERVAL_GENERATION));
            } catch (InterruptedException ex) {

            }
            number++;
        }
    }
}
