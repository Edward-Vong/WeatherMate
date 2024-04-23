package app.ContainerManagers.APIManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

    // Method to test the HTTP connection and response code
    public boolean testConnection() {
        try {
            HttpURLConnection conn = (HttpURLConnection) urlString.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            conn.disconnect();
            return responseCode == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch raw data as a string
    public String fetchRawData() {
        try {
            HttpURLConnection conn = (HttpURLConnection) urlString.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(urlString.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                return informationString.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getJSONArray() {
        try {
            String rawData = fetchRawData();
            if (rawData == null) {
                return null;
            }
            JSONParser parse = new JSONParser();
            return (JSONArray) parse.parse(rawData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getJSONObject() {
        try {
            String rawData = fetchRawData();
            if (rawData == null) {
                return null;
            }
            JSONParser parse = new JSONParser();
            return (JSONObject) parse.parse(rawData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
