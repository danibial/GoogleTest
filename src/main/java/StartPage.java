import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartPage {
    private WebDriver driver;

    public StartPage(WebDriver driver){
        this.driver = driver;
    }

    private By enterButton = By.xpath("//a[text()='Войти']");
    private By enterDiskButton = By.xpath("(//a[@data-action='go to drive' and @data-label='header'])[1]");

    public LoginPage clickEnter(){
        driver.findElement(enterButton).click();
        return new LoginPage(driver);
    }

    public LoginPage clickDiskEnter(){
        driver.findElement(enterDiskButton).click();
        for (String tab: driver.getWindowHandles()){
            driver.switchTo().window(tab);
        }
        return new LoginPage(driver);
    }
}
