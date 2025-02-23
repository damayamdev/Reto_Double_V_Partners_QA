package co.com.certificacion.api.stepdefinitions;

import co.com.certificacion.api.exceptions.AssertionsServices;
import co.com.certificacion.api.models.Product;
import co.com.certificacion.api.questions.*;
import co.com.certificacion.api.tasks.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ProductosStepdefinition {

    @When("realizo una petición GET a {string}")
    public void realizoUnaPeticiónGETA(String endpoint) {
        theActorInTheSpotlight().attemptsTo(
                ListarTodosLosProductos.get(endpoint)
        );
    }

    @Then("debería recibir una respuesta con código {int}")
    public void deberiaRecibirUnaRespuestaConCodigo(Integer codigo) {
        theActorInTheSpotlight()
                .should(seeThat(ValidarRespuestaEstado.expected(codigo))
                        .orComplainWith(AssertionsServices.class,
                                AssertionsServices.NO_SE_ESPERA_LA_RESPUESTA_DE_LOS_SERVICIOS_DE_CÓDIGO_DE_ESTADO)
                );
    }

    @Then("la respuesta debería contener una lista de productos")
    public void laRespuestaDeberíaContenerUnaListaDeProductos() {
        String datos = "[{\"id\":9,\"title\":\"WD 2TB Elements Portable External Hard Drive - USB 3.0 \",\"price\":64,\"description\":\"USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user’s hardware configuration and operating system\",\"category\":\"electronics\",\"image\":\"https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg\",\"rating\":{\"rate\":3.3,\"count\":203}},{\"id\":10,\"title\":\"SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s\",\"price\":109,\"description\":\"Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.)\",\"category\":\"electronics\",\"image\":\"https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg\",\"rating\":{\"rate\":2.9,\"count\":470}},{\"id\":11,\"title\":\"Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5\",\"price\":109,\"description\":\"3D NAND flash are applied to deliver high transfer speeds Remarkable transfer speeds that enable faster bootup and improved overall system performance. The advanced SLC Cache Technology allows performance boost and longer lifespan 7mm slim design suitable for Ultrabooks and Ultra-slim notebooks. Supports TRIM command, Garbage Collection technology, RAID, and ECC (Error Checking & Correction) to provide the optimized performance and enhanced reliability.\",\"category\":\"electronics\",\"image\":\"https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg\",\"rating\":{\"rate\":4.8,\"count\":319}},{\"id\":12,\"title\":\"WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive\",\"price\":114,\"description\":\"Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty\",\"category\":\"electronics\",\"image\":\"https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg\",\"rating\":{\"rate\":4.8,\"count\":400}},{\"id\":13,\"title\":\"Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin\",\"price\":599,\"description\":\"21. 5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz\",\"category\":\"electronics\",\"image\":\"https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg\",\"rating\":{\"rate\":2.9,\"count\":250}},{\"id\":14,\"title\":\"Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED \",\"price\":999.99,\"description\":\"49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR with dual 27 inch screen side by side QUANTUM DOT (QLED) TECHNOLOGY, HDR support and factory calibration provides stunningly realistic and accurate color and contrast 144HZ HIGH REFRESH RATE and 1ms ultra fast response time work to eliminate motion blur, ghosting, and reduce input lag\",\"category\":\"electronics\",\"image\":\"https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg\",\"rating\":{\"rate\":2.2,\"count\":140}}]";
        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarDatos.items(datos))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );
    }

    @Then("todos los productos en la lista deberían tener la categoría {string}")
    public void todosLosProductosEnLaListaDeberíanTenerLaCategoría(String categoria) {
        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarCategoria.go(categoria))
        );

    }

    @When("realizo una petición GETID a {string}")
    public void realizoUnaPeticionGETIDA(String idProducto) {
       OnStage.theActorInTheSpotlight().attemptsTo(
               ListarProductoXId.get(idProducto)
       );
    }

    @Then("la respuesta debería contener los detalles del producto con ID {int}")
    public void laRespuestaDeberiaContenerLosDetallesDelProductoConID(Integer idProducto) {
        String detalle = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday";
        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarRetornoDatos.expected("description", detalle))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );
    }

    @When("realizo una petición POST a {string} con los siguientes datos:")
    public void realizoUnaPeticionPOSTAConLosSiguientesDatos(String endpoint, DataTable dataTable) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CrearProducto.crear(endpoint, Product.setData(dataTable))
        );
    }

    @Then("la respuesta debería contener los detalles del producto creado")
    public void laRespuestaDeberiaContenerLosDetallesDelProductoCreado(DataTable dataTable) {
        List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);

        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarRetornoDatos.expected("category", filas.get(0).get("category")))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );

        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarRetornoDatos.expected("title", filas.get(0).get("title")))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );

        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarRetornoDatos.expected("description", filas.get(0).get("description")))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );

        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarRetornoDatos.expected("image", filas.get(0).get("image")))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );

    }

    @Then("el producto debería tener un ID asignado")
    public void elProductoDeberiaTenerUnIDAsignado() {
        theActorInTheSpotlight()
                .should(seeThat(
                        ValidarSchema.expected("POSTProduct"))
                        .orComplainWith(AssertionsServices.class,
                                AssertionsServices.EL_ESQUEMA_DE_LA_RESPUESTA_NO_ES_EL_CORRECTO)
                );
    }

    @When("realizo una petición PATCH a {string} con los siguientes datos:")
    public void realizoUnaPeticionPATCHAConLosSiguientesDatos(String endpoint, DataTable dataTable) {
        List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                ActualizarImagen.actualizarImagen(endpoint, filas.get(0).get("imagen"))
        );

    }
    @Then("la imagen del producto debería ser {string}")
    public void laImagenDelProductoDeberiaSer(String imagen) {
        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarRetornoDatos.expected("image", imagen))
                .orComplainWith(AssertionsServices.class,
                        AssertionsServices.LOS_DATOS_DE_LA_RESPUESTA_NO_SON_LOS_CORRECTOS)
        );
    }

    @Given("que consulto todos los productos con la petición GET a {string}")
    public void queConsultoTodosLosProductosConLaPeticiónGETA(String endpoint) {
        theActorInTheSpotlight().wasAbleTo(
                ListarTodosLosProductos.get(endpoint)
        );
    }

    @When("para cada producto filtrado realizo una petición DELETE a {string}")
    public void paraCadaProductoFiltradoRealizoUnaPeticiónDELETEA(String endpoint) {
        theActorInTheSpotlight().attemptsTo(
                EliminarPorCondicion.eliminar(endpoint)
        );
    }

    @Then("todas las respuestas DELETE deberían tener código {int}")
    public void todasLasRespuestasDELETEDeberíanTenerCódigo(Integer codigo) {
        theActorInTheSpotlight()
                .should(seeThat(ValidarRespuestaEstado.expected(codigo))
                        .orComplainWith(AssertionsServices.class,
                                AssertionsServices.NO_SE_ESPERA_LA_RESPUESTA_DE_LOS_SERVICIOS_DE_CÓDIGO_DE_ESTADO)
                );
    }


}
