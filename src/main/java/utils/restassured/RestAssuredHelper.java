package utils.restassured;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import utils.logger.RestAssuredLogger;

import java.util.Arrays;

public class RestAssuredHelper {
    private static final HeaderConfig headerConfig = new HeaderConfig()
            .overwriteHeadersWithName("Content-Type", "Authorization");

    public static RequestSpecification createSerenityRest(String baseUri) {
        RequestSpecification ra = SerenityRest
                .given()
                .config(RestAssured.config().headerConfig(headerConfig))
                .contentType(ContentType.JSON)
                .baseUri(baseUri);

        return ra;
    }
}
