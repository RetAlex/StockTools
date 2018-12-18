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

        CompanyStockInfo a = stocksService.prepareData("APPL").get("APPL");
        List<CompanyStockInfo> analogies = new ArrayList(stocksService.prepareData("GOOGL","MFST").values());

        FairPriceTool priceTool = new FairPriceToolMean(a, analogies);

        double k1 = 0.1;
        double k2 = 0.4;
        double k3 = 0.2;
        double k4 = 0.4;

        System.out.println(priceTool.getFairPrice(k1,k2,k3,k4));

    }


}
