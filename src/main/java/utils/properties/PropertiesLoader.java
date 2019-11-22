package utils.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.exceptions.ResourceReadFailedException;

import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesLoader {

    public static void loadThreadProperties(Class loaderClass, String path) {
        try (final InputStream stream = loaderClass.getClassLoader().getResourceAsStream(path)) {
            PropertiesSupplier.load(stream);
        } catch (IOException e) {
            throw new ResourceReadFailedException("Problem occurred during reading properties file", e);
        }
    }

    public static void loadGlobalProperties(Class loaderClass, String path) {
        try (final InputStream stream = loaderClass.getClassLoader().getResourceAsStream(path)) {
            PropertiesSupplier.loadGlobalProperties(stream);
        } catch (IOException e) {
            throw new ResourceReadFailedException("Problem occurred during reading properties file", e);
        }
    }

    public static void loadSystemProperties() {
        PropertiesSupplier.loadSystemProperties();
    }

    public static void loadEnvProperties() {
        PropertiesSupplier.loadEnvProperties();
    }
}
