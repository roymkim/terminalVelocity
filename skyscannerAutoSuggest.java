import java.net.*;
import java.io.*;

public class skyscannerAutoSuggest{
    private String market;
    private String currency;
    private String locale;
    private String apiKey;
    private String query;

    private static String USER_AGENT = "Mozilla/5.0";
    private String site = "http://partners.api.skyscanner.net/apiservices/autosuggest/v1.0/";
	
    public skyscannerAutoSuggest(String market, String currency, String locale, String apiKey, String query){
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.apiKey = apiKey;
	this.query = query;
    }

    public skyscannerAutoSuggest(String apiKey, String query){
	this("UK", "EUR", "en-GB", apiKey, query);
    }
    
    public void createSession() throws Exception{
	site+="/"+market+"/"+currency+"/"+locale+"/?query="+query+"&apiKey="+apiKey;
	URL url = new URL(site);
	System.out.println(url);
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	con.connect();
	int code = con.getResponseCode();
	System.out.println(code);	
    }

    public void pollSession() throws Exception{
	StringBuilder result = new StringBuilder();
	URL url = new URL(site);
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String line;
	while ((line = rd.readLine()) != null){
	    result.append(line);
	}
	rd.close();
	System.out.println(result.toString());
    }

    public static void main(String[]args){
	skyscannerAutoSuggest a = new skyscannerAutoSuggest("prtl6749387986743898559646983194", "New York"); 
    }
}