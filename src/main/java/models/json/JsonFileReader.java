package models.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log
public class JsonFileReader {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static <T> T readItemFromJsonFile(final String fileName, final Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(JsonFileReader.class.getClassLoader().getResource(fileName), clazz);
        } catch (IOException e) {
            String message = "Exception raised while file reading: " + e.getMessage();
            LOGGER.severe(message);
        }

        throw new IllegalArgumentException("No items have been found for the given path and class");
    }

    public static <T> T readItemFromJsonFile(final String fileName, final TypeReference<T> reference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(JsonFileReader.class.getClassLoader().getResource(fileName), reference);
        } catch (IOException e) {
            String message = "Exception raised while file reading: " + e.getMessage();
            LOGGER.severe(message);
        }

        throw new IllegalArgumentException("No items have been found for the given path and class");
    }
}

