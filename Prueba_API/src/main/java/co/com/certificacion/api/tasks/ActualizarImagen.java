package co.com.certificacion.api.tasks;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class ActualizarImagen implements Task {

    private String imagen;
    private String endpoint;

    public ActualizarImagen(String endpoint, String imagen){
        this.imagen = imagen;
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();

        JsonObject json = new JsonObject();
        json.addProperty("image", imagen);

        actor.attemptsTo(
                Put.to(endpoint)
                        .with(
                                resource -> resource
                                        .contentType(ContentType.JSON)
                                        .body(json.toString())
                                        .relaxedHTTPSValidation()
                        )
        );



        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK) {
            throw new AssertionError("El codigo de respuesta es diferente al apropiado");
        } else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            actor.remember("response", response);
        }
    }

    public static ActualizarImagen actualizarImagen(String endpoint, String imagen){
        return Tasks.instrumented(ActualizarImagen.class, endpoint, imagen);
    }
}
