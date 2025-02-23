package co.com.certificacion.api.runners;



import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;


@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/",
        tags = "@endpointProductos",
        glue = {
                "co.com.certificacion.api.stepdefinitions.hook",
                "co.com.certificacion.api.stepdefinitions"
        },
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
        publish = true
)
public class Runner {

}
