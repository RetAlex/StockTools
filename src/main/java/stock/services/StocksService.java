package stock.services;

import stock.domain.CompanyStockInfo;
import stock.stocks.StocksAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StocksService {
    public Map<String, CompanyStockInfo> prepareData(String companyName, List<String> opponentsNames){
        Map<String, CompanyStockInfo> result = new HashMap<>();
        result.put(companyName, prepareData(companyName));
        for(String opponentName: opponentsNames){
            result.put(opponentName, prepareData(opponentName));
        }
        return result;
    }

    private CompanyStockInfo prepareData(String name){
        StocksAPI.Financials fin = StocksAPI.getFinancials(name);
        StocksAPI.PriceAndVolume pav = StocksAPI.getPriceAndVolme(name);
        return new CompanyStockInfo(pav.getVolume(), pav.getPrice(), fin.getRevenue(), fin.getDebt(), fin.getShareholderEquity(), fin.getIncome(), fin.getExpense(), StocksAPI.getEBITDA(name));
    }
}
