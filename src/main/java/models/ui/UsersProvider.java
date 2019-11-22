package models.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import models.json.JsonFileReader;
import utils.exceptions.TestErrorException;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersProvider {
    private static Map<String, User> users;

    public static void loadUsers(String filename) {
        TypeReference<Map<String, User>> typeReference = new TypeReference<Map<String, User>>() {
        };
        users = JsonFileReader.readItemFromJsonFile(filename, typeReference);
    }

    public static User getUser(String userType) {
        User user = users.get(userType);
        if (user == null) {
            throw new TestErrorException(userType + " user was not found in configuration");
        }

        return user;
    }
}
