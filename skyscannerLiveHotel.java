import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Map;
import java.util.LinkedHashMap;
import java.net.URLEncoder;

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
	} catch (Exception e){
	}
    }
    
    public skyscannerLiveHotel(String apiKey, String entityid, String checkindate, String checkoutdate, int guests, int rooms){
	this(apiKey, "US", "USD", "en-US", entityid, checkindate, checkoutdate, guests, rooms);
    }

    private boolean createSession() throws Exception{
	url+="/"+market+"/"+currency+"/"+locale+"/"+entityid+"/"+checkindate+"/"+checkoutdate+"/"+guests+"/"+rooms+"?apiKey="+apiKey;
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	con.setRequestMethod("GET");
	con.setRequestProperty("User-Agent", USER_AGENT);    

	int responseCode = con.getResponseCode();
	System.out.println("Response code :" + responseCode);

	if (responseCode == 302){
	    return true;
	} else {
	    return false;
	}   
   }
}
