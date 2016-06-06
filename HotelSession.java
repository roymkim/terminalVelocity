import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.*;
import java.io.*;
import com.eclipsesource.json.*;

public class HotelSession implements Serializable{

    private static final String URL = "http://partners.api.skyscanner.net/apiservices/hotels/livedetails/v2/details/%s?apiKey=%s&hotelIds=%s";

    private HotelEntry[] entries;
    private int numHotels;
    private String sessionID;
    private String apiKey;
    
    public HotelSession(String apiKey, String sessionID) {
	entries = new HotelEntry[100];
	numHotels = 0;
	this.sessionID = sessionID;
	this.apiKey = apiKey;
    } 

    public boolean addEntry(HotelEntry entry) {
	if (numHotels >= entries.length) {
	    return false;
	}
        entries[numHotels] = entry;
	numHotels++;
	return true;
    }

    public void writeSession() {
	try {
	    FileOutputStream fos = new FileOutputStream("hotelSessionData.txt");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(this);
	    oos.close();
	    fos.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

    public HotelSession readSession() {
	HotelSession s = null;
	try {
	    FileInputStream fis = new FileInputStream("hotelSessionData.txt");
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    s = (HotelSession) ois.readObject();
	    ois.close();
	    fis.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
	return s;
    }

    public String getHotelDeeplink(int entryIndex) {
	String hotelID = entries[entryIndex].getHotelID();
	try {
	    URL urlObj = new URL(String.format(URL, sessionID, apiKey, hotelID));
	    
	    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	    con.setRequestMethod("GET");
	    
	    StringBuilder result = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null){
		result.append(line);
	    }
	    rd.close();
	    
	    JsonObject resultObj = Json.parse(result.toString()).asObject();
	    JsonArray hotels_prices = resultObj.get("hotels_prices").asArray();
	    JsonObject hotel_price = hotels_prices.get(0).asObject();
	    JsonArray agent_prices = hotel_price.get("agent_prices").asArray();
	    
	    return agent_prices.get(0).asObject().getString("booking_deeplink", "idk");
	    
	} catch (Exception e) {
	    System.out.println(e);
	}
	
	return "";
    }
	
    private boolean checkSessionIsValid() {
	return false;
    }

    public HotelEntry[] getEntries() {
	return entries;
    }

    public int getNumHotels() {
	return numHotels;
    }

    public String getSessionID() {
	return sessionID;
    }

}
