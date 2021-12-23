package Leroy;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;

public class BasketPage {
//    public BasketPage(WebDriver driver) {
//        super(driver);
//    }

    @Step("Получение суммы корзины")
    public String getBasketPriceText() {
//        webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.querySelector(\"#root > uc-app >" +
//                " uc-container > main > uc-basket\").shadowRoot.querySelector(\"section:nth-child(4) > div > h1\").textContent == \"Корзина\""));
        String basketPriceText = executeJavaScript("return document.querySelector(\"#root >" +
                " uc-app > uc-container > main > uc-basket\").shadowRoot.querySelector(\"section:nth-child(10) > aside > div >" +
                " div > div.summary-information-block__total > span.summary-information-block__total-price\").textContent").toString();
        basketPriceText = basketPriceText.substring(0, basketPriceText.length() - 2).replaceAll("[^0-9/.]", "");
        return basketPriceText;
    }

    @Step("Получение товара из корзины")
    public String getNameBasketItem(String item) throws InterruptedException {
        Thread.sleep(5000);
        String nameBasketItem = null;
        if (item.equals("item1")) {
            nameBasketItem = executeJavaScript("return document.querySelector(\"#root > uc-app >" +
                    " uc-container > main > uc-basket\").shadowRoot.querySelector(\"section:nth-child(10) > section >" +
                    " item-basket-card:nth-child(4)\").shadowRoot.querySelector(\"layout-basket-card > div > a\").textContent").toString();
        }
        if (item.equals("item2")) {
            nameBasketItem = executeJavaScript("return document.querySelector(\"#root > uc-app >" +
                    " uc-container > main > uc-basket\").shadowRoot.querySelector(\"section:nth-child(10) > section >" +
                    " item-basket-card:nth-child(5)\").shadowRoot.querySelector(\"layout-basket-card > div > a\").textContent").toString();
        }
        return nameBasketItem;
    }

    @Step("Нажатие кнопки заказа")
    public OrderPage clickOrderButton() {
//        webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.querySelector(\"#root > uc-app >" +
//                " uc-container > main > uc-basket\").shadowRoot.querySelector(\"section:nth-child(4) > div > h1\").textContent == \"Корзина\""));
        executeJavaScript("return document.querySelector(\"#root > uc-app > uc-container > main" +
                " > uc-basket\").shadowRoot.querySelector(\"section:nth-child(12) > aside > div > div > uc-button > button\").click()");
        return page(OrderPage.class);
    }

}
