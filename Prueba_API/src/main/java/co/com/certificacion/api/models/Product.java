package co.com.certificacion.api.models;

import com.google.gson.JsonObject;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Product {
    private String title;
    private String price;
    private String description;
    private String image;
    private String category;

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public static String setData(DataTable dataTable) {
        List<Product> dates = new ArrayList<>();
        List<java.util.Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, Product.class));
        }
        return toJsonString(dates);
    }

    public static String toJsonString(List<Product> lista) {
        JsonObject json = new JsonObject();
        json.addProperty("title", lista.get(0).title);
        json.addProperty("price", Double.parseDouble(lista.get(0).price));
        json.addProperty("description", lista.get(0).description);
        json.addProperty("image", lista.get(0).image);
        json.addProperty("category", lista.get(0).category);
        return json.toString();
    }
}
