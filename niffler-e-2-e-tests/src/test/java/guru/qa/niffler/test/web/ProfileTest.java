package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.HeaderComponent;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private static final Config CFG = Config.getInstance();

    @Category(
            username = "duck",
            archived = true
    )
    @Test
    void archivedCategoryShouldPresentInCategoriesList(CategoryJson category) {

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .doLogin("duck", "12345");

        new HeaderComponent()
                .openProfile()
                .checkThatCategoryIsNotInList(category.name())
                .clickArchivedSwitcher()
                .checkThatCategoryIsInList(category.name());
    }

    @Category(
            username = "duck",
            archived = false
    )
    @Test
    void activeCategoryShouldPresentInCategoriesList(CategoryJson category) {

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .doLogin("duck", "12345");

        new HeaderComponent()
                .openProfile()
                .checkThatCategoryIsInList(category.name());
    }
}
