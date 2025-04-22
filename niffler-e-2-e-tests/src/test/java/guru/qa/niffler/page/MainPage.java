package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final ElementsCollection tableRows = $$("#spendings tbody tr");
    private final SelenideElement historyOfSpendingsElement = $("#spendings");
    private final SelenideElement statisticsElement = $("#stat");

    public HeaderComponent getHeader() {
        return new HeaderComponent();
    }

    public EditSpendingPage editSpending(String spendingDescription) {
        tableRows.find(text(spendingDescription))
                .$$("td")
                .get(5)
                .click();
        return new EditSpendingPage();
    }

    public void checkThatTableContains(String spendingDescription) {
        tableRows.find(text(spendingDescription))
                .should(visible);
    }

    public void checkThatStatisticsAndHistoryElementsVisible() {
        historyOfSpendingsElement.shouldBe(visible);
        statisticsElement.shouldBe(visible);
    }

}
