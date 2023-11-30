import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static steps.UserSteps.createUser;

public class UserTest extends BaseTest {
    @Test
    @DisplayName("Basic user creation")
    public void CreateUserTest(){
       TOKEN = createUser(BaseTest.EMAIL, BaseTest.PASSWORD, BaseTest.NAME)
                .then()
                .extract().path("accessToken");

    }
    @Test
    @DisplayName()

}
