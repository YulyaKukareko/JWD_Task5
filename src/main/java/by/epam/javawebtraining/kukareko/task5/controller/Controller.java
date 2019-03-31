package by.epam.javawebtraining.kukareko.task5.controller;

import by.epam.javawebtraining.kukareko.task5.model.entity.ParkingPlace;
import by.epam.javawebtraining.kukareko.task5.model.entity.Parking;
import by.epam.javawebtraining.kukareko.task5.util.generation.CarGeneration;
import by.epam.javawebtraining.kukareko.task5.util.generation.ParkingPlacesGeneration;

/**
 * @author Yulya Kukareko
 * @version 1.0 29 Mar 2019
 */
public class Controller {

    public static void main(String[] args) {

        Parking<ParkingPlace> parking = new Parking<>();

        new ParkingPlacesGeneration(parking);

        new CarGeneration(parking);
    }
}
