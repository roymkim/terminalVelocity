import java.net.*;
import java.io.*;

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
    private String site = "http://partners.api.skyscanner.net/apiservices/hotels/liveprices/v2";

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
	} catch (Exception e){
	}
    }
    
    public skyscannerLiveHotel(String apiKey, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this(apiKey, "UK", "EUR", "en-GB", entityid, checkindate, checkoutdate, guests, rooms);
    }

    private boolean createSession() throws Exception{
	site+="/"+market+"/"+currency+"/"+locale+"/"+entityid+"/"+checkindate+"/"+checkoutdate+"/"+guests+"/"+rooms+"?apiKey="+apiKey;
	//System.out.println(site);
	URL url = new URL(site);
	System.out.println(url);
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	con.connect();
	int code = con.getResponseCode();
	System.out.println(code);
	return true;
    }
    
    

    public static void main(String[]args){
	skyscannerLiveHotel h = new skyscannerLiveHotel("prtl6749387986743898559646983194", "27539733", "2016-05-23", "2016-05-25", 2, 1);
    }
}
