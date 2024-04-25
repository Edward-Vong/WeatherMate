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

    public String getQuery(String geoURL, String searchType) throws IOException {
        if (URLconnection(geoURL)) {
            String JSON = String.valueOf(transcribeJSON());
            JSONObject jsonObj;

            if (searchType == "city") {
                JSONArray jsonArr = new JSONArray(JSON);
                jsonObj = jsonArr.getJSONObject(0);
            }

            else {
                jsonObj = new JSONObject(JSON);
            }

            double[] coords = new double[2];

            coords[0] = jsonObj.getDouble("lat");
            coords[1] = jsonObj.getDouble("lon");

            URLBuilder builder = new URLBuilder();
            
            System.out.println("lat: " + coords[0] + "\nlon: " + coords[1]);
            String query = builder.getQueryUrl(coords[0], coords[1]);
            System.out.print(query + "\n");

            return query;
        }

        return null;
    }

    public HashMap<Integer, JSONObject> getWeatherHashMap(String query) throws IOException {
        //collect the response data into usable objects
        JSONObject jsonData = getJSONObj(query);
        JSONArray weatherArr = new JSONArray(jsonData.getJSONArray("list"));
        
        //initialize hashmap
        HashMap<Integer, JSONObject> weatherHashMap = new HashMap<>();
        
        //get counts of all objects in weatherArr
        int count = jsonData.getInt("cnt");

        //For every element in list, put them into HashMap with associated id value
        for(int i = 0; i < count; i++) {
            jsonData = new JSONObject(weatherArr.getJSONObject(i));
            weatherHashMap.put(i, jsonData);
        }

        //if the size of the Hash is not equal to the count of elements in the list throw error
        if(weatherHashMap.size() != count) {
            throw new RuntimeException("Did not complete forecast collection");
        }

        return weatherHashMap;
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
    
    @SuppressWarnings("deprecation")
    private boolean URLconnection(String query) throws IOException{
        try {
            this.url = new URL(query);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            conn.connect();
            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                JSONObject errorResponse = new JSONObject(String.valueOf(transcribeJSON()));
                throw new IOException("HTTP GET request failed with Error code: " + responseCode
                                    + "\n" + errorResponse.getString("message"));
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

    private JSONObject getJSONObj(String query) throws IOException {
        if (URLconnection(query)) {
            String JSONString = String.valueOf(transcribeJSON());
            JSONObject JSON = new JSONObject(JSONString);

            return JSON;
        }

        return null;
    } 
} 
