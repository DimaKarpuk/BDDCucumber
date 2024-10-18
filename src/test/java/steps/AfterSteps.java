package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;

public class AfterSteps {
    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    @AfterStep
    public void makeScreenshot(){
        Selenide.screenshot(System.currentTimeMillis() + "step");
    }
}
