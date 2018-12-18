package stock.services;

import org.junit.Test;

public class StocksServiceTest {

    @Test
    public void prepareData() {
        StocksService stocksService = new StocksService();
        System.out.println(stocksService.prepareData("AMZN"));
    }
}