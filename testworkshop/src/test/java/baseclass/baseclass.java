package baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import utilities.ExtentManager;
import utilities.XLUtility;




public class baseclass {
	
	    public static WebDriver driver;
	    public static WebElement driver1;
		public static Properties pro;
		public static FileInputStream fis;
		public static String browser;
		public static WebDriverWait wait;
		public static Logger log = Logger.getLogger("devpinoyLogger");
		
	
	@BeforeSuite
	 public void BeforeSuite() {
	  ExtentManager.setExtent();
	 }
	 
	 @AfterSuite
	 public void AfterSuite() {
	  ExtentManager.endReport();
	 }
	 
	 @BeforeMethod
	 public void setup() {
					
		 if (driver == null) {
					try {
						 fis= new FileInputStream("C:\\Users\\BOSU\\eclipse-workspace\\testworkshop\\src\\test\\resources\\config.properties");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					 pro=new Properties();
					try {
						pro.load(fis);
						log.info("Config file loaded !!!");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					String ss=pro.getProperty("browser");
					System.out.println(ss);
					
			
				if(pro.getProperty("browser").equals("chrome")) {
					
					System.setProperty("webdriver.chrome.driver","C:\\Users\\BOSU\\eclipse-workspace\\testworkshop\\src\\test\\resources\\Drivers\\chromedriver.exe");
					driver = new ChromeDriver();
					log.info("launching chrome broswer");
				
				}else if(pro.getProperty("browser").equals("firefox")) {
					
					System.setProperty("webdriver.gecko.driver","C:\\Users\\BOSU\\eclipse-workspace\\testworkshop\\src\\test\\resources\\Drivers\\geckodriver.exe");
					driver = new FirefoxDriver();
					log.info("Open firefox driver !!!");
					
			}
			    
				driver.manage().window().maximize();
				driver.get(pro.getProperty("url"));
				log.info("Hit the URL !!!");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
	 }
	 
	 }
	 public static String screenShot(WebDriver driver,String filename) {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";
		  File finalDestination= new File(destination);
		  try {
		   FileUtils.copyFile(source, finalDestination);
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.getMessage();
		  }
		  return destination;
		 }
		 
		 public static String getCurrentTime() {  
		     String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());  
		     return currentDate;  
		 }  
   
		 @DataProvider(name="LoginData1")
			public String [][] getdata() throws IOException{
				
				String path=".\\src\\test\\java\\testdata\\Billing Address.xlsx";
				XLUtility excel=new XLUtility(path);
				
				int rows=excel.getRowCount("sheet1");
				int cols=excel.getCellCount("sheet1", 1);
				
				String [][] loginData=new String[rows][cols];
						
				for(int i=1;i<=rows;i++) {
					for(int j=0;j<cols;j++) {
						
						loginData[i - 1][j]=excel.getCellData("sheet1", i, j);
					}
					
				}
				
				return loginData;		
			}
			

	
		@AfterSuite
		 public void tearDown() throws IOException {
			 if(driver!= null) {
					driver.quit();
					driver.close();
					}
		 } 
}
