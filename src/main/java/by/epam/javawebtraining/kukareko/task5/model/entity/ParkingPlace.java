package by.epam.javawebtraining.kukareko.task5.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class ParkingPlace implements Serializable {

    private int number;

    public ParkingPlace() {
    }

    public ParkingPlace(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingPlace that = (ParkingPlace) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "by.epam.javawebtraining.kukareko.task5.model.entity.ParkingPlace{" +
                "number=" + number +
                '}';
    }
}
