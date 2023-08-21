package defenitions;

import java.io.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import pageObject.RegisterPage;
//import utilities.ScreenShot;
import utilities.StepDetails;
import utilities.TestSetUp;

public class Hooks {
	
	public WebDriver driver;
	
	public TestSetUp testSetUp;
	
	public Hooks(TestSetUp testSetUp) {
		this.testSetUp = testSetUp;
		
	}
	
	@Before
	public void startUp(Scenario scenario) throws IOException {
		
		driver=testSetUp.driverSetUp.WebDriverManager();
		System.out.println("**************************************************\n");
		System.out.println(scenario.getName()+" started");	
		System.out.println("**************************************************\n");
	}
	
	@AfterStep
	public void takeScreenshot(Scenario scenario) {
		
		RegisterPage registerPage=testSetUp.pageObjectManager.getRegisterPage();
		try {
			if(scenario.isFailed()) {
				String screenshotName = StepDetails.stepName.replace(" ", "_");
				System.out.println(screenshotName+" ,"+registerPage.getByteScreenshot().size());
				for(byte[] b:registerPage.getByteScreenshot()) {
					scenario.attach(b, "image/png", screenshotName);	
					Allure.addAttachment(screenshotName, new ByteArrayInputStream(b));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown(Scenario scenario) throws IOException {
		
		System.out.println("**************************************************\n");
		System.out.println(scenario.getName()+" completed");
		System.out.println("**************************************************\n");
		testSetUp.driverSetUp.WebDriverManager().quit();
	}

}