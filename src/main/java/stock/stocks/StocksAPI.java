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

    public static Stats getStats(String symbol){
        String answer = RequestHelper.getHttp("https://api.iextrading.com/1.0/stock/"+symbol+"/stats", null);
        JSONObject answerJSON = new JSONObject(answer);
        return new Stats(Double.parseDouble(RequestHelper.getHttp("https://api.iextrading.com/1.0/stock/"+symbol+"/price", null)),answerJSON.getLong("sharesOutstanding"), answerJSON.getLong("EBITDA"));
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
    public static class Stats{
        private double price;
        private long volume;
        private long EBITDA;
    }
}