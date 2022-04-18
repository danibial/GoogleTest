import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,10);
    }

    private By emailField = By.xpath("//input[@id='identifierId']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By nextButton = By.xpath("//span[text()='Далее']");

    public LoginPage typeEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage nextClick(){
        driver.findElement(nextButton).click();
        return this;
    }

    public MailPage secondNextClick(){
        driver.findElement(nextButton).click();
        return new MailPage(driver);
    }

    public DiskPage clickToDisk(){
        driver.findElement(nextButton).click();
        return new DiskPage(driver);
    }

}
