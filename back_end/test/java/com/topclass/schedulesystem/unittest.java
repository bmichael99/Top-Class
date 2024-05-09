package com.topclass.schedulesystem;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class unittest{
    private WebDriver driver;

    @BeforeAll
    public static void setUpClass() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\micha\\Documents\\chromedriver.exe");

    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }
    @Test
    public void test1() {
        driver.get("http://localhost:3000/home");
        WebElement exploreclasses = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div/a"));
        exploreclasses.click();
        List<WebElement> checkboxes = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[1]/div[2]")).findElements(By.cssSelector("input[type='checkbox']"));
        for (int i=0;i<checkboxes.size()-2;i++){
            checkboxes.get(i).click();
        }


        WebElement submitclasses = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[1]/button"));
        submitclasses.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        List<WebElement> classes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h4[contains(@style, 'cursor: pointer;')]")));
        //System.out.println(classesnottaken.size());
        for (int i=0;i<classes.size();i++){
            classes.get(i).click();
        }

        WebElement classeswrapper=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[2]"));
        List<WebElement> sections=classeswrapper.findElements((By.xpath("./div")));
        for (int i=0;i<sections.size();i++){
            sections.get(i).findElements(By.cssSelector("input[type='checkbox']")).get(0).click();

        }
        //List <WebElement> professors=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[style*='display: inline-block; margin: 0px 10px;']")));

        //System.out.println(professors.size());
    }
    //@AfterEach
    /*public void tearDown() {
        // Close WebDriver after each test method
        if (driver != null) {
            driver.quit();
        }
    }*/
}
