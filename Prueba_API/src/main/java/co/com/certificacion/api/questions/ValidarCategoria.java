package co.com.certificacion.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.json.JSONArray;
import org.json.JSONObject;

public class ValidarCategoria implements Question<Boolean> {

    private String categoria;

    private ValidarCategoria(String categoria){
        this.categoria = categoria;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        JSONArray response = actor.recall("response");

        boolean respuesta = true;

        for (int i = 0; i < response.length(); i++) {
            JSONObject endpoint = response.getJSONObject(i);

            respuesta = endpoint.getString("category").equals(categoria);

            if (!respuesta) {
                throw new AssertionError("El producto no pertenece a la categoría 'electronics'.\n" +
                        "Producto esperado: \n" +
                        "Categoría esperada: electronics\n" +
                        "Categoría recibida: " + endpoint.getString("category"));
            }
        }

        return respuesta;
    }

    public static ValidarCategoria go(String categoria){
        return new ValidarCategoria(categoria);
    }
}
