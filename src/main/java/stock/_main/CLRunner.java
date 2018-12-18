package stock._main;

import org.json.JSONObject;
import stock.domain.CompanyStockInfo;
import stock.services.StocksService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CLRunner {
    public static void main(String[] args){
        try {
            JSONObject configs = new JSONObject(new String(Files.readAllBytes(Paths.get("data.json")), StandardCharsets.UTF_8));
            String company = configs.getString("company");
            List<String> params = configs.getJSONArray("opponents").toList().stream().map(Object::toString).collect(Collectors.toList());
            params.add(company);
            Map<String, CompanyStockInfo> preparedData = new StocksService().prepareData(params);
            System.out.println("Finished");
            //TODO add work with prepared data
        }catch (Exception e){
            Logger.getGlobal().warning("Can't finish process because of exception "+e.getClass()+"["+e.getMessage()+"]");
        }
    }
}
