package app.ContainerManagers.APIManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.net.URL;
import java.util.HashMap;

public class APIConnector {

    private URL url;
    private HttpURLConnection conn;

    public double[] findCoordinate(String geoURL) throws IOException {
        if (URLconnection(geoURL)) {
            String JSON = String.valueOf(transcribeJSON());
            JSONArray jsonArr = new JSONArray(JSON);
            JSONObject jsonObj = jsonArr.getJSONObject(0);

            double[] coords = new double[2];

            coords[0] = jsonObj.getDouble("lat");
            coords[1] = jsonObj.getDouble("lon");

            return coords;
        }

        return null;
    }

    public HashMap<Integer, JSONObject> getWeatherHashMap(String geoQuery) throws IOException {
        JSONArray weatherArr = getWeatherArr(geoQuery);
        JSONObject jsonData = new JSONObject(weatherArr);
        HashMap<Integer, JSONObject> weatherHashMap = new HashMap<>();
        
        int count = jsonData.getInt("cnt");

        for(int i = 0; i < count; i++) {
            jsonData = new JSONObject(weatherArr.getJSONObject(i));
            weatherHashMap.put(i, jsonData);
        }

        if(weatherHashMap.size() != count) {
            throw new RuntimeException("Did not complete forecast collection");
        }

        return weatherHashMap;
    }

    @SuppressWarnings("deprecation")
    private boolean URLconnection(String query) throws IOException{
        try {
            this.url = new URL(query);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            conn.connect();
            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP GET request failed with Error code: " + responseCode);
            }

            else {
                return true;
            }

        }

        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        catch(Exception e) {
            e.printStackTrace();
            throw new IOException("Failed to connect due to an unspecified error.", e);
        }
    }

    private StringBuilder transcribeJSON() throws IOException {
        StringBuilder informationString = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while((line = reader.readLine()) != null) {
                    informationString.append(line);
                }
            }

            finally {
                conn.disconnect();
            }

        return informationString;
    }

    private JSONArray transcribeWeather(String query) throws IOException {
        if (URLconnection(query)) {
            String JSONString = String.valueOf(transcribeJSON());
            JSONObject JSON = new JSONObject(JSONString);

            return new JSONArray(JSON.getJSONObject("list"));
        }

        return null;
    }

    private JSONArray getWeatherArr(String geoQuery) throws IOException {
        URLBuilder builder = new URLBuilder();

        double[] coords = findCoordinate(geoQuery);
        
        System.out.println("lat: " + coords[0] + "\nlon: " + coords[1]);
        String query = builder.getQueryUrl(coords[0], coords[1]);
        System.out.print(query);
    
        return transcribeWeather(query);
    }
} 
