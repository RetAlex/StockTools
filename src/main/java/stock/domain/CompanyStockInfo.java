package stock.domain;

import stock.domain.utils.Period;

public class CompanyStockInfo {

    private Period period;
    private long actionsQuantity;
    private ActionPrice actionPrice;
    private long sales;
    private long debit;
    private long balanceCost;
    private long earnings;
    private long depression;
    private long amortization;
    private long expense;



    public long getEBIT(){
        return sales - expense;
    }

    public long getEBITDA(){
        return getEBIT() + depression + amortization;
    }

    public long getStockCapitalCost(){
        return actionsQuantity * actionPrice.getPrice();
    }

    public long getCompanyCost(){
        return getStockCapitalCost() + debit;
    }

    public long getProfitability(){
        return getEBIT()/sales * 100; // TODO check for cast
    }
}
