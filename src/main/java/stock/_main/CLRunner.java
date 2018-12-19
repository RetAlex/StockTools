package stock._main;

import org.json.JSONObject;
import stock.domain.CompanyStockInfo;
import stock.services.StocksService;
import stock.tools.FairPriceToolMean;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CLRunner {
    public static void main(String[] args){
        try {
            JSONObject configs = new JSONObject(new String(Files.readAllBytes(Paths.get("data.json")), StandardCharsets.UTF_8));
            String company = configs.getString("company");
            List<String> params = configs.getJSONArray("opponents").toList().stream().map(Object::toString).collect(Collectors.toList());
            params.add(company);
            Map<String, CompanyStockInfo> preparedData = new StocksService().prepareData(params);
            System.out.println("Finished");

            CompanyStockInfo companyStockInfo = preparedData.get(company);
            preparedData.remove(company);
            List<CompanyStockInfo> analogies = new ArrayList<>(preparedData.values());
            FairPriceToolMean priceTool = new FairPriceToolMean(companyStockInfo, analogies);


            double k1 = 0.1;
            double k2 = 0.3;
            double k3 = 0.3;
            double k4 = 0.3;

            System.out.println("P1 (EV/S) = "+percents(companyStockInfo.getStockCapitalCost(), priceTool.getP1()) + "%");
            System.out.println("P2 (EV/EBITDA) = "+percents(companyStockInfo.getStockCapitalCost(),priceTool.getP2()) + "%");
            System.out.println("P3 (P/E) = "+percents(companyStockInfo.getStockCapitalCost(),priceTool.getP3()) + "%");
            System.out.println("P4 (P/BV) = "+percents(companyStockInfo.getStockCapitalCost(),priceTool.getP4()) + "%");

            double fairPrice = priceTool.getFairPrice(k1,k2,k3,k4);
            int sign = fairPrice - companyStockInfo.getActionPrice() > 0 ? 1 : -1;
            int surge = (int)((fairPrice / companyStockInfo.getActionPrice())*100.0);

            System.out.println("\nFair price = "+fairPrice);
            System.out.println("Actual price = "+companyStockInfo.getActionPrice());

            System.out.println("Expecting " + (sign >0 ? "increase" : "decrease") + " = "+(sign > 0?  surge - 100 : 100-surge)+"%");


        }catch (Exception e){
            Logger.getGlobal().warning("Can't finish process because of exception "+e.getClass()+"["+e.getMessage()+"]");
        }
    }


    private static long percents(long actual, long expected){



        return (long) (((double)expected/actual)*100.0);
    }
}
