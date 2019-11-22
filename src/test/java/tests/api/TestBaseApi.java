package tests.api;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.SystemEnvironmentVariables;
import tests.TestBase;

public class TestBaseApi extends TestBase {
    public static String baseUrl = EnvironmentSpecificConfiguration.from(
            SystemEnvironmentVariables.createEnvironmentVariables())
            .getProperty("restapi.base.url");
}
