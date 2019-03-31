package by.epam.javawebtraining.kukareko.task5.model.exception.logical;

import by.epam.javawebtraining.kukareko.task5.model.exception.CarMultithreadingException;

/**
 * @author Yulya Kukareko
 * @version 1.0 31 Mar 2019
 */
public class CarMultithreadingLogicalException extends CarMultithreadingException {

    public CarMultithreadingLogicalException() {
    }

    public CarMultithreadingLogicalException(String message) {
        super(message);
    }
}
