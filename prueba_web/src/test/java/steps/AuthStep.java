package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.OpencartPage;
import java.util.List;
import java.util.Map;

public class AuthStep {

    private WebDriver driver;
    private OpencartPage opencartPage;

    public AuthStep(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.opencartPage = new OpencartPage(driver);
    }

    @Given("que estoy en la página de registro")
    public void que_estoy_en_la_pagina_de_registro() {
        opencartPage.navigateTo();
        opencartPage.navegateRegisterPage();
    }

    @When("ingreso los datos del formulario válidos")
    public void ingreso_los_datos_del_formulario_validos(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        opencartPage.fillRegisterForm(data.get(0).get("firstName"), data.get(0).get("lastName"), data.get(0).get("email"), data.get(0).get("telephone"), data.get(0).get("password"), data.get(0).get("confirmPassword"));
    }

    @Then("debería ver un mensaje de confirmación de registro exitoso {string}")
    public void deberia_ver_un_mensaje_de_confirmacion_de_registro_exitoso(String text) {
        Assert.assertEquals(text, opencartPage.firstResult(text));
    }

    @Given("que estoy en la página de login")
    public void que_estoy_en_la_pagina_de_login() {
        opencartPage.navigateTo();
        opencartPage.navegateLoginPage();
    }

    @When("ingreso las credenciales correctamente {string} {string}")
    public void ingreso_las_credenciales_correctamente(String usuario, String password) {
        opencartPage.fillLoginForm(usuario, password);
    }

    @Then("debería ver My Account")
    public void deberia_ver_my_account() {
        opencartPage.myAccountTitle();
    }


}
