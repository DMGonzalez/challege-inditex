package pageObjects;

import org.openqa.selenium.By;

public class GooglePage {

    public static By getInputSearch() {
        return By.xpath(".//*[@name='q']");
    }

    public static By getWikipediaResult(String href) {
        return By.xpath(".//div[@role='complementary']//a[contains(@href,'" + href + "')]");
    }

    public static By getWikipediaImg() {
        return By.xpath(".//div[@id='p-logo']//a[contains(@href,'Wikipedia:Portada')]");
    }

    public static By getHistoryLink() {
        return By.xpath(".//a[@href='#Historia']");
    }

    public static By getHistoryLabel() {
        return By.xpath(".//span[@id='Historia']//ancestor::h2//following::p[1]");
    }
}
