package stock.stocks;

import org.junit.Test;

public class StocksAPITest {

    @Test
    public void getFinancials() {
        System.out.println(StocksAPI.getFinancials("msft"));
    }

    @Test
    public void getPriceAndVolume() {
        System.out.println(StocksAPI.getStats("amzn"));
    }
}