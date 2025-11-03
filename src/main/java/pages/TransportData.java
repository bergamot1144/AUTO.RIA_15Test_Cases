package pages;

import java.util.ArrayList;
import java.util.List;

public class TransportData {
    private String title;
    private String price;
    private String year;
    private String location;

    public TransportData(String title, String price, String year, String location) {
        this.title = title;
        this.price = price;
        this.year = year;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TransportData{title='" + title + "', price='" + price + "', year='" + year + "', location='" + location
                + "'}";
    }
}
