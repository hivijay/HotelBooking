package stepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" }, monochrome = true,
        features = "src/test/resource/",
        glue = {"stepDefinitions"}
)
public class RunnerClass {
}
