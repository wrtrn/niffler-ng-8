package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    private static final String PROFILE_PAGE_URL = "/profile";

    private final SelenideElement showArchivedSwitcher = $(".PrivateSwitchBase-input");
    private final ElementsCollection categoriesList = $$(".MuiChip-label");

    public ProfilePage checkThatCategoryIsInList(String categoryName) {
        categoriesList.findBy(exactText(categoryName)).shouldBe(visible);
        return this;
    }

    public ProfilePage checkThatCategoryIsNotInList(String categoryName) {
        categoriesList.filterBy(exactText(categoryName)).shouldHave(size(0));
        return this;
    }

    public ProfilePage clickArchivedSwitcher() {
        showArchivedSwitcher.click();
        return this;
    }
}
