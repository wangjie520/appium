package com.appium.test;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;



public class firtappium {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		AndroidDriver driver;
		
		DesiredCapabilities cap=new DesiredCapabilities();
		//设备 adb devices查看
		cap.setCapability("deviceName","127.0.0.1:62001");
		//手机系统版本
		cap.setCapability("platformVersion", "5.0");
		//测试引擎，默认Appium,API-level<17的情况下设置为selendriod
		cap.setCapability("automationName", "Appium");
		//必填，测试平台，iOS，Android，Firefox OS
		cap.setCapability("platformName", "Android");
		//apk文件路径，appium运行时会先尝试安装再开始测试，如果是安卓设置了appPackage和appActivity，则忽略Capability
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
		Thread.sleep(5000);
		
		driver.quit();

	}
}
