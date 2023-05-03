import java.io.IOException;
import java.util.*;

public class JsonCommands {

    public void getAmountsNames(String name) throws IOException {
        List<String> listNames = new ArrayList<String>();
        String[] names = name.split(",");
        for (int x = 0; x < names.length; x++) {
            String addString = names[x].replaceAll("[^a-zA-Z]", "");
            listNames.add(addString);
        }
        System.out.println("### CANTIDAD DE NOMBRES DE MASCOTAS ###");
        Set<String> unique = new HashSet<String>(listNames);
        for (String key : unique) {
            System.out.println(key + ": " + Collections.frequency(listNames, key));
        }
    }

}
