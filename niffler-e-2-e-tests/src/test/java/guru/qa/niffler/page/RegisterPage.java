package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement passwordSubmitInput = $("input[name='passwordSubmit']");
    private final SelenideElement signUpBtn = $("button[type='submit']");
    private final SelenideElement congratulationsText = $("*[class*='form__paragraph_success']");
    private final SelenideElement signInBtn = $("*[class='form_sign-in']");
    private final SelenideElement formError = $("*[class='form__error']");

    public RegisterPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegisterPage setPasswordSubmit(String passwordSubmit) {
        passwordSubmitInput.setValue(passwordSubmit);
        return this;
    }

    public LoginPage submitRegistration() {
        signUpBtn.click();
        return new LoginPage();
    }

    public void checkThatRegisterWasSuccessful() {
        congratulationsText.shouldHave(text("Congratulations! You've registered!"));
        signInBtn.should(visible);
    }

    public void checkThatUsernameAlreadyExists(String username) {
        formError.shouldHave(text("Username `" + username + "` already exists"));
    }

    public void checkThatPasswordsNotEqual() {
        formError.shouldHave(text("Passwords should be equal"));
    }
}
