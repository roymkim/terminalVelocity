 import java.net.*;
import java.io.*;
import com.eclipsesource.json.*;

public class skyscannerLiveHotel{
    private final String apiKey;
    private final String market;
    private final String currency;
    private final String locale;
    private final String entityid;
    private final String checkindate;
    private final String checkoutdate;
    private final int guests;
    private final int rooms;

    private static final String USER_AGENT = "Mozilla/5.0";
    private String url = "http://partners.api.skyscanner.net/apiservices/hotels/liveprices/v2";

    public skyscannerLiveHotel(String apiKey, String market, String currency, String locale, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this.apiKey = apiKey;
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.entityid = entityid;
	this.checkindate = checkindate;
	this.checkoutdate = checkoutdate;
	this.guests = guests;
	this.rooms = rooms;
	try {
	    createSession();
	    String results = pollSession();
	    parseResult(results);
	} catch (Exception e){
	    System.out.println(e);
	}
    }
    
    public skyscannerLiveHotel(String apiKey, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this(apiKey, "UK", "EUR", "en-GB", entityid, checkindate, checkoutdate, guests, rooms);
    }

    public String buildParameters() {
	String params = "/"+market+"/"+currency+"/"+locale+"/"+entityid+"/"+checkindate+"/"+checkoutdate+"/"+guests+"/"+rooms+"?apiKey="+apiKey;
	return params;
    }

    public void createSession() throws Exception{
	String params = buildParameters();
	URL urlObj = new URL(url + params);

	HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	con.setRequestMethod("GET");
	con.connect();

	int responseCode = con.getResponseCode();
	if (responseCode == 200) {
	    System.out.println("Results found for location: " + entityid + " for dates: " + checkindate + " - " + checkoutdate);
	    System.out.println();
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

    private void parseResult(String result) {
	String[] hotelNames = new String[10];
	String[] hotelStars = new String[10];
	int[] hotelPrices = new int[10];

	JsonObject resultObj = Json.parse(result).asObject();

	JsonArray hotels_prices = resultObj.get("hotels_prices").asArray();
	JsonArray hotels = resultObj.get("hotels").asArray();
      

	int entry = 0;
	for(JsonValue hotel: hotels) {
	    //Hotel Name
	    hotelNames[entry] = hotel.asObject().getString("name", "idk");

	    //Hotel Stars
	    int stars = hotel.asObject().getInt("star_rating", 1);
	    String stringOfStars = "";
	    for (int i = 0; i < stars; i++) {
		stringOfStars += "*";
	    }
	    hotelStars[entry] = stringOfStars;
	    
	    //Hotel Price
	    JsonObject hotel_price = hotels_prices.get(entry).asObject();
	    JsonArray agent_prices = hotel_price.get("agent_prices").asArray();
	    hotelPrices[entry] = agent_prices.get(0).asObject().getInt("price_total", 1);

	    entry++;
	}

	System.out.format("%-4s%-64s%-24s%-24s%n", "#", "Hotel Name", "Star Rating", "Total Price");
	System.out.println("---------------------------------------------------------------------------------------------------");
	for (int i = 0; i < hotelNames.length; i++) {
	    String entryNum = i + ".";
	    String entryName = hotelNames[i];
	    String entryStars = hotelStars[i];
	    String entryPrice = "$" + hotelPrices[i];
	    String resultEntry = " ";
	    resultEntry += hotelNames[i] + "  ";
	    resultEntry += hotelStars[i] + "  ";
	    resultEntry += "$" + hotelPrices[i];
	    
	    System.out.format("%-4s%-64s%-24s%-24s%n", entryNum, entryName, entryStars, entryPrice);
	}

    }

    public static void main(String[]args){
	if (args.length >= 3) {
	    skyscannerLiveHotel h = new skyscannerLiveHotel("prtl6749387986743898559646983194", args[0], args[1], args[2], 2, 1);
	} else {
	skyscannerLiveHotel h = new skyscannerLiveHotel("prtl6749387986743898559646983194", "27544008", "2016-07-23", "2016-07-25", 2, 1);
	}
    }

}
