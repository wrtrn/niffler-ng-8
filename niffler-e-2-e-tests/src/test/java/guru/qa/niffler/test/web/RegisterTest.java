package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class RegisterTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void shouldRegisterNewUser() {
        String username = RandomStringUtils.randomAlphanumeric(5);
        String password = RandomStringUtils.randomAlphanumeric(5);

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .clickCreateNewAccountBtn()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration();

        new RegisterPage().checkThatRegisterWasSuccessful();
    }

    @Test
    void shouldNotRegisterUserWithExistingUsername() {
        String existingUsername = "duck";
        String password = RandomStringUtils.randomAlphanumeric(5);

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .clickCreateNewAccountBtn()
                .setUsername(existingUsername)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration();

        new RegisterPage().checkThatUsernameAlreadyExists(existingUsername);
    }

    @Test
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        String existingUsername = RandomStringUtils.randomAlphanumeric(5);
        String password = RandomStringUtils.randomAlphanumeric(5);
        String passwordSubmit = RandomStringUtils.randomAlphanumeric(5);

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .clickCreateNewAccountBtn()
                .setUsername(existingUsername)
                .setPassword(password)
                .setPasswordSubmit(passwordSubmit)
                .submitRegistration();

        new RegisterPage().checkThatPasswordsNotEqual();
    }
}
