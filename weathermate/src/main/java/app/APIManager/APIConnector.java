package app.APIManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APIConnector {
    
    private final URL urlString;

    @SuppressWarnings("deprecation")
    public APIConnector(String urlString) throws MalformedURLException {
        this.urlString = new URL(urlString);
    }

    public JSONArray getJSONArray() {
        try {
                HttpURLConnection conn = 
                    (HttpURLConnection) urlString.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                int responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responseCode);
                } 
                
                else {
                    StringBuilder informationString = new StringBuilder();
                    Scanner scanner = new Scanner(urlString.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }

                    scanner.close();

                    JSONParser parse = new JSONParser();

                    return (JSONArray) parse.parse(String.valueOf(informationString));
            }
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

