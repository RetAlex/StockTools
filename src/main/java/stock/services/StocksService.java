package stock.services;

import stock.domain.CompanyStockInfo;
import stock.stocks.StocksAPI;

import java.util.ArrayList;
import java.util.List;

public class StocksService {
    private CompanyStockInfo company;
    private List<CompanyStockInfo> opponents;

    public void prepareData(String companyName, List<String> opponentsNames){
        company = prepareData(companyName);
        opponents = new ArrayList<>(opponentsNames.size());
        for(String opponentName: opponentsNames){
            opponents.add(prepareData(opponentName));
        }
    }

    private CompanyStockInfo prepareData(String name){
        StocksAPI.Financials fin = StocksAPI.getFinancials(name);
        StocksAPI.PriceAndVolume pav = StocksAPI.getPriceAndVolme(name);
    }
}
