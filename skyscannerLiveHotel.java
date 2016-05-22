import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Map;
import java.util.LinkedHashMap;
import java.net.URLEncoder;
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
	    System.out.println("Hello");
	} catch (Exception e){
	}
    }
    
    public skyscannerLiveHotel(String apiKey, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this(apiKey, "UK", "EUR", "en-GB", entityid, checkindate, checkoutdate, guests, rooms);
    }

    private boolean createSession() throws Exception{
	StringBuilder result = new StringBuilder();
	site+="/"+market+"/"+currency+"/"+locale+"/"+entityid+"/"+checkindate+"/"+checkoutdate+"/"+guests+"/"+rooms+"?apiKey="+apiKey;
	//System.out.println(site);
	URL url = new URL(site);
	System.out.println(url);
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	con.connect();
	int code = con.getResponseCode();
	System.out.println(code);
BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String line;
	while ((line = rd.readLine()) != null){
	    result.append(line);
	}
	rd.close();
	con.connect();
	System.out.println("test");
	//int code = con.getResponseCode();
	
	//con.setRequestProperty("User-Agent", USER_AGENT);    

	//int responseCode = con.getResponseCode();
	System.out.println(code);
	/*System.out.println("Response code :" + responseCode);

	if (responseCode == 302){
	    return true;
	} else {
	    return false;
	    } */
	return true;
    }
    
    

    public static void main(String[]args){
	skyscannerLiveHotel h = new skyscannerLiveHotel("prtl6749387986743898559646983194", "27539733", "2015-05-23", "2015-05-25", 2, 1);
    }
}
