import java.net.*;
import java.io.*;

public class skyscannerAutoSuggest{
    private String market;
    private String currency;
    private String locale;
    private String apiKey;
    private String query;

    private static String USER_AGENT = "Mozilla/5.0";
    private String site = "http://partners.api.skyscanner.net/apiservices/autosuggest/v1.0";
	
    public skyscannerAutoSuggest(String market, String currency, String locale, String apiKey, String query){
	this.market = market;
	this.currency = currency;
	this.locale = locale;
	this.apiKey = apiKey;
	this.query = query;
	try{
	    createSession();
	    pollSession();
	} catch (Exception e){
	    System.out.println(e);
	}
    }

    public skyscannerAutoSuggest(String apiKey, String query){
	this("GB", "GBP", "en-GB", apiKey, query);
    }

    public String buildParameters(){
	String params = "/"+market+"/"+currency+"/"+locale+"?query="+query+"&apiKey="+apiKey;
	return params;
    }
    
    public void createSession() throws Exception{
	String params = buildParameters();
	URL url = new URL(site+params);

	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	con.connect();

	int responseCode = con.getResponseCode();
	System.out.println(url);
	System.out.println(responseCode);	
    }

    public String pollSession() throws Exception{
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
	return result.toString();
    }

    public static void main(String[]args){
	skyscannerAutoSuggest a = new skyscannerAutoSuggest("prtl6749387986743898559646983194", "ed"); 
    }
}
