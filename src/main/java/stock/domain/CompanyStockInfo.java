package stock.domain;

import stock.domain.utils.Period;

public class CompanyStockInfo {

    private long actionsQuantity;
    private double actionPrice;
    private long sales;
    private long debit;
    private long balanceCost;
    private long earnings;
    private long expense;
    private long EBITDA;



    public long getEBITDA(){
        return EBITDA;
    }

    public long getStockCapitalCost(){
        return (long) (actionsQuantity * actionPrice);
    }

    public long getCompanyCost(){
        return getStockCapitalCost() + debit;
    }

    public long getActionsQuantity() {
        return actionsQuantity;
    }

    public double getActionPrice() {
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


    public long getExpense() {
        return expense;
    }

    public void setEBITDA(long EBITDA) {
        this.EBITDA = EBITDA;
    }

    public void setActionsQuantity(long actionsQuantity) {
        this.actionsQuantity = actionsQuantity;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public void setDebit(long debit) {
        this.debit = debit;
    }

    public void setBalanceCost(long balanceCost) {
        this.balanceCost = balanceCost;
    }

    public void setEarnings(long earnings) {
        this.earnings = earnings;
    }

    public void setExpense(long expense) {
        this.expense = expense;
    }

    public void setActionPrice(double actionPrice) {
        this.actionPrice = actionPrice;
    }


}
