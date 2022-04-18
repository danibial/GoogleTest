import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private int number;

    public MailPage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver,60);
    }

    private By writeButton = By.xpath("//div[text()='Написать']");
    private By attachButton = By.xpath("//input[@type='file']");
    private By toWhomField = By.xpath("//textarea[@aria-label='Кому']");
    private By themeField = By.xpath("//input[@name='subjectbox']");
    private By sendButton = By.xpath("(//div[contains(@aria-label,'Отправить')])[2]");
    private By refreshButton = By.xpath("//div[@title='Обновить']");
    private By themeName = By.xpath("//table[@role='grid']//tr[@role='row']//div[@role='link']//span[contains(@data-thread-id,'#thread')]");
    private By attachment = By.xpath("//a[@target='_blank' and @role='link']");
    private By numberOfMails = By.xpath("//div[@data-tooltip='Входящие']//span/following::div");

    private By addressPlaceHolder = By.xpath("//div[text()='Получатели']");

    /*public void getNumberOfMails(){
        number = Integer.parseInt(driver.findElement(numberOfMails).getText());
    }*/

    public MailPage writeMail(){
       // getNumberOfMails();
        driver.findElement(writeButton).click();
        return this;
    }

    public MailPage attachFile(String filename){
        driver.findElement(attachButton).sendKeys(filename);
     return this;
    }

    public MailPage writeToWhom(String mail){
        if(driver.findElement(addressPlaceHolder).isDisplayed()){
            driver.findElement(toWhomField).click();
        }

        driver.findElement(toWhomField).sendKeys(mail);
        return this;
    }

    public MailPage writeTheme(String theme){
        driver.findElement(themeField).sendKeys(theme);
        return this;
    }

    public MailPage sendMail(){
        driver.findElement(sendButton).click();
        return this;
    }

    public MailPage refresh(){
        driver.navigate().refresh();
        //driver.findElement(refreshButton).click();
        return this;
    }

    public String findThemeName(String theme){
        wait.until(ExpectedConditions.textToBe(By.xpath("(//table[@role='grid']//tr[@role='row']//div[@role='link']//span[contains(@data-thread-id,'#thread')])[1]"),theme));
        String testTheme = null;
        List<WebElement> namesArray = driver.findElements(themeName);
        for(WebElement name:namesArray){
            System.out.println(name.getText());
            if (name.getText().equals(theme)){
                System.out.println(name.getText());
                testTheme=name.getText();
                break;
            }
        }
        return testTheme;
    }

    public String findLastElement(){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(numberOfMails,number));
        String lastMail = driver.findElement(By.xpath("(//table[@role='grid']//tr[@role='row']//div[@role='link']//span[contains(@data-thread-id,'#thread')])[1]")).getText();
        return lastMail;
    }

    public MailPage clickOnLastMail(String theme) {
        List<WebElement> namesArray = driver.findElements(themeName);
        for (WebElement name : namesArray) {
            System.out.println(name.getText());
            if (name.getText().equals(theme)) {
                name.click();
                break;
            }
        }
        return this;
    }

    public boolean findAttachment(){
        return driver.findElement(attachment).isDisplayed();
    }
}
