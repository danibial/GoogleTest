import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class DiskPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DiskPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,15);
    }

    private By file = By.xpath("//div[@role='gridcell']");
    private By trash = By.xpath("//span[@data-tooltip='Удаленные файлы и папки']");
    private By createButton = By.xpath("//button[@aria-label='Создать' and @aria-disabled='false']");
    private By uploadFile = By.xpath("//div[@aria-label='Загрузить файлы']/ancestor::div[@role='menuitem']");
    private By uploadInput = By.xpath("//input[@type='file']");
    private By delite = By.xpath("//div[text()='Удалить']");
    private By clearTrash = By.xpath("//span[text()='Очистить корзину']");
    private By confirmClearTrash = By.xpath("//span[text()='Удалить']");
    private By diskSize = By.xpath("//a[contains(@aria-label,'Статистика использования хранилища')]");
    private By myDiskButton = By.xpath("//span[@aria-label='Мой диск']");

    public boolean findFiles(){
        List<WebElement> files = driver.findElements(file);
        if (files.size()==0){
            System.out.println("Файлов нет!");
            return true;
        }else{
            System.out.println("Файлы есть!");
            return false;
        }
    }

    public DiskPage deleteFiles(){
        List<WebElement> files = driver.findElements(file);
        Actions actions = new Actions(driver);
        for (WebElement element:files){
            actions.contextClick(element).build().perform();
            driver.findElement(delite).click();
        }
        return this;
    }

    public DiskPage deleteFilesDragAndDrop(){
        List<WebElement> files = driver.findElements(file);
        Actions actions = new Actions(driver);
        for (WebElement element:files){
           actions.dragAndDrop(element, driver.findElement(trash)).build().perform();
        }
        return this;
    }
    public DiskPage refresh(){
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(file)));
        driver.navigate().refresh();
        return this;
    }
    public DiskPage clickCreateButton(){
        driver.findElement(createButton).click();
        return this;
    }
    public DiskPage upload(String fileName) throws AWTException {
        driver.findElement(uploadFile).click();
        try{Thread.sleep(2000);}
        catch (InterruptedException i){}
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ESCAPE);
        r.keyRelease(KeyEvent.VK_ESCAPE);
        driver.findElement(uploadInput).sendKeys(fileName);
        return this;
    }

    public DiskPage clickTrash(){
        driver.findElement(trash).click();
        return this;
    }

    public DiskPage clearTrashClick(){
        wait.until(ExpectedConditions.elementToBeClickable(clearTrash));
        driver.findElement(clearTrash).click();
        driver.findElement(confirmClearTrash).click();
        return this;
    }

    public boolean getDiskSize(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(diskSize));
        if(driver.findElement(diskSize).getText().contains("Использовано 0 байт")){
            System.out.println("Диск пустой!");
            return true;
        }else{
            System.out.println("На диске есть файлы!");
            return false;
        }
    }

    public DiskPage myDiskButtonClick(){
        driver.findElement(myDiskButton).click();
        return this;
    }
}
