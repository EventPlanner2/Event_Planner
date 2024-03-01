package applicationTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources",
        plugin = {"summary" ,"html:target/cucumber/wikipedia.html"},
        monochrome=true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"applicationTest/steps"}
)
public class acceptanceTest {

}
