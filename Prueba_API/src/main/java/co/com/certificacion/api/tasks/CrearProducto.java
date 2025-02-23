package co.com.certificacion.api.tasks;

import co.com.certificacion.api.models.Product;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.http.ContentType.JSON;

public class CrearProducto implements Task {

    private String json;
    private String endpoint;

    public CrearProducto(String endpoint, String json){
        this.json = json;
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Post.to(endpoint)
                        .with(
                                resource -> resource
                                        .contentType(JSON)
                                        .body(json)
                                        .relaxedHTTPSValidation()
                        )
        );

        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK){
            throw new AssertionError("El codigo de respuesta es diferente al apropiado");
        }else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            actor.remember("responsePost", response);
        }
    }

    public static CrearProducto crear(String endpoint ,String json){
        return Tasks.instrumented(CrearProducto.class, endpoint, json);
    }
}
