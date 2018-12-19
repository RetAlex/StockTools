package stock.tools;


import org.junit.Test;
import stock.domain.CompanyStockInfo;
import stock.services.StocksService;
import stock.tools.interfaces.FairPriceTool;


import java.util.ArrayList;
import java.util.List;



public class FairPriceToolMeanTest {





    @Test
    public void test1(){
        StocksService stocksService = new StocksService();

        CompanyStockInfo a = stocksService.prepareData("AAPL").get("AAPL");
        List<CompanyStockInfo> analogies = new ArrayList<>(stocksService.prepareData("GOOGL", "MSFT").values());

        FairPriceToolMean priceTool = new FairPriceToolMean(a, analogies);

        double k1 = 0.25;
        double k2 = 0.5;
        double k3 = 0.1;
        double k4 = 0.15;

        System.out.println("P1 (EV/S) = "+priceTool.getP1());
        System.out.println("P2 (EV/EBITDA) = "+priceTool.getP2());
        System.out.println("P3 (P/E) = "+priceTool.getP3());
        System.out.println("P4 (P/BV) = "+priceTool.getP4());
        System.out.println("Actual P = "+ (long)(a.getActionPrice() * a.getActionsQuantity()));

        System.out.println("Fair price = "+priceTool.getFairPrice(k1,k2,k3,k4));
        System.out.println("Actual price = "+a.getActionPrice());

        System.out.println(a);
        for(CompanyStockInfo x: analogies)
            System.out.println(x);

    }


}
