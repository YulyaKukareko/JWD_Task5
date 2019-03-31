package by.epam.javawebtraining.kukareko.task5.model.logic;

import by.epam.javawebtraining.kukareko.task5.model.entity.ParkingPlace;
import by.epam.javawebtraining.kukareko.task5.view.FileRender;
import by.epam.javawebtraining.kukareko.task5.view.Renderer;
import org.apache.log4j.Logger;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Yulya Kukareko
 * @version 1.0 31 Mar 2019
 */
public class UsingParking {

    private static final Logger LOGGER;
    private static final Exchanger<ParkingPlace> exchanger;
    private static final int STOP_INTERVAL = 1000;
    private static final int WAITING_REPARKING_INTERVAL = 1000;
    private static final int  NEED_REPARKING = 2;

    private static Random random;
    private static Renderer renderer;

    static {
        exchanger = new Exchanger<>();
        random = new Random();
        LOGGER = Logger.getLogger("UsingParkingLogger");
        renderer = new FileRender();
    }

    public static ParkingPlace using(ParkingPlace currentPlace) {
        ParkingPlace newParkingPlace = currentPlace;
        try {
            newParkingPlace = trySwap(newParkingPlace, currentPlace);
            Thread.sleep(random.nextInt(STOP_INTERVAL));

        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }
        return newParkingPlace;
    }

    private static ParkingPlace trySwap(ParkingPlace newParkingPlace, ParkingPlace currentParking){
        try {
            if (random.nextInt(NEED_REPARKING) == 1) {
                newParkingPlace = exchanger.exchange(newParkingPlace, random.nextInt(random.nextInt(WAITING_REPARKING_INTERVAL)), TimeUnit.MILLISECONDS);
            }
        } catch (TimeoutException ex){
            renderer.render("No car for reparking");
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }
        return Math.abs(newParkingPlace.getNumber() - currentParking.getNumber()) < 2 ? newParkingPlace : currentParking;
    }
}
