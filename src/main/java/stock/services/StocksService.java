package stock.services;

import stock.domain.CompanyStockInfo;
import stock.stocks.StocksAPI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StocksService {
    public Map<String, CompanyStockInfo> prepareData(List<String> symbols){
        Map<String, CompanyStockInfo> result = new HashMap<>();
        for(String opponentName: symbols){
            result.put(opponentName, prepareData(opponentName));
        }
        return result;
    }

    public Map<String, CompanyStockInfo> prepareData(String... companies){
        return prepareData(Arrays.asList(companies));
    }

    private CompanyStockInfo prepareData(String name){
        StocksAPI.Financials fin = StocksAPI.getFinancials(name);
        StocksAPI.Stats stats = StocksAPI.getStats(name);
        return new CompanyStockInfo(stats.getVolume(), stats.getPrice(), fin.getRevenue(), fin.getDebt(), fin.getShareholderEquity(), fin.getIncome(), fin.getExpense(), stats.getEBITDA());
    }
}
