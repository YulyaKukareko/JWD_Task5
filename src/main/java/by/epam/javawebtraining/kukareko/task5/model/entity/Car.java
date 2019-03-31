package by.epam.javawebtraining.kukareko.task5.model.entity;

import by.epam.javawebtraining.kukareko.task5.model.logic.Parking;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class Car implements Runnable, Serializable {

    private int number;
    private Parking<ParkingPlace> place;
    private Thread thread;

    {
        thread = new Thread(this);
    }

    public Car() {
        this.place = new Parking<>();
        thread.start();
    }

    public Car(int number, Parking<ParkingPlace> places) {
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
            currentPlace = place.getResource(5000);
            ParkingPlace oldPlaces = currentPlace;

            System.out.println("Машина: " + getNumber() + " заняла место " + currentPlace.getNumber());
            currentPlace = currentPlace.using();

            if(oldPlaces != currentPlace){
                System.out.println("Машина " + getNumber() + " перепаркоавлвсь на место " + currentPlace.getNumber() + " с места " +
                        oldPlaces.getNumber());
            }

        } catch (Exception e) {
            System.out.println("Нет места для машины: " + getNumber());
        } finally {
            if (currentPlace != null) {
                System.out.println("Машина " + getNumber() + " уехала, освободив место " + currentPlace.getNumber());
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
}
