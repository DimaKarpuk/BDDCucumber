package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class ClavaGonkiStep {
    private final SelenideElement
            closeWindowButton = $x("//input[@value='Закрыть']"),
            startGameButton = $x("//a[@id='host_start']"),
            wordFocus = $x("//span[@id='typefocus']"),
            inputText = $x("//input[@id='inputtext']"),
            afterFocus = $x("//span[@id='afterfocus']"),
            resultText = $x("//td[text()='Это вы']//ancestor-or-self::div//div[@class='stats']//div[2]/span/span");

    private String getCurrentWord() {
        return wordFocus.getText().replaceAll("c", "с").replaceAll("o", "о");
    }

    @Given("Open webSite {string}")
    public void open(String url) {
        Configuration.timeout = 60000;
        Selenide.open(url);
    }

    @When("Start game")
    public void startGame() {
        closeWindowButton.click();
        if (startGameButton.isDisplayed()) {
            startGameButton.click();
        }
    }

    @And("Waiting start game")
    public void waitStartGame() {
        wordFocus.click();
    }

    @And("Enter the highlighted word in a loop")
    public void inputFocusWord() {
        while (true) {
            String text = getCurrentWord();
            String afterFocusSymbol = afterFocus.getText();
            inputText.setValue(text);
            if (afterFocusSymbol.equals(".")) {
                inputText.sendKeys(".");
                break;
            }
            inputText.sendKeys(Keys.SPACE);
        }
    }

    @Then("Fix that game is over symbol more {int}")
    public void fixEndGame(int minValue) {
        String getResult =  resultText.getText();
        int myResult = Integer.parseInt(getResult);
        Assertions.assertTrue(myResult>minValue, "Actual result " + myResult);
    }
}
