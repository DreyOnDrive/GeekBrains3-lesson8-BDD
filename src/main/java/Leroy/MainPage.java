package Leroy;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    @Step("Нажатие кнопки каталога")
    public MainPage clickCatalogue() throws InterruptedException {
//        Подскажите пожалуйста как выставить умное ожидание с результатами JavaScript, аналогичное тому что можно было сделать через такое выражение
//        webDriverWait.until(ExpectedConditions.jsReturnsValue("return document.querySelector(\"a[href^=" +
//                "'/catalogue/']\").textContent == 'Каталог'"));

        Thread.sleep(5000);
        executeJavaScript("document.querySelector(\"a[href^='/catalogue/']\").click()");
        return this;
    }

    @Step("Нажатие кнопки категории")
    public MainPage clickCategory(String categoryXpath) throws InterruptedException {
        if (categoryXpath.equals("//span[.='Хранение']")) {
            executeJavaScript("javascript:window.scrollBy(0,550)");
            Thread.sleep(1000);
        }
        if (categoryXpath.equals("//span[.='Плитка']")) {
            executeJavaScript("javascript:window.scrollBy(0,1100)");
            Thread.sleep(1000);
        }
        SelenideElement category = $(By.xpath(categoryXpath));
        category.click();
        return this;
    }

    private String getItemStartXpath() {
//      Подскажите пожалуйста - не удаётся поймать ошибку, аналогично тому, что делал в предыдущем задании
//        try {
//        SelenideElement startXpath = $(By.xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/section/div[4]"));
        return "//*[@id=\"root\"]/main/div[2]/div[2]/div/section/div[4]";
//        } catch (NoSuchElementException e) {
//            webDriverWaitSmall.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//        SelenideElement startXpath = $(By.xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/section/div[3]"));
//            return "//*[@id=\"root\"]/main/div[2]/div[2]/div/section/div[3]";
//        }
    }

    private final String item1PriceTextXpath = "/section/div[1]/div[1]/div[2]/div/div[1]/p[1]";
    private final String item2PriceTextXpath = "/section/div[1]/div[2]/div[2]/div/div[1]/p[1]";

    @Step("Получение цены товара")
    public String getItemPriceText(String item) {
        SelenideElement itemPriceTextElement = null;
        if (item.equals("item1")) {
            itemPriceTextElement = $(By.xpath(getItemStartXpath() + item1PriceTextXpath));
        }
        if (item.equals("item2")) {
            itemPriceTextElement = $(By.xpath(getItemStartXpath() + item2PriceTextXpath));
        }
        String itemPriceText = itemPriceTextElement.getText().replaceAll("[^0-9/.]", "");
        return itemPriceText;
    }

    private final String item1NameTextXpath = "/section/div[1]/div[1]/div[1]/a/span/span";
    private final String item2NameTextXpath = "/section/div[1]/div[2]/div[1]/a/span/span";

    @Step("Получение названия товара")
    public String getItemNameText(String item) {
        SelenideElement itemNameTextElement = null;
        if (item.equals("item1")) {
            itemNameTextElement = $(By.xpath(getItemStartXpath() + item1NameTextXpath));
        }
        if (item.equals("item2")) {
            itemNameTextElement = $(By.xpath(getItemStartXpath() + item2NameTextXpath));
        }
        String itemNameText = itemNameTextElement.getText();
        return itemNameText;
    }

    private final String item1ClickXpath = "/section/div[1]/div[1]/div[2]/button";
    private final String item2ClickXpath = "/section/div[1]/div[2]/div[2]/button";

    @Step("Добавление товара в корзину")
    public MainPage clickItem(String item) throws InterruptedException {
        SelenideElement clickItemElement = null;
        if (item.equals("item1")) {
            clickItemElement = $(By.xpath(getItemStartXpath() + item1ClickXpath));
        }
        if (item.equals("item2")) {
            clickItemElement = $(By.xpath(getItemStartXpath() + item2ClickXpath));
        }
        clickItemElement.click();
        return this;
    }

    private final static String BASKET_XPATH = "//a[@href=\"/basket/\"]";
    private SelenideElement basket = $(By.xpath(BASKET_XPATH));

    @Step("Переход в корзину")
    public BasketPage clickBasket() {
        basket.click();
        return page(BasketPage.class);
    }
}
