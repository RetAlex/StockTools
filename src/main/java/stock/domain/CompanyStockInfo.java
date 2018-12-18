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


    public Period getPeriod() {
        return period;
    }

    public long getActionsQuantity() {
        return actionsQuantity;
    }

    public ActionPrice getActionPrice() {
        return actionPrice;
    }

    public long getSales() {
        return sales;
    }

    public long getDebit() {
        return debit;
    }

    public long getBalanceCost() {
        return balanceCost;
    }

    public long getEarnings() {
        return earnings;
    }

    public long getDepression() {
        return depression;
    }

    public long getAmortization() {
        return amortization;
    }

    public long getExpense() {
        return expense;
    }
}
