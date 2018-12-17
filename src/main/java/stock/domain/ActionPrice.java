package stock.domain;

import java.util.Date;

public class ActionPrice {

    private int price;
    private Date date;

    public ActionPrice(int price, Date date) {
        this.price = price;
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
