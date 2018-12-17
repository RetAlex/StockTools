package stock.tools;


import stock.domain.Company;
import stock.tools.interfaces.FairPriceTool;

import java.util.List;

public class FairPriceToolMean implements FairPriceTool {
    private Company company;
    private List<Company> analoges;

    public FairPriceToolMean(Company company, List<Company> analoges) {
    }

    @Override
    public long getFairPrice(double p1, double p2, double p3, double p4) {
        return 0;
    }
}
