package pageMethods;

import org.openqa.selenium.Keys;
import pageObjects.GooglePage;

public class GoogleMethods {

    public static void searchInGoogle(String searchString) {
        CommonsMethods.elementWaitDisplayed(GooglePage.getInputSearch());
        CommonsMethods.elementSendText(GooglePage.getInputSearch(), searchString);
        CommonsMethods.pressKey(Keys.ENTER);
    }

    public static void searchPageResult(String searchString) {
        CommonsMethods.elementWaitDisplayed(GooglePage.getWikipediaResult(searchString));
        CommonsMethods.elementClick(GooglePage.getWikipediaResult(searchString));
        CommonsMethods.elementWaitDisplayed(GooglePage.getWikipediaImg());
        CommonsMethods.checkPathFromUrl(searchString);
        CommonsMethods.scrollToElement(GooglePage.getHistoryLink());
        CommonsMethods.elementClick(GooglePage.getHistoryLink());
        CommonsMethods.elementWaitDisplayed(GooglePage.getHistoryLabel());
        String elementText = CommonsMethods.getElementText(GooglePage.getHistoryLabel());
        System.out.println("RESULTADO ENCONTRADO => " + elementText);
        CommonsMethods.takeScreenshots("Resultado encontrado");
    }
}
