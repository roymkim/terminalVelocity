import java.net.*;
import java.io.*;
import com.eclipsesource.json.*;

public class skyscannerHotel{
    
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "http://partners.api.skyscanner.net/apiservices/hotels/liveprices/v2";

    private String apiKey;
    private String market;
    private String currency;
    private String locale;
    private String entityid;
    private String checkindate;
    private String checkoutdate;
    private int guests;
    private int rooms;

    private HotelSession sessionObj;
    private String sessionURL;

    public skyscannerHotel(String apiKey, String market, String currency, String locale, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this.apiKey = apiKey;
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.entityid = entityid;
	this.checkindate = checkindate;
	this.checkoutdate = checkoutdate;
	this.guests = guests;
	this.rooms = rooms;
    }
    
    public skyscannerHotel(String apiKey, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this(apiKey, "UK", "EUR", "en-GB", entityid, checkindate, checkoutdate, guests, rooms);
    }
    
    public String buildParameters() {
	String params = "/"+market+"/"+currency+"/"+locale+"/"+entityid+"/"+checkindate+"/"+checkoutdate+"/"+guests+"/"+rooms+"?apiKey="+apiKey;
	return params;
    }
    
    public void createSession() throws Exception{
	String params = buildParameters();
	URL urlObj = new URL(URL + params);
	HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	con.setRequestMethod("GET");
	con.connect();
	
	int responseCode = con.getResponseCode();
	if (responseCode == 200) {
	    
	    StringBuilder result = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null){
		result.append(line);
	    }
	    rd.close();

	    sessionURL = con.getHeaderField("Location");
	    sessionObj = new HotelSession(parseSessionID(sessionURL));
	    
	    System.out.println("Results found for location: " + entityid + " for dates: " + checkindate + " - " + checkoutdate);
	    System.out.println();
	}
	
    }
    
    public String pollSession() throws Exception{
	
	String params = buildParameters();
	URL urlObj = new URL("http://partners.api.skyscanner.net" + sessionURL);
	
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

    public HotelSession parseResult(String result) {
	
	JsonObject resultObj = Json.parse(result).asObject();

	JsonArray hotels_prices = resultObj.get("hotels_prices").asArray();
	JsonArray hotels = resultObj.get("hotels").asArray();
      
	
	int entry = 0;
	for(JsonValue hotel: hotels) {
	    String entryName;
	    String entryStars;
	    String entryPrice;
	    String entryHotelID;
	    String entryAgentID;
	    
	    //Hotel Name
	    entryName = hotel.asObject().getString("name", "idk");

	    //Hotel Stars
	    int stars = hotel.asObject().getInt("star_rating", 1);
	    String stringOfStars = "";
	    for (int i = 0; i < stars; i++) {
		stringOfStars += "*";
	    }
	    entryStars = stringOfStars;
	    
	    //Hotel Price
	    JsonObject hotel_price = hotels_prices.get(entry).asObject();
	    entryHotelID = Integer.toString(hotel_price.getInt("id", 0));
	    
	    JsonArray agent_prices = hotel_price.get("agent_prices").asArray();
	    entryAgentID = Integer.toString(agent_prices.get(0).asObject().getInt("id", 0));
	    entryPrice = Integer.toString(agent_prices.get(0).asObject().getInt("price_total", 0));
	 
	    HotelEntry hotelEntry = new HotelEntry(entryName, entryHotelID, entryAgentID, entryStars, entryPrice);
	    sessionObj.addEntry(hotelEntry);
	
	    entry++;
	}

	
	return sessionObj;
    }

    private String parseSessionID(String URL) {
	return URL.substring(34,174);
    }
    
}
