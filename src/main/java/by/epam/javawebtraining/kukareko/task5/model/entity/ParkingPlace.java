package by.epam.javawebtraining.kukareko.task5.model.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class ParkingPlace implements Serializable {

    private int number;
    private static final Exchanger<ParkingPlace> exchanger;
    private static Random random;
    private static final int STOP_INTERVAL = 1000;
    private static final int WAITING_REPARKING_INTERVAL = 1000;
    private static final int  NEED_REPARKING = 2;

    static {
        exchanger = new Exchanger<>();
        random = new Random();
    }

    public ParkingPlace() {
    }

    public ParkingPlace(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public ParkingPlace using() {
        ParkingPlace newParkingPlace = this;
        try {
            newParkingPlace = trySwap(newParkingPlace);
            Thread.sleep(random.nextInt(STOP_INTERVAL));

        } catch (InterruptedException ex) {

        }
        return newParkingPlace;
    }

    private ParkingPlace trySwap(ParkingPlace newParkingPlace){
        try {
            if (random.nextInt(NEED_REPARKING) == 1) {
                newParkingPlace = exchanger.exchange(newParkingPlace, random.nextInt(random.nextInt(WAITING_REPARKING_INTERVAL)), TimeUnit.MILLISECONDS);
            }
        } catch (TimeoutException | InterruptedException ex){
            System.out.println("Нет мест для перепарковки");
        }
        return (newParkingPlace.getNumber() - this.getNumber()) < 2 ? newParkingPlace : this;
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
