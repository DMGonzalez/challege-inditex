package stepsDefinitions;

import io.cucumber.java.es.Dado;
import pageMethods.CommonsMethods;

public class CommonsDefinitions {
    @Dado("Que el usuario accede a Google")
    public void goToGooglePage() {
        CommonsMethods.goToBaseUrl();
    }
}
