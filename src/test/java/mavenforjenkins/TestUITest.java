package mavenforjenkins;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestUITest {

	@Test
	public static void Review_Candidate() throws InterruptedException {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        Actions actions = new Actions(driver);
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.manage().window().maximize();
			// Navigate to the login page
			driver.navigate().to("https://xdev.recruitbpm.com/users/login");
			
		    // Find the email and password input fields and enter the credentials
			driver.findElement(By.name("identity")).sendKeys("asad@recruitbpm.com");
			driver.findElement(By.id("password")).sendKeys("123456");
			driver.findElement(By.id("submit")).click();
			
			driver.findElement(By.cssSelector("div a.menutoggle")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("li[data-sort='6'] a.activeAncher span i.dropdown-chevron")).click();
			driver.findElement(By.className("candidates_listing")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"table2_wrapper\"]/div[3]/div[3]/div[2]/div/table/tbody/tr[5]/td[2]/a")).click();
			driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/div/div/ul/li[11]/a")).click(); // Review Candidate

	        Thread.sleep(1000);
	        // Rating Skills
	        try {
	        	Thread.sleep(2000);
	            List<WebElement> starRatingElements = driver.findElements(By.className("rating-stars"));

	            // Iterate over each star rating element and simulate hover
	            for (WebElement starElement : starRatingElements) {
	                actions.moveToElement(starElement).perform();
	                

	                Thread.sleep(3000);

	                // Verify if the star is highlighted by checking its CSS class
	                String starClass = starElement.getAttribute("style");
	                System.out.println(starClass);
	                if (starClass.equals("width: 0%;")) {
	                    System.out.println("Star is highlighted on hover!");
	                    actions.moveByOffset(60, 0).click().perform();
	                    break;
	                } else {
	                    System.out.println("Star is not highlighted on hover!");
	                }
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	    }
	        driver.findElement(By.xpath("//*[@placeholder='Note']")).sendKeys("Test NOte"); // Note
	        driver.findElement(By.xpath("//*[@type='submit' and text()='Submit']")).click(); // Submit Button
	        driver.quit();
	    }
	}
