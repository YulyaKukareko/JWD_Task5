package by.epam.javawebtraining.kukareko.task5.model.entity;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yulya Kukareko
 * @version 1.0 01 Apr 2019
 */
@RunWith(ConcurrentTestRunner.class)
public class ParkingTest {

    private static Parking<ParkingPlace> parking;
    private static Queue<ParkingPlace> places;
    private static ParkingPlace[] parkingPlaces;
    private static final int COUNT_THREADS = 5;

    @BeforeClass
    public static void init() {
        parkingPlaces = new ParkingPlace[]{new ParkingPlace(1), new ParkingPlace(2),
                new ParkingPlace(3), new ParkingPlace(4), new ParkingPlace(5)};
        places = new LinkedList<>(Arrays.asList(parkingPlaces));
        parking = new Parking<>(places);
    }

    @Test
    @ThreadCount(COUNT_THREADS)
    public void testGetResources() throws Exception {
        parking.getResource(0);
    }

    @After
    public void testCheckCount() {
        int expected = 0;

        Assert.assertEquals(expected, places.size());
    }
}
