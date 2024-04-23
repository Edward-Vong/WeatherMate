package app.ContainerManagers.APIManager;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; */

public class URLBuilder {
    private static final String GEOCODE_URL = "http://api.openweathermap.org/geo/1.0/";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private String key;

    public URLBuilder() throws FileNotFoundException, IOException {
        Properties p = new Properties();
        try(InputStream input = new FileInputStream("properties/config.properties")) {
            p.load(input);
            key = p.getProperty("api_key");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        if (key == null) {
            System.out.println("Missing key");
            throw new IllegalStateException("API key is required but was not found in config.properties");
        }
    }

    public String getQueryUrl(double lon, double lat) {
        String query = WEATHER_API_URL + "?lat=" + lat + "&lon=" + lon;
        return buildUrl(query);
    }

    public String cityGeoURL(String city, String stateCode, String countryCode) {
        String geoURL = GEOCODE_URL;
    
        String formattedCity = city.replace(" ", "_");
    
        geoURL += "direct?q=" + formattedCity;
        if (!stateCode.isEmpty()) geoURL += "," + stateCode;
        if (!countryCode.isEmpty()) geoURL += "," + countryCode;
    
        return buildUrl(geoURL);
    }

    public String zipGeoUrl(int zip, String countryCode) {
        String geoURL = GEOCODE_URL;
        geoURL += "zip?zip=" + zip;
        if (countryCode != "") geoURL += "," + countryCode;

        return buildUrl(geoURL);
    }

    private String buildUrl(String query) {
        return  query + "&appid=" + key;
    }

    /*public static void main(String[] args) {
        try {
            File file = new File("API_KEY.txt");
            
            Scanner sc = new Scanner(file);
            URLBuilder url = new URLBuilder(sc.nextLine());
            sc.close();

            System.out.println(url.cityGeoURL("San Jose", "", ""));
        }

        catch (FileNotFoundException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    } */
}
