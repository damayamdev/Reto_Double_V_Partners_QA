package co.com.certificacion.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;

public class ValidarRespuestaEstado implements Question<Boolean> {

    private final int codigo;

    public ValidarRespuestaEstado(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                ResponseConsequence.seeThatResponse("Código de estado de la respuesta de la API del servicio",
                        response -> response
                                .statusCode(codigo))
        );
        return true;
    }

    public static ValidarRespuestaEstado expected(int codigo){
        return new ValidarRespuestaEstado(codigo);
    }
}
