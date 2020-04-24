package flipkartValidation.testRunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty","html:target/Destination"}, features="src/main/resources/features",
        glue="stepDefenitions", tags = "@FunctionalTest")
public class testRunnerClass {
}