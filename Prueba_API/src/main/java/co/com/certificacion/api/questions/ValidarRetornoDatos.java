package co.com.certificacion.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.hamcrest.Matchers;

public class ValidarRetornoDatos implements Question<Boolean> {


    private String nombre;
    private String item;

    public ValidarRetornoDatos(String nombre, String item){
        this.nombre = nombre;
        this.item = item;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                ResponseConsequence.seeThatResponse("Los valores optenidos de la respuesta de la api existe",
                        response -> response
                                .assertThat()
                                .and().body(nombre, Matchers.equalTo(item)))
        );
        return true;
    }

    public static ValidarRetornoDatos expected(String nombre, String item){
        return new ValidarRetornoDatos(nombre, item);
    }

}
