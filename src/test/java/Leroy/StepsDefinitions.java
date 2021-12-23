package Leroy;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.open;

public class StepsDefinitions {

    final public static String URL_LOGIN = "https://leroymerlin.ru/login";
    final public static String LOGIN = "xowapi1@ineedsa.com";
    final public static String PASSWORD = "Aa123456";

    String item1PriceText;
    String item2PriceText;
    String item1NameText;
    String item2NameText;
    String item1 = "item1";
    String item2 = "item2";
//    String category1 = "//span[.='Декор']";
//    String category2 = "//span[.='Хранение']";
//    String category3 = "//span[.='Плитка']";
    @Given("Пользователь авторизован на сайте")
    public void userAuthorization() {
        open(URL_LOGIN);
        new LoginPage()
                .switchToLoginFrame()
                .fillLoginInput(LOGIN)
                .fillPasswordInput(PASSWORD)
                .clickSubmitLoginButton();
    }

    @When("Я кликаю на каталог")
    public void iClickCatalogue() throws InterruptedException {
        new MainPage()
                .clickCatalogue();
    }

    @And("Я кликаю на категорию {string}")
    public void iClickAtCategory(String category) throws InterruptedException {
        String xpath = "//span[.='" + category + "']";
        new MainPage()
                .clickCategory(xpath);
    }


    @And("Я добавляю два товара")
    public void iAddTwoItemsToBasket() throws InterruptedException {
        new MainPage()
                .clickItem(item1)
                .clickItem(item2);
    }

    @And("Я получаю их названия")
    public void iGetItemsNames() {
        MainPage mainPage = new MainPage();
        item1PriceText = mainPage.getItemPriceText(item1);
        item2PriceText = mainPage.getItemPriceText(item2);
    }

    @And("Я получаю их цены")
    public void iGetItemsPrices() throws InterruptedException {
        MainPage mainPage = new MainPage();
        item1NameText = mainPage.getItemNameText(item1);
        item2NameText = mainPage.getItemNameText(item2);
    }

    @And("Кликаю на кнопку корзины")
    public void iClickAtBasket() {
        new MainPage()
                .clickBasket();
    }

    @Then("Два выбранных товара показываются в корзине")
    public void twoItemsShownInBasket() throws InterruptedException {
        BasketPage basketPage = new BasketPage();
        String NameBasketItem1 = basketPage.getNameBasketItem(item1);
        String NameBasketItem2 = basketPage.getNameBasketItem(item2);
//        ассерты пришлось оставить так как их значения получаются как строчки из JavaScript
        Assertions.assertEquals(item1NameText, NameBasketItem1);
        Assertions.assertEquals(item2NameText, NameBasketItem2);
    }

    @Then("Цена корзины соответвует сумме цен товаров")
    public void basketPriceIsEqualsItemsSum() {
        BasketPage basketPage = new BasketPage();
        String basketPriceText = basketPage.getBasketPriceText();
        Double price1 = Double.parseDouble(item1PriceText);
        Double price2 = Double.parseDouble(item2PriceText);
        Double sumPrice = price1 + price2;
        Double basketPrice = Double.parseDouble(basketPriceText);
        Assertions.assertEquals(sumPrice, basketPrice);
    }

}
