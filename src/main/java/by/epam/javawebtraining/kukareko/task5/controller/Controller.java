package by.epam.javawebtraining.kukareko.task5.controller;

import by.epam.javawebtraining.kukareko.task5.model.entity.Parking;
import by.epam.javawebtraining.kukareko.task5.util.generation.CarGeneration;
import by.epam.javawebtraining.kukareko.task5.util.generation.ParkingPlacesGeneration;
import org.apache.log4j.Logger;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class Controller {

    public static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static void main(String[] args) {
        try {

            Parking parking = new Parking();

            new ParkingPlacesGeneration(parking);

            new CarGeneration(parking);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
