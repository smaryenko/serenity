package utils.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.exceptions.ResourceReadFailedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesSupplier {
    private static final String PROPERTY_NOT_FOUND_ERR_MSG = "Property %s was not found in system properties";
    private static Properties globalProperties;
    private static ThreadLocal<Properties> threadProperties = new ThreadLocal<>();

    public static void load(InputStream stream) {
        try {
            getThreadProperties().load(stream);
        } catch (IOException e) {
            throw new ResourceReadFailedException("Problem occurred during reading properties file", e);
        }
    }

    public static synchronized void loadGlobalProperties(InputStream stream) {
        try {
            getGlobalProperties().load(stream);
        } catch (IOException e) {
            throw new ResourceReadFailedException("Problem occurred during reading properties file", e);
        }
    }

    public static Integer getPropertyAsInteger(final String propertyName, final Integer defaultValue) {
        String value = get(propertyName);
        if (value != null) {
            return Integer.valueOf(value);
        } else {
            return defaultValue;
        }
    }

    public static Integer getPropertyAsInteger(final String propertyName) {
        String value = get(propertyName);
        if (value != null) {
            return Integer.valueOf(value);
        } else {
            throw new ResourceReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public static Boolean getPropertyAsBoolean(final String propertyName, final Boolean defaultValue) {
        String value = get(propertyName);
        if (value != null) {
            return Boolean.valueOf(value);
        } else {
            return defaultValue;
        }
    }

    public static Boolean getPropertyAsBoolean(final String propertyName) {
        String value = get(propertyName);
        if (value != null) {
            return Boolean.valueOf(value);
        } else {
            throw new ResourceReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public static String getProperty(final String propertyName, final String defaultValue) {
        String value = get(propertyName);
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public static String getProperty(final String propertyName) {
        String value = get(propertyName);
        if (value != null) {
            return value;
        } else {
            throw new ResourceReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public static String tryGetProperty(final String propertyName) {
        return get(propertyName);
    }

    public static synchronized void setThreadProperty(String property, String value) {
        getThreadProperties().setProperty(property, value);
    }

    public static synchronized void setGlobalProperty(String property, String value) {
        getGlobalProperties().setProperty(property, value);
    }

    private static synchronized Properties getGlobalProperties() {
        if (Objects.isNull(globalProperties)) {
            globalProperties = new Properties(System.getProperties());
        }
        return globalProperties;
    }

    private static Properties getThreadProperties() {
        if (Objects.isNull(threadProperties.get())) {
            threadProperties.set(new Properties());
        }
        return threadProperties.get();
    }

    private static String get(String key) {
        //Firstly look to the global properties shared between all threads
        String globalProperty = globalProperties.getProperty(key, null);
        if (Objects.nonNull(globalProperty)) {
            return globalProperty;
        }

        //Then look to the system properties (for current Thread)
        String systemProperty = System.getProperties().getProperty(key, null);
        if (Objects.nonNull(systemProperty)) {
            return systemProperty;
        }

        //Otherwise look to the Thread properties
        return getThreadProperties().getProperty(key);
    }

    public static void loadSystemProperties() {
        getGlobalProperties().putAll(System.getProperties());
    }

    public static void loadEnvProperties() {
        getGlobalProperties().putAll(System.getenv());
    }
}
