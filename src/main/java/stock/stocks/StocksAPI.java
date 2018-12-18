package stock.stocks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import stock.utils.RequestHelper;

public class StocksAPI {
    private static  final String alphavantageKey = "O28ZXN46GMIFDX27";
    public static Financials getFinancials(String symbol){
        String answer = RequestHelper.getHttp("https://api.iextrading.com/1.0/stock/"+symbol+"/financials", null);
        JSONObject answerJSON = new JSONObject(answer).getJSONArray("financials").getJSONObject(0);
        return new Financials(answerJSON.isNull("totalDebt")?0:answerJSON.getLong("totalDebt"), answerJSON.getLong("netIncome"), answerJSON.getLong("totalRevenue"), answerJSON.getLong("shareholderEquity"), answerJSON.getLong("operatingExpense"));
    }
    /*
        Available fields to add:
        reportDate	string
        grossProfit	number
        costOfRevenue	number
        operatingRevenue	number
        totalRevenue	number
        operatingIncome	number
        netIncome	number
        researchAndDevelopment	number
        operatingExpense	number
        currentAssets	number
        totalAssets	number
        totalLiabilities	number
        currentCash	number
        currentDebt	number
        totalCash	number
        totalDebt	number
        shareholderEquity	number
        cashChange	number
        cashFlow	number
        operatingGainsLosses
    */

    public static long getEBITDA(String symbol){
        String answer = RequestHelper.getHttp("https://api.iextrading.com/1.0/stock/"+symbol+"/stats", null);
        JSONObject answerJSON = new JSONObject(answer);
        return answerJSON.getLong("EBITDA");
    }

    public static PriceAndVolume getPriceAndVolme(String symbol){
        String answer = RequestHelper.getHttp("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+symbol+"&apikey="+alphavantageKey, null);
        JSONObject answerJSON = new JSONObject(answer).getJSONObject("Global Quote");
        return new PriceAndVolume(Double.parseDouble(answerJSON.getString("05. price")), Long.parseLong(answerJSON.getString("06. volume")));
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Financials{
        private long debt;
        private long income;
        private long revenue;
        private long shareholderEquity;
        private long expense;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PriceAndVolume{
        private double price;
        private long volume;
    }
}