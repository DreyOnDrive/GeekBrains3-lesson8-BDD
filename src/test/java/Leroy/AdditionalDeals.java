package Leroy;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Cucumber.class)
    public class    AdditionalDeals {
    @After
    public void after(){
        Selenide.closeWebDriver();
    }
}


