package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

    private final SelenideElement avatar = $("[data-testid='PersonIcon']");
    private final SelenideElement profileBtn = $("[href='/profile']");

    public ProfilePage openProfile() {
        avatar.click();
        profileBtn.click();
        return new ProfilePage();
    }
}
