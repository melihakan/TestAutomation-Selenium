package lastpackage;



import java.sql.Driver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

public class SimpleTest {
    private WebDriver driver;
    final static Logger logger = Logger.getLogger(SimpleTest.class);
    List<WebElement> sonuc;
    JavascriptExecutor js;
    @Test(priority=1)
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver","C:\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.gittigidiyor.com");
        //String url = "https://www.gittigidiyor.com";
        logger.info("Test" + driver.getTitle());
        Assert.assertEquals( "https://www.gittigidiyor.com/", driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        
    }
    @Test(priority=2)
    public void login() {
        driver.findElement(By.linkText("Sepetim")).click();
        driver.findElement(By.linkText("Giriþ Yap")).click();
        driver.findElement(By.id("L-UserNameField")).sendKeys("deneme_sirket@hotmail.com");
        driver.findElement(By.id("L-PasswordField")).sendKeys("deneme12");
        driver.findElement(By.id("gg-login-enter")).click();
        driver.findElement(By.linkText("anasayfaya")).click();
        
        
        //String actResult = "denemedenemeee";
        
        //sonuc is not defined
        //Assert.assertEquals(actResult, sonuc,"Login Baþarýsýz!");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    
    @Test(priority=3)
    public void checkname()
    {
    	driver.findElement(By.name("k")).sendKeys("bilgisayar");
    	driver.findElement(By.xpath("//button[@type='submit']")).click();
    	
    	WebElement element = driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li[2]/a"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        Assert.assertEquals("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2", driver.getCurrentUrl(),"2. sayfa açýlamadý!");
    	
    	//driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[2]"));
    	//driver.get("https://www.gittigidiyor.com/arama/?k=bilgisayar&v=normal&sf=2");
    	//driver.findElement(By.linkText("2")).click();
    	
    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	//Assert.assertEquals(driver.getCurrentUrl(),"https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
    	//*[@id="best-match-right"]/div[4]
    }
    @Test(priority=3)
    public void randomly()
    {
    	
    	sonuc = driver.findElements(By.xpath("//*[@id=\"best-match-right\"]/div[3]/div[2]/ul"));
    	Random random = new Random();
    	int count = random.nextInt(sonuc.size());
    	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    	sonuc.get(count).click();
    	
    }
    @Test(priority=4)
    public void basket()
    {
    	WebElement elements = driver.findElement(By.xpath("//*[@class='IncNumber gg-icon gg-icon-plus']"));
        JavascriptExecutor executors = (JavascriptExecutor)driver;
        executors.executeScript("arguments[0].click();", elements);
        
    	WebElement element = driver.findElement(By.xpath("//*[@id='add-to-basket']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        
    	//driver.findElement(By.xpath("//*[@id=\"sp-addbasket-button\"]")).click();
    	//driver.findElement(By.id("sp-addbasket-button")).click();
    	//driver.findElement(By.className("control-button gg-ui-button plr10 gg-ui-btn-default")).click();
    	//driver.findElement(By.xpath("//button[@type='submit']")).click();
        //driver.findElement(By.id("add-to-basket")).click();
    	//driver.findElement(By.xpath("//button[@id='add-to-basket']")).click();
    	//driver.findElement(By.linkText("Sepete Ekle")).click();
        //***********************************************************
      //  String price = driver.findElement(By.id("sp-price-discountPrice")).toString();
       // String newprice = driver.findElement(By.id("product-new-price")).toString();
        
       // Assert.assertEquals(price, newprice, "Eþit deðil!!!");
        //****************************************************************
        driver.findElement(By.linkText("Sepete Git")).click();
        
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        String string = ((WebElement) js.executeScript("return document.getElementsByClassName('btn-delete btn-update-item')[0].click();")).getText();
        Assert.assertEquals("Boþ deðil", "Sepetinizde ürün bulunmamaktadýr.", string);
    
    }

    
    @AfterTest
    public void Control() {
    	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        //driver.quit();
        logger.info("Test tamamlandý.");

    }
    
}
