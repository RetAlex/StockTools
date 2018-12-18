package stock.tools;


import stock.domain.Company;
import stock.domain.CompanyStockInfo;
import stock.tools.interfaces.FairPriceTool;

import java.util.List;

public class FairPriceToolMean implements FairPriceTool {
    private CompanyStockInfo company;
    private List<CompanyStockInfo> analogies;

    public FairPriceToolMean(CompanyStockInfo company, List<CompanyStockInfo> analogies) {
        this.company = company;
        this.analogies = analogies;
    }

    @Override
    public double getFairPrice(double a1, double a2, double a3, double a4) {
        if((a1 + a2 + a3 + a4)==1.0) return -1;
        double p = a1*getP1() + a2*getP2() + a3*getP3() + a4*getP4();

        return  p/company.getActionsQuantity();
    }

    private double getP1(){
        double ev_s = 0.0;


        for(CompanyStockInfo x: analogies){

            ev_s += x.getCompanyCost()/x.getSales();

        }

        ev_s/=analogies.size();

        double ev_avg = ev_s*company.getSales();

        return ev_avg - company.getDebit();
    }

    private double getP2(){
        double ev_ebitda = 0.0;

        for(CompanyStockInfo x: analogies)
            ev_ebitda += x.getCompanyCost()/x.getEBITDA();

        ev_ebitda/=analogies.size();

        double ev_avg = ev_ebitda*company.getEBITDA();

        return ev_avg - company.getDebit();
    }

    private double getP3(){

        double p_e=0.0;

        for(CompanyStockInfo x: analogies)
            p_e += x.getStockCapitalCost()/x.getEarnings();

        p_e/=analogies.size();


        return p_e * company.getEarnings();
    }

    private double getP4(){
        double p_bv=0.0;

        for(CompanyStockInfo x: analogies)
            p_bv += x.getStockCapitalCost()/x.getBalanceCost();

        p_bv/=analogies.size();


        return p_bv * company.getBalanceCost();
    }

}
