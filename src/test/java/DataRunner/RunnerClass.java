package DataRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
				features="DataFeature", 
				glue="DataStepDefinition",
				plugin = {"pretty", "html:target/cucumber-report/cucumber.html",
		                "json:target/cucumber-report/cucumber.json",
		                "junit:target/cucumber-report/cucumber.xml"})
public class RunnerClass {
	

}
