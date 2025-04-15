package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        String username = "duck";
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .doLogin(username, password)
                .checkThatStatisticsAndHistoryElementsVisible();
    }

    @Test
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
        String username = RandomStringUtils.randomAlphanumeric(5);
        String password = RandomStringUtils.randomAlphanumeric(5);

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .doLogin(username, password);

        new LoginPage().checkThatCredentialsInvalid();

    }
}
