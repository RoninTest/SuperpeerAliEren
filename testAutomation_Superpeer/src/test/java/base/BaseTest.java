package base;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    @Test
    //Superpeer Başarı Hikayesi videosu açılıyor.
    //Gidilen sayfada Superbeer ' in videosunu aradığımızı doğruluyor.
    public void Step1()  {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Eren\\Desktop\\Alieren\\TESTS\\TestAutomationSuperpeer\\chromedriver.exe");
        driver=new ChromeDriver();

        try {
            System.out.println("1.Test Scenario is Started = "+" Search and Click ="+" Superpeer Başarı Hikayesi");
            driver.get("https://www.youtube.com");
            driver.manage().window().maximize();
            System.out.println(driver.getTitle());


            WebElement searchField=driver.findElement(By.xpath("//div[@id=\"search-input\"]//input[@id='search']"));
            searchField.sendKeys("Superpeer'in kuruluş hikayesi");
            WebElement searchButton=driver.findElement(By.xpath("//button[@id=\"search-icon-legacy\"]"));
            searchButton.click();
            System.out.println("1.Test Scenario is Finished = "+" Search and Click ="+" Superpeer Başarı Hikayesi");
            Thread.sleep(5000);


            WebElement verifyTitle=driver.findElement(By.xpath("//yt-formatted-string[contains(text(),\"Superpeer'in kuruluş hikayesi\")]"));
            String value=verifyTitle.getText();
            Assert.assertEquals(value,"Superpeer'in kuruluş hikayesi");



        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Step-1 failed");
        }

        driver.quit();


    }

    @Test
    //Superpeer başarı hikayesi açılıyor ve URL doğrulanıyor.
    public void Step2()  {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Eren\\Desktop\\Alieren\\TESTS\\TestAutomationSuperpeer\\chromedriver.exe");
        driver=new ChromeDriver();


        System.out.println("2.Test Scenario is Started = " + " url opening as (/watch?v=ZNe-TIiLiEI) and Assert the URL");

        try {

            driver.get("https://www.youtube.com/watch?v=ZNe-TIiLiEI");
            driver.manage().window().maximize();
            System.out.println(driver.getTitle());

            String URL = driver.getCurrentUrl();
            Assert.assertEquals(URL,"https://www.youtube.com/watch?v=ZNe-TIiLiEI");

            WebElement superpeerPlayVideo=driver.findElement(By.xpath("//button[@class=\"ytp-large-play-button ytp-button\"]"));
            superpeerPlayVideo.click();
            System.out.println("2.Test Scenario is Finished = " + " url opening as (/watch?v=ZNe-TIiLiEI) and Assert the URL");

            WebElement superpeerStopVideo=driver.findElement(By.xpath("//button[@class=\"ytp-play-button ytp-button\"]"));
            Thread.sleep(5000);
            superpeerStopVideo.click();


            Thread.sleep(5000);


        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Step-2 failed");
        }
        driver.quit();

    }

    @Test
    //Date-Owner-like number testleri yapılıyor.
    public void Step3() throws InterruptedException {


        System.out.println("Test Scenario 3 = "+"Scroll Down and click a video");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Eren\\Desktop\\Alieren\\TESTS\\TestAutomationSuperpeer\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.youtube.com/watch?v=ZNe-TIiLiEI");
        driver.manage().window().maximize();
        Thread.sleep(3000);



        String value_1="Dec 11, 2020";
        WebElement dateFiled=driver.findElement(By.xpath("//yt-formatted-string[contains(text(),'Dec 11, 2020')]"));
        String dateFiledGet=dateFiled.getText();
        System.out.println("Text= " + dateFiledGet);
        if(dateFiledGet.contentEquals(value_1)){
            System.out.println(value_1 + " = "+dateFiledGet);
        }


        System.out.println("*************************************************");

        String value_2="Fatih Acet";
        WebElement ownerName= driver.findElement(By.xpath("//a[contains(text(),'Fatih Acet')]"));
        String ownerNameGet=ownerName.getText();
        ownerNameGet.contentEquals(value_2);
        System.out.println("Owner Name = " + ownerNameGet);
        if(ownerNameGet.contentEquals(value_2)){
            System.out.println(value_2+" = "+ownerNameGet);
        }

        System.out.println("*************************************************");

        int value_3=100;
        WebElement likeNumber=driver.findElement(By.xpath("//yt-formatted-string[contains(text(),'333')]"));
        String likeNumberGet=likeNumber.getText();
        int likeNumberInt=Integer.parseInt(likeNumberGet);
        System.out.println("Like Number = "+likeNumberGet);
        if( likeNumberInt > value_3 ){
            System.out.println("Above 300");
        }

        driver.quit();

    }

    @Test
    //Video durduruluyor ve herhangi ilgili superpeer videosu açılıyor.
    public void Step4()  throws InterruptedException {
        System.out.println("Test Scenario 3 = "+"Scroll Down and click a video");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Eren\\Desktop\\Alieren\\TESTS\\TestAutomationSuperpeer\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.youtube.com/watch?v=ZNe-TIiLiEI");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(6000);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");

        WebElement superOtherVideo_1= driver.findElement(By.cssSelector("a[href*='/watch?v=oTOd11PEJtE']"));
        superOtherVideo_1.click();

        /*
        WebElement superOtherVideo_2=driver.findElement(By.cssSelector("a[href*='/watch?v=v8bA5OgS-2k']"));
        WebElement superOtherVideo_2=driver.findElement(By.cssSelector("a[href*='/watch?v=3sTodWCRR2U']"));
        WebElement superOtherVideo_3=driver.findElement(By.cssSelector("a[href*='/watch?v=TZcpvMGMOok']"));

        */


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement skipTrail=driver.findElement(By.xpath("//button[@class=\"ytp-ad-skip-button ytp-button\"]"));
        if(skipTrail.isDisplayed()){
            skipTrail.click();
            driver.quit();
        }else{
            skipTrail.click();
            Thread.sleep(10000);
            driver.quit();

        }


    }

    public static void main (String args []) throws InterruptedException {
        BaseTest test=new BaseTest();

        test.Step1(); // open youtube and Search the video

        test.Step2(); // open URL and stop video

        test.Step3(); // Date-Owner-More Than 100

        test.Step4(); // ScrollDown and Open a Video

    }

}
