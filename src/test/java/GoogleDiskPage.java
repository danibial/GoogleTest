import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GoogleDiskPage {
    private WebDriver driver;
    private StartPage startPage;
    private DiskPage diskPage;

    String mail = "iqwidan@gmail.com";
    String password = "7O1135qwe";
    String filename = "C:\\Users\\- H P -\\Desktop\\Эскобар.jpg";

    @Before
    public void setUp(){
        //System.setProperty("webdriver.chrome.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\chromedriver.exe");
        //driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/intl/ru/drive/");

        startPage = new StartPage(driver);
        LoginPage loginPage = startPage.clickDiskEnter();
        loginPage.typeEmail(mail);
        loginPage.nextClick();
        loginPage.typePassword(password);
        diskPage = loginPage.clickToDisk();
        boolean files = diskPage.findFiles();
        if(files==false){
            diskPage.deleteFiles();
            diskPage.refresh();
            diskPage.findFiles();
        }
    }

    @Test
    public void firstTest() throws AWTException {
        diskPage.clickCreateButton();
        diskPage.upload(filename);
        diskPage.deleteFiles();
        boolean files = diskPage.findFiles();
        if(files==true){
            diskPage.clickTrash();
        }
        diskPage.clearTrashClick();
        diskPage.refresh();
        diskPage.getDiskSize();
        diskPage.myDiskButtonClick();
        diskPage.clickCreateButton();
        diskPage.upload(filename);
        boolean checkFiles = diskPage.findFiles();
        Assert.assertFalse(checkFiles);
    }
}
