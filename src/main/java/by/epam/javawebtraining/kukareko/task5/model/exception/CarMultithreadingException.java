package by.epam.javawebtraining.kukareko.task5.model.exception;

/**
 * @author Yulya Kukareko
 * @version 1.0 31 Mar 2019
 */
public class CarMultithreadingException extends Exception {

    public CarMultithreadingException() {
    }

    public CarMultithreadingException(String message) {
        super(message);
    }

    public CarMultithreadingException(Throwable cause) {
        super(cause);
    }
}
