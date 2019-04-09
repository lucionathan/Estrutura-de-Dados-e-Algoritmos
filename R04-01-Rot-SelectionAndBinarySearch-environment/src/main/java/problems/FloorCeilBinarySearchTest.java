package problems;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FloorCeilBinarySearchTest {

    private Integer[] array;
    private Integer[] arrayVazio;
    private FloorCeilBinarySearch fc;

    @Before
    public void setUp() {

        fc = new FloorCeilBinarySearch();
        array = new Integer[]{1, 2, 4, 6, 78, 90, 105, 245, 290};
        arrayVazio = new Integer[]{};
    }

    @Test
    public void floor() {
        Integer b = null;
        assertEquals(b, fc.floor(array, 1));
        Integer x = 1;
        assertEquals(x, fc.floor(array, 2));
        Integer y = 2;
        assertEquals(y, fc.floor(array, 4));
        Integer z = 4;
        assertEquals(z, fc.floor(array, 6));
        Integer p = 245;
        assertEquals(p, fc.floor(array, 290));
        assertEquals(b, fc.floor(arrayVazio, 240));

    }

    @Test
    public void ceil() {
        System.out.println(arrayVazio.length);
        Integer x = 2;
        assertEquals(x, fc.ceil(array, 1));
        Integer y = 6;
        assertEquals(y, fc.ceil(array, 4));
        Integer z = 78;
        assertEquals(z, fc.ceil(array, 6));
        Integer p = null;
        assertEquals(p, fc.ceil(array, 290));
        assertEquals(p, fc.ceil(arrayVazio, 240));

    }
}