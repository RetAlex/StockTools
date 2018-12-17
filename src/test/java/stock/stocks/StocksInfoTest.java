package stock.stocks;

import org.junit.Test;

public class StocksInfoTest {

    @Test
    public void getFinancials() {
        System.out.println(StocksInfo.getFinancials("amzn"));
    }

    @Test
    public void getPriceAndVolume() {
        System.out.println(StocksInfo.getPriceAndVolme("amzn"));
    }
}