package app.APIManager;

public class URLBuilder {
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private final String key;

    public URLBuilder(String key) {
        this.key = key;
    }

    public String getCityUrl(String city) {
        return buildUrl("q=" + city);
    }

    public String getCoordUrl(double lon, double lat) {
        return buildUrl("lat=" + lat + "&lon=" + lon);
    }

    public String getIdUrl(int id) {
        return buildUrl("id=" + id);
    }

    public String getZipUrlUS(int zip) {
        return buildUrl("zip=" + zip + ",us");
    }

    public String getZipUrlInternational(int zip, int countryCode) {
        return buildUrl("zip=" + zip + "," + countryCode);
    }

    private String buildUrl(String query) {
        return WEATHER_API_URL + "?" + query + "&appid=" + key;
    }
}
