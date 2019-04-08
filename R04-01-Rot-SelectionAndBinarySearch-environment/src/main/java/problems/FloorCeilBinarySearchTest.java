package problems;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FloorCeilBinarySearchTest {

    private Integer[] array;
    private FloorCeilBinarySearch fc;

    @Before
    public void setUp() {

        fc = new FloorCeilBinarySearch();
        array = new Integer[]{1, 2, 4, 6, 78, 90, 105, 245, 290};
    }

    @Test
    public void floor() {
        Integer x = 4;
        assertEquals(x, fc.floor(array, 6));


    }

    @Test
    public void ceil() {
    }
}