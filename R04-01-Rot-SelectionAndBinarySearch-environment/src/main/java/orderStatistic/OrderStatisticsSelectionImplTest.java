package orderStatistic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderStatisticsSelectionImplTest {

    private Integer[] array;
    private OrderStatisticsSelectionImpl ossi;

    @Before
    public void setUp() throws Exception {
        array = new Integer[]{1, 21, 4, 6, 78, 105, 245, 3, 523, 12, 29};
        ossi = new OrderStatisticsSelectionImpl();
    }

    @Test
    public void getOrderStatistics() {
        assertEquals(12, ossi.getOrderStatistics(array, 5));
        assertEquals(523, ossi.getOrderStatistics(array, 11));
    }
}