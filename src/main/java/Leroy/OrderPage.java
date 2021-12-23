package Leroy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class OrderPage {
//    public OrderPage(WebDriver driver) {
//        super(driver);
//    }

    public OrderPage clickSubmitOrder(){
//        webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.querySelector(\"#root > uc-app > uc-container >" +
//                " main > uc-checkout\").shadowRoot.querySelector(\"layout-checkout > h2\").textContent == 'Оформление заказа'"));
        executeJavaScript("document.querySelector(\"#root > uc-app > uc-container > main > uc-checkout\")" +
                ".shadowRoot.querySelector(\"layout-checkout > checkout-totals > uc-button > button\").click()");
        return this;
    }

    public boolean checkOrderAvailability (){
       boolean checkOrderAvailability = (Boolean) executeJavaScript("return document.querySelector(\"#root > uc-app >" +
                " uc-container > main > uc-checkout\").shadowRoot.querySelector(\"layout-checkout > checkout-totals > uc-button > button\").disabled");
       return checkOrderAvailability;
    }


}
