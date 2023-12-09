package ru.StepDefinitions;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Обеспечивает запуск тестов в Cucumber с указанными опциями (см. аннотацию @CucumberOptions).
 * Created by Dima Makieiev on 08.12.2015.
 * Updated by Vladimir V. Klochkov on 08.07.2020.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json", "json:target/cucumber.json"})

public class RunTest {
    //==================================================================================================================
    //     Этот класс всегда должен быть пустым ! Имя класса обязательно должно закачиваться словом Test !
    //==================================================================================================================
}
