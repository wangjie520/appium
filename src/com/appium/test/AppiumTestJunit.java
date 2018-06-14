package com.appium.test;


import static org.junit.Assert.*;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumTestJunit {
	AndroidDriver driver;
	
	@Before
	public void setUp() throws Exception {

		DesiredCapabilities cap=new DesiredCapabilities();
		//设备 adb devices查看
		cap.setCapability("deviceName","127.0.0.1:62001");
		//手机系统版本
		cap.setCapability("platformVersion", "5.0");
		//测试引擎，默认Appium,API-level<17的情况下设置为selendriod
		cap.setCapability("automationName", "Appium");
		//必填，测试平台，iOS，Android，Firefox OS
		cap.setCapability("platformName", "Android");
		//apk文件路径，appium运行时会先尝试安装再开始测试,如果没有的话，会根据appActivity和appPackage去目标机器查找目标app
		//cap.setCapability("app", "C:\\Users\\Administrator\\Desktop\\apks\\vcm-release-2.6.0(6).apk");
		//安卓必填 被测应用程序的包名
		cap.setCapability("appPackage", "com.checheyun.vcm");
		//被测应用程序启动的Activity的名称
		cap.setCapability("appActivity", ".MainActivity");
		//appium服务端超过设置的时间没有收到消息时认为客户端退出，默认60
		cap.setCapability("newCommandTimeout", 60);
		//等待测试设备ready的超时时间
		cap.setCapability("devicereadyTimeout", 30);
		//是否启用支持unicode的键盘
		cap.setCapability("unicodeKeyboard", true);
		//session结束后是否重置键盘
		cap.setCapability("resetKeyboard", true);
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		//设置全局隐式等待30S
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test001() throws InterruptedException {
		WebElement username=driver.findElementByXPath("//android.widget.EditText[@text=\"请输入手机号\"]");
		//WebElement username=driver.findElementByXPath("//android.widget.ScrollView/android.view.View/android.view.View[1]/android.widget.EditText");
		username.sendKeys("18581540581");
		WebElement passwd=driver.findElementByXPath("//android.widget.ScrollView/android.view.View/android.view.View[2]/android.widget.EditText");
		passwd.sendKeys("123456");
		driver.findElementByAndroidUIAutomator("new UiSelector().description(\"login_btn\")").click();
		//driver.findElementByAccessibilityId("login_btn").click();
		Thread.sleep(8000);
		WebElement me=driver.findElementByXPath("//*[contains(@content-desc,\"bottom_icon\")]/android.view.View[5]/android.widget.TextView");
		String me_cont=me.getText();
		System.out.println(me_cont);
		assertTrue("mebtn not exist",me_cont.equals("我的"));
		
	}
	@Test
	public void test002(){
		driver.findElementByXPath("//*[contains(@content-desc,\"bottom_icon\")]/android.view.View[5]/android.widget.TextView").click();
		driver.findElementByXPath("//*[contains(@content-desc,\"today_day\")]/following-sibling::android.view.View[2]").click();
		String rs=driver.findElementByXPath("//*[contains(@text,\"藏A12340\")]/../following-sibling::android.view.View[1]/android.widget.TextView").getText();
		assertTrue("评价信息错误",rs.equals("检测专业"));
		
	}
	@Test
	public void test004(){
		System.out.println("执行004");
	}
	@Test
	public void test003(){
		System.out.println("执行003");
	}

}
