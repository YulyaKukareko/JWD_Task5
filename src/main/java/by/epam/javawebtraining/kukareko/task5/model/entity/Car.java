package by.epam.javawebtraining.kukareko.task5.model.entity;

import by.epam.javawebtraining.kukareko.task5.model.logic.UsingParking;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class Car implements Runnable, Serializable {

    private int number;
    private Parking<ParkingPlace> place;
    private static final int MAX_WAITING_TIME = 3000;
    private static final int TIME_ON_PARKING = 3000;
    private static final int TIME_WITHOUT_REPARKING = 700;
    private static Random random;
    private Thread thread;

    static {
        random = new Random();
    }

    {
        thread = new Thread(this);
    }

    public Car() {
        this.place = new Parking();
        thread.start();
    }

    public Car(int number, Parking places) {
        this.number = number;
        this.place = places;
        thread.start();
    }

    public int getNumber() {
        return number;
    }

    public void run() {
        ParkingPlace currentPlace = null;
        try {
            currentPlace = place.getResource(MAX_WAITING_TIME);
            ParkingPlace oldPlaces;

            Parking.LOGGER.info("Car: " + getNumber() + " took place " + currentPlace.getNumber());

            long end = currentTimeMillis() + random.nextInt(TIME_ON_PARKING);

            while (currentTimeMillis() < end) {
                Thread.sleep(TIME_WITHOUT_REPARKING);

                oldPlaces = currentPlace;
                currentPlace = UsingParking.using(currentPlace);

                if (oldPlaces != currentPlace) {
                    Parking.LOGGER.info("Car " + getNumber() + " reparking on place " + currentPlace.getNumber()
                            + " from place " + oldPlaces.getNumber());
                }
            }

        } catch (Exception e) {
            Parking.LOGGER.info("No place for car: " + getNumber());
        } finally {
            if (currentPlace != null) {
                Parking.LOGGER.info("Car " + getNumber() + " left, making room " + currentPlace.getNumber());
                place.returnResources(currentPlace);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number == car.number &&
                Objects.equals(place, car.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, place);
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", place=" + place +
                ", thread=" + thread +
                '}';
    }
}
