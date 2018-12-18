package stock.services;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StocksServiceTest {

    @Test
    public void prepareData() {
        StocksService stocksService = new StocksService();
        System.out.println(stocksService.prepareData("AMZN", new ArrayList<>()));
    }
}