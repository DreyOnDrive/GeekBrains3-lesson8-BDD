package Leroy;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

//    public LoginPage(WebDriver driver) {
//        super(driver);
//    }

    private final static String LOGIN_FRAME_LOCATOR_BY_ID = "oauth-iframe";
    private SelenideElement loginFrame = $(By.id(LOGIN_FRAME_LOCATOR_BY_ID));
    @Step("Переключиться в логин фрейм")
    public LoginPage switchToLoginFrame() {
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_FRAME_LOCATOR_BY_ID)));
        switchTo().frame(loginFrame);
        return this;
    }

    private final static String LOGIN_INPUT_BY_XPATH = "//input[@id=\"username\"]";
    private SelenideElement loginInput = $(By.xpath(LOGIN_INPUT_BY_XPATH));
    @Step("Заполнить логин")
    public LoginPage fillLoginInput(String login) {
        loginInput.sendKeys(login);
        return this;
    }

    private final static String PASSWORD_INPUT_BY_XPATH = "//input[@id=\"password\"]";

    private SelenideElement passwordInput = $(By.xpath(PASSWORD_INPUT_BY_XPATH));
    @Step("Заполнить пароль")
    public LoginPage fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    private final static String SUBMIT_LOGIN_BUTTON_BY_XPATH = "//input[@value=\"Войти\"]";

    private SelenideElement submitLoginButton = $(By.xpath(SUBMIT_LOGIN_BUTTON_BY_XPATH));
    @Step("Нажать кнопку входа")
    public MainPage clickSubmitLoginButton () {
        submitLoginButton.click();
        return page(MainPage.class);
    }



}
