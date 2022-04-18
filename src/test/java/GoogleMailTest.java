import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.annotation.Repeatable;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoogleMailTest {
  private WebDriver driver;
  private StartPage startPage;
  private MailPage mailPage;

    String mail = "iqwidan@gmail.com";
    String password = "7O1135qwe";
    String filename = "C:\\Users\\- H P -\\Desktop\\Эскобар.jpg";
    static double themeRandom = Math.random();
    String themeName = Double.toString(themeRandom);

  @Before
  public void setUp(){
    System.setProperty("webdriver.chrome.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\chromedriver.exe");
    driver = new ChromeDriver();
   //System.setProperty("webdriver.gecko.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\geckodriver.exe");
   //driver = new FirefoxDriver();
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
   driver.manage().window().maximize();
   driver.get("https://www.google.com/intl/ru/gmail/about/");

   startPage = new StartPage(driver);
   LoginPage loginPage = startPage.clickEnter();
   loginPage.typeEmail(mail);
   loginPage.nextClick();
   loginPage.typePassword(password);
   mailPage = loginPage.secondNextClick();
  }

  @Test
    public void firstTest(){
    mailPage.writeMail();
    mailPage.attachFile(filename);
    mailPage.writeToWhom(mail);
    mailPage.writeTheme(themeName);
    mailPage.sendMail();
    String findTheme = mailPage.findThemeName(themeName);
    //String findTheme = mailPage.findLastElement();
    Assert.assertEquals(themeName,findTheme);
  }

  @Test
  public void secondTest(){
    mailPage.clickOnLastMail(themeName);
    boolean attach = mailPage.findAttachment();
    Assert.assertTrue(attach);
  }

  @After
  public void setDown(){
    driver.quit();
  }

}
