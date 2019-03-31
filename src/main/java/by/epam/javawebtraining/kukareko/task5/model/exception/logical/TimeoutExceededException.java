package by.epam.javawebtraining.kukareko.task5.model.exception.logical;

/**
 * @author Yulya Kukareko
 * @version 1.0 31 Mar 2019
 */
public class TimeoutExceededException extends CarMultithreadingLogicalException {

    public TimeoutExceededException() {
        super("Car timeout exceeded");
    }
}
