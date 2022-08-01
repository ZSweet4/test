package com.luckyboy.ui.cucumber;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith( CucumberWithSerenity.class )
@CucumberOptions(
        features = { "src/test/resources/features/ui/Lottery.feature" },
        plugin = { "pretty" }
)
public class SerenityCucumberRunner {
}
