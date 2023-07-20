package Pages;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataReader {
	
	public static JSONObject testData() {

        try {
            String jsonData = new String(Files.readAllBytes(Paths.get("src/main/resources/Objects/TestAPI.json")));
            System.out.println("Json Data: "+jsonData);
            return new JSONObject(jsonData);
        } catch (Exception e) {
            System.out.println("Exception testData "+ e);
            e.printStackTrace();
        }
        return null;
    }


}
