package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.OpencartPage;

public class BuyStep {

    OpencartPage opencartPage = new OpencartPage();

    @Given("que Daniel inició sesión en la página Opencart {string} {string}")
    public void que_daniel_inicio_sesion_en_la_pagina_opencart(String usuario, String password) {
        opencartPage.navigateTo();
        opencartPage.navegateLoginPage();
        opencartPage.fillLoginForm(usuario, password);
    }
    @When("selecciona un portátil {string} de la lista")
    public void selecciona_un_portatil(String productName) {
        opencartPage.navigateToLaptopsNotebookPage(productName);
    }
    @When("desde la barra de búsqueda selecciona el producto {string}")
    public void desde_la_barra_de_busqueda_selecciona_el_producto(String productName) {
        opencartPage.navigateToTabletPage(productName);
    }
    @When("desde el carrito elimina el producto {string}")
    public void desde_el_carrito_elimina_el_producto(String nameProduct) {
        opencartPage.deleteProduct();
    }
    @When("agrega más cantidad al producto restante finalizando la compra")
    public void agrega_mas_cantidad_al_producto_restante_finalizando_la_compra() {
        opencartPage.endCart();
        opencartPage.billingDetails();
    }
    @Then("debería ver el número del pedido")
    public void deberia_ver_el_numero_del_pedido() {
        Assert.assertEquals("Your order has been placed!", opencartPage.orderResult());
    }
}
