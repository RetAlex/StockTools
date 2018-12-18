package stock.stocks;

import org.junit.Test;

public class StocksAPITest {

    @Test
    public void getFinancials() {
        System.out.println(StocksAPI.getFinancials("amzn"));
    }

    @Test
    public void getPriceAndVolume() {
        System.out.println(StocksAPI.getPriceAndVolme("amzn"));
    }
}