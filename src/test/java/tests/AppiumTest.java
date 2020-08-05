package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumTest {

    AppiumDriver<MobileElement> driver = null;

    @Test
    public void testMethod() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability(MobileCapabilityType.APP, "D:\\Torrents\\app-release.apk");

        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.id("com.ruselkim.wiremocktest:id/newsButton")).click();
            List<MobileElement> news = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout"));
            Assert.assertEquals(7, news.size());
            news.get(0).click();
            String linkText = driver.findElement(By.id("com.ruselkim.wiremocktest:id/details_url")).getText();
            String author = driver.findElement(By.id("com.ruselkim.wiremocktest:id/details_author")).getText();
            String newsTitle = driver.findElement(By.id("com.ruselkim.wiremocktest:id/toolbarTitleView")).getText();
            Assert.assertEquals("https://lenta.ru/news/2020/08/05/sledili/", linkText);
            Assert.assertEquals("Инкогнито", author);
            Assert.assertEquals("СВР вела слежку за Сафроновым с осени 2019 года - Lenta.ru", newsTitle);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
}
