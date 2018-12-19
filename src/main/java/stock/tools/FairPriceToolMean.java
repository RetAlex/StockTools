package stock.tools;


import stock.domain.CompanyStockInfo;
import stock.tools.interfaces.FairPriceTool;

import java.util.List;

public class FairPriceToolMean implements FairPriceTool {
    private CompanyStockInfo company;
    private List<CompanyStockInfo> analogies;
    private long p1;
    private long p2;
    private long p3;
    private long p4;

    public FairPriceToolMean(CompanyStockInfo company, List<CompanyStockInfo> analogies) {
        this.company = company;
        this.analogies = analogies;
        this.computeMultiplicators();

    }

    @Override
    public long getFairPrice(double a1, double a2, double a3, double a4) {

        double p = a1*p1 + a2*p2 + a3*p3 + a4*p4;

        if(company.getActionsQuantity() <= 0) return -1;

        return ((long) p )/company.getActionsQuantity();
    }

    public void computeMultiplicators(){
        this.p1 = p1();
        this.p2 = p2();
        this.p3 = p3();
        this.p4 = p4();
    }

    private long p1(){
        long ev_s = 0;
        int i = 0;

        for(CompanyStockInfo x: analogies){

            if(x.getSales() <= 0) continue;

            ev_s += x.getCompanyCost()/x.getSales();
            i++;
        }

        ev_s/=i;

        long ev_avg = ev_s*company.getSales();

        return ev_avg - company.getDebit();
    }

    private long p2(){
        long ev_ebitda = 0;
        int i = 0;

        for(CompanyStockInfo x: analogies){
            if(x.getEBITDA() <= 0) continue;

            ev_ebitda += x.getCompanyCost()/x.getEBITDA();
            i++;
        }


        ev_ebitda/=i;

        long ev_avg = ev_ebitda*company.getEBITDA();

        return ev_avg - company.getDebit();
    }

    private long p3(){

        long p_e=0;
        int i = 0;

        for(CompanyStockInfo x: analogies){

            if(x.getEarnings() <= 0) continue;

            p_e += x.getStockCapitalCost()/x.getEarnings();
            i++;
        }


        p_e/=i;


        return p_e * company.getEarnings();
    }

    private long p4(){
        long p_bv=0;
        int i = 0;

        for(CompanyStockInfo x: analogies){

            if(x.getBalanceCost() <= 0) continue;

            p_bv += x.getStockCapitalCost()/x.getBalanceCost();
            i++;
        }


        p_bv/=i;


        return p_bv * company.getBalanceCost();
    }


    public long getP1() {
        return p1;
    }

    public long getP2() {
        return p2;
    }

    public long getP3() {
        return p3;
    }

    public long getP4() {
        return p4;
    }

    public void setP1(long p1) {
        this.p1 = p1;
    }

    public void setP2(long p2) {
        this.p2 = p2;
    }

    public void setP3(long p3) {
        this.p3 = p3;
    }

    public void setP4(long p4) {
        this.p4 = p4;
    }

    public void setAnalogies(List<CompanyStockInfo> analogies) {
        this.analogies = analogies;
        computeMultiplicators();
    }
}
