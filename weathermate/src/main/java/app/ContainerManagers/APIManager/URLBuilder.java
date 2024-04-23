package app.ContainerManagers.APIManager;

/* import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; */

public class URLBuilder {
    private static final String GEOCODE_URL = "http://api.openweathermap.org/geo/1.0/";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private final String key;

    public URLBuilder(String key) {
        this.key = key;
    }

    public String getQueryUrl(double lon, double lat) {
        String query = WEATHER_API_URL + "?lat=" + lat + "&lon=" + lon;
        return buildUrl(query);
    }

    public String cityGeoURL(String city, String stateCode, String countryCode) {
        String geoURL = GEOCODE_URL;

        geoURL += "direct?q=" + city;
        if (stateCode != "") geoURL += "." + stateCode;
        if (countryCode != "") geoURL += "," + countryCode;

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
