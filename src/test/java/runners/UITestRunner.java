package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-html-report",
        "json:target/cucumber.json",
        "rerun:target/failed.txt"},  //rerun will keep track of failed scenarios under failed.txt
        features="src/test/resources/uiFeatures",
        glue="ui_automation.step_definitions",
        tags={"@reg"},
        dryRun = false,
        monochrome = true
)

public class UITestRunner {

}