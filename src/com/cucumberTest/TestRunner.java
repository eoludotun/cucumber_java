package com.cucumberTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature"
		,glue={"stepDefinition"},monochrome = true, plugin = {"pretty", "html:target/cucumber", "rerun:target/rerun.txt"}
		)

public class TestRunner {

}