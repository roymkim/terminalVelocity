import java.net.*;
import java.io.*;
import com.eclipsesource.json.*;

public class skyscannerAutoSuggest{
    private String market;
    private String currency;
    private String locale;
    private String query;
    private String apiKey;

    private static final String USER_AGENT = "Mozilla/5.0";
    private String url = "http://partners.api.skyscanner.net/apiservices/hotels/autosuggest/v2";
    
    public skyscannerAutoSuggest(String market, String currency, String locale, String query, String apiKey){
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.query = query;
	this.apiKey = apiKey;
	try{
	    createSession();
	    String results = pollSession();
	} catch (Exception e){
	    System.out.println(e);
	}
    }

    public skyscannerAutoSuggest(String query, String apiKey){
	this("UK", "EUR", "en-GB", query, apiKey);
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
	    System.out.println("Results found");
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
	System.out.println(result);
	return result.toString();
    }

    public static void main(String[]args){
	skyscannerAutoSuggest t = new skyscannerAutoSuggest("bleh", "prtl6749387986743898559646983194");
    }
}
