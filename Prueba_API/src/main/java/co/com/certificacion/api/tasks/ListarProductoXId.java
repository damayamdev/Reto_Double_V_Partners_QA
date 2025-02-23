package co.com.certificacion.api.tasks;

import co.com.certificacion.api.exceptions.AssertionsServices;
import co.com.certificacion.api.exceptions.ErrorServicesException;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.http.ContentType.JSON;

public class ListarProductoXId implements Task {

    private String endpoint;

    public ListarProductoXId(String endpoint){
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Get.resource(endpoint)
                        .with(
                                resource -> resource
                                        .contentType(JSON)
                                        .relaxedHTTPSValidation()
                        )
        );
        if(SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK){
            throw new ErrorServicesException(AssertionsServices.EL_CODIGO_DE_RESPUESTA_ES_DIFERENTE_AL_APROPIADO);
        }else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            actor.remember("response", response);
        }
    }

    public static ListarProductoXId get(String endpoint){
        return Tasks.instrumented(ListarProductoXId.class, endpoint);
    }
}
