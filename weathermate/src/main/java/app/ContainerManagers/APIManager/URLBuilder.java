package app.ContainerManagers.APIManager;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class URLBuilder {
    private static final String GEOCODE_URL = "http://api.openweathermap.org/geo/1.0/";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private String key;

    public URLBuilder() throws FileNotFoundException, IOException {
        Properties p = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/app/config.properties")) { // Notice the leading slash
            if (input == null) {
                throw new FileNotFoundException("config.properties file not found in classpath under /app");
            }
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

    public String getQueryUrl(double lat, double lon) {
        String query = WEATHER_API_URL + "?lat=" + lat + "&lon=" + lon;
        return buildUrl(query)  + "&units=imperial&cnt=8";
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
}
