package com.example.EventPlanner.AcceptanceTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(features = "cases",
        plugin = {"html:target/cucumber/wikipedia.html"},
        monochrome=true,
        snippets = SnippetType.CAMELCASE,
        glue = {"com.example.Sayartek.AcceptanceTest"}
)

public class acceptanceTest {



}
