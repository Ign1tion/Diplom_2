import model.User;
import org.junit.After;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static steps.UserSteps.deleteUser;

public class BaseTest {
    protected static final String EMAIL = "burgerenjoyer@bun.com";
    protected static final String PASSWORD = "ANGUS_TERIYAKI";
    protected static final String NAME = "John";
    protected static String TOKEN;
    protected static String EDITED_EMAIL = "burgerenjoyer@bun.sesame";
    protected static String EDITED_NAME = "JohnSausage";
    protected static List<String> INGREDIENTS = Arrays.asList("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f");
    protected static List<String> FAKE_INGREDIENTS = Arrays.asList("wa9vol2yhs6b0ct62rzz0rex", "lin1ztzmeq2s8r9u1ggk8kz9");
    @After
    public void clearData() {
        if (TOKEN != null) {
            deleteUser(TOKEN);
        }
    }

}
