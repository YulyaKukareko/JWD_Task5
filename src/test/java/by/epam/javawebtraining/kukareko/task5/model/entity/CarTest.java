package by.epam.javawebtraining.kukareko.task5.model.entity;

import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.Test;

/**
 * @author Yulya Kukareko
 * @version 1.0 01 Apr 2019
 */
public class CarTest {

    private Car car;
    private static final int THREAD_COUNT = 5;

    @Test(timeout = 5000)
    @ThreadCount(THREAD_COUNT)
    public void runTest() {
        car = new Car();
    }
}
