package co.com.certificacion.api.questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.json.JSONArray;
import org.json.JSONObject;

public class ValidarDatos implements Question<Boolean> {

    private String items;

    private ValidarDatos(String datos){
        this.items = datos;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        JSONArray responseLocal = new JSONArray(items);
        JSONArray response = actor.recall("response");

        boolean respuesta = false;

        for (int i = 0; i < responseLocal.length(); i++) {
            JSONObject local = responseLocal.getJSONObject(i);
            JSONObject endpoint = response.getJSONObject(i);
            respuesta = local.getInt("id") == endpoint.getInt("id") && local.getString("title").equals(endpoint.getString("title")) && local.getString("description").equals(endpoint.getString("description")) && local.getString("category").equals(endpoint.getString("category"));
            if (!respuesta) {
                throw new AssertionError("Los datos del producto recibidos no coinciden con los esperados.\n" +
                        "Producto esperado: \n" +
                        "ID: " + local.getInt("id") + "\n" +
                        "Título: " + local.getString("title") + "\n" +
                        "Descripción: " + local.getString("description") + "\n" +
                        "Categoría: " + local.getString("category") + "\n\n" +
                        "Producto recibido: \n" +
                        "ID: " + endpoint.getInt("id") + "\n" +
                        "Título: " + endpoint.getString("title") + "\n" +
                        "Descripción: " + endpoint.getString("description") + "\n" +
                        "Categoría: " + endpoint.getString("category"));
            }

        }

        return respuesta;
    }

    public static ValidarDatos items(String texto){
        return new ValidarDatos(texto);
    }
}
