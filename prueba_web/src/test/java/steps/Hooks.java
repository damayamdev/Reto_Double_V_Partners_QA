package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.OpencartPage;

public class Hooks {
    private WebDriver driver;
    private OpencartPage opencartPage;

    @Before
    public void setUp() {
        driver = BasePage.createDriver(); // Crear instancia del WebDriver
        opencartPage = new OpencartPage(driver); // Pasar WebDriver a la página
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("El escenario falló, adjuntando captura de pantalla.");
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Captura de Error");
        }

        if (driver != null) {
            driver.quit(); // Cerrar el navegador después de cada prueba
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
