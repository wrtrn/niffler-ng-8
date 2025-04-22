package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement submitBtn = $("button[type='submit']");
    private final SelenideElement createNewAccountBtn = $("*[class='form__register']");
    private final SelenideElement formError = $("*[class='form__error']");
    private final SelenideElement header = $("h1[class='header']");

    public MainPage doLogin(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        submitBtn.click();
        return new MainPage();
    }

    public RegisterPage clickCreateNewAccountBtn() {
        createNewAccountBtn.click();
        return new RegisterPage();
    }

    public void checkThatCredentialsInvalid() {
        formError.shouldHave(text("Неверные учетные данные пользователя"));
        header.shouldHave(text("Log in"));
    }
}
