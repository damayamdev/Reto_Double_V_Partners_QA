package co.com.certificacion.api.tasks;

import co.com.certificacion.api.exceptions.AssertionsServices;
import co.com.certificacion.api.exceptions.ErrorServicesException;
import co.com.certificacion.api.models.Product;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.http.ContentType.JSON;

public class EliminarPorCondicion implements Task {

    private String endpoint;

    public EliminarPorCondicion(String endpoint){
        this.endpoint = endpoint;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        JSONArray datos = actor.recall("response");
        List<JSONObject> filteredProducts = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            JSONObject product = datos.getJSONObject(i);
            JSONObject rating = product.getJSONObject("rating");
            if (rating.getInt("count") < 100) {
                filteredProducts.add(product);
            }
        }
        for (JSONObject product : filteredProducts) {
            actor.attemptsTo(
                    Get.resource(endpoint+product.getInt("id"))
                            .with(
                                    resource -> resource
                                            .contentType(JSON)
                                            .relaxedHTTPSValidation()
                            )
            );
        }
        if(SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK){
            throw new ErrorServicesException(AssertionsServices.EL_CODIGO_DE_RESPUESTA_ES_DIFERENTE_AL_APROPIADO);
        }else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            actor.remember("response", response);
        }
    }

    public static EliminarPorCondicion eliminar(String endpoint){
        return Tasks.instrumented(EliminarPorCondicion.class, endpoint);
    }
}
