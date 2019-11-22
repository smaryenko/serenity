package tests;

import org.junit.BeforeClass;
import utils.properties.PropertiesLoader;

public class TestBase {

    @BeforeClass
    public static void beforeAll() {
        PropertiesLoader.loadSystemProperties();
        PropertiesLoader.loadEnvProperties();
    }
}
