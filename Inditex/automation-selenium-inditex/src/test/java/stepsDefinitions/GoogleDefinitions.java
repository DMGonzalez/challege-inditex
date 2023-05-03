package stepsDefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import pageMethods.GoogleMethods;

public class GoogleDefinitions {

    @Cuando("^Busca información de ([^\"]*)$")
    public void searchInGoogle(String searchString) {
        GoogleMethods.searchInGoogle(searchString);
    }

    @Entonces("^Visualiza la fecha de la primera automatización en ([^\"]*)$")
    public void searchHistoryOfResult(String hrefFromPage) {
        GoogleMethods.searchPageResult(hrefFromPage);
    }
}
