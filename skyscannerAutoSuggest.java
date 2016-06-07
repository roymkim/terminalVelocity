import java.net.*;
import java.io.*;
import com.eclipsesource.json.*;
import java.util.*;

public class skyscannerAutoSuggest{
    private String market;
    private String currency;
    private String locale;
    private String query;
    private String apiKey;

    private static final String USER_AGENT = "Mozilla/5.0";
    private String url = "http://partners.api.skyscanner.net/apiservices/hotels/autosuggest/v2";    

    private ArrayList<String> entityIDs;

    public skyscannerAutoSuggest(String market, String currency, String locale, String query, String apiKey){
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.query = query;
	this.apiKey = apiKey;
	try{
	    createSession();
	    String results = pollSession();
	    parseResult(results);
	} catch (Exception e){
	    System.out.println(e);
	}
    }

    public skyscannerAutoSuggest(String query, String apiKey){
	this("US", "USD", "en-US", query, apiKey);
    }
    
    public String buildParameters(){
	String params = "/"+market+"/"+currency+"/"+locale+"/"+query+"?apikey="+apiKey;
	return params;
    }

    public void createSession() throws Exception{
	String params = buildParameters();
	URL urlObj = new URL(url + params);

	HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	con.setRequestMethod("GET");
	con.connect();

	int responseCode = con.getResponseCode();
	if (responseCode == 200){
	    
	}
    }

    public String pollSession() throws Exception{
	String params = buildParameters();
	URL urlObj = new URL(url + params);
	
	HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	con.setRequestMethod("GET");

	StringBuilder result = new StringBuilder();
	BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String line;
	while ((line = rd.readLine()) != null){
	    result.append(line);
	}
	rd.close();
	return result.toString();
    }

    public void parseResult(String result){
	ArrayList<String> cityNames = new ArrayList<String>();
	ArrayList<String> adminNames = new ArrayList<String>();
	ArrayList<String> countryNames = new ArrayList<String>();
	ArrayList<String> placeIDs = new ArrayList<String>();
	ArrayList<String> displayNames = new ArrayList<String>();
	ArrayList<String> parentPlaceIDs = new ArrayList<String>();
	entityIDs = new ArrayList<String>();

	JsonObject resultObj = Json.parse(result).asObject();
		
	JsonArray places = resultObj.get("places").asArray();
	JsonArray results = resultObj.get("results").asArray();

	for (JsonValue place : places){
	    cityNames.add(place.asObject().getString("city_name", "N/A"));
	    adminNames.add(place.asObject().getString("admin_level1", "N/A"));
	    countryNames.add(place.asObject().getString("country_name", "N/A"));
	    placeIDs.add(Integer.toString(place.asObject().getInt("place_id", 0)));
	}

	for (JsonValue res : results){
	    displayNames.add(res.asObject().getString("display_name", "N/A"));
	    parentPlaceIDs.add(Integer.toString(res.asObject().getInt("parent_place_id", 0)));
	    entityIDs.add(res.asObject().getString("individual_id", "N/A"));
	}

       
	System.out.println();
	System.out.format("%-4s%-20s%-32s%-20s%n", "#", "City", "State, Province, District", "Country");
	System.out.println("--------------------------------------------------------------------");
	

	for (int i = 0; i < cityNames.size(); i++){
	    String entryNum = i + 1 + ".";
	    String entryCity = cityNames.get(i);
	    String entryAdmin = adminNames.get(i);
	    String entryCountry = countryNames.get(i);
	    System.out.format("%-4s%-20s%-32s%-20s%n", entryNum, entryCity, entryAdmin, entryCountry);
	}
	
	System.out.println("--------------------------------------------------------------------");

    }

    public String getEntityID(int index) {
	return entityIDs.get(index);
    }

}
