package tests.ui;

import models.ui.UsersProvider;
import org.junit.BeforeClass;
import tests.TestBase;

public class TestBaseUi extends TestBase {
    @BeforeClass
    public static void beforeAll() {
        UsersProvider.loadUsers("users.json");
    }
}
