import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Map;
import java.util.LinkedHashMap;
import java.net.URLEncoder;


public class skyscannerFlight {

    private String apiKey;
    private String country;
    private String currency;
    private String locale;
    private String originplace;
    private String destinationplace;
    private String outbounddate;
    private String inbounddate;
    private String cabinclass;
    private int adults;
    private int children;
    private int infants;
    private boolean  groupPricing;

    private static final String USER_AGENT = "Mozilla/5.0";
    private String url = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0";
    private String sessionURL;

    public skyscannerLiveFlight(String apiKey, String country, String currency, String locale, String originplace, String destinationplace, String outbounddate, String inbounddate, String cabinclass, int adults, int children, int infants, boolean groupPricing) {
	this.apiKey = apiKey;
	this.country = country;
	this.currency = currency;
	this.locale = locale;
	this.originplace = originplace;
	this.destinationplace = destinationplace;
	this.outbounddate = outbounddate;
	this.inbounddate = inbounddate;
	this.cabinclass = cabinclass;
	this.adults = adults;
	this.children = children;
	this.infants = infants;
	this.groupPricing = groupPricing;
	
	try {
	    byte[] params = buildParams();
	    createSession(params);
	} catch (Exception e) {

	}
    }

    public skyscannerLiveFlight(String apiKey, String originplace, String destinationplace, String outbounddate, String inbounddate, String cabinclass, int adults, int children, int infants) {
	this(apiKey, "US", "USD", "en-US", originplace, destinationplace, outbounddate, inbounddate, cabinclass, adults, children, infants, true);
    }

    private byte[] buildParams() throws Exception{
	Map<String,Object> params = new LinkedHashMap<>();
	params.put("apiKey", apiKey);
	params.put("country", country);
       	params.put("currency",currency);
	params.put("locale", locale);
	params.put("originplace", originplace);
	params.put("destinationplace", destinationplace);
	params.put("outbounddate", outbounddate);
	params.put("inbounddate", inbounddate);
	params.put("cabinclass", cabinclass);
	params.put("adults", adults);
	params.put("children", children);
	params.put("infants", infants);
	params.put("groupPricing", groupPricing);

	StringBuilder postData = new StringBuilder();
	for (Map.Entry<String,Object> param : params.entrySet()) {
	    if (postData.length() != 0) {
		postData.append('&');
	    } 
	    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	    postData.append("=");
	    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	}

	byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	return postDataBytes;
    }
    
    private void createSession(byte[] params) throws Exception  {
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US.en;q=0.5");
	con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	con.setRequestProperty("Accept", "application/json");	
	con.setDoOutput(true);
	
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
       
	wr.write(params);
	wr.flush();
	wr.close();
	
	int responseCode = con.getResponseCode();
	System.out.println("Response code :" + responseCode);

	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	sessionURL = con.getHeaderField("Location");

	System.out.println(response.toString());
    }        

    public void pollSession() throws Exception{
	String formatKey = URLEncoder.encode(apiKey, "UTF-8");
	URL obj = new URL(sessionURL + "?apiKey=" + apiKey);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	con.setRequestMethod("GET");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setDoInput(true);
	con.setDoOutput(false);

	int responseCode = con.getResponseCode();
	System.out.println("\nSending 'GET' request to URL : " + obj);
	System.out.println("Response Code : " + responseCode);


	BufferedReader in = new BufferedReader(
					       new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	System.out.println(response.toString());

    }

    public static void main(String[] args) {
	skyscannerLiveFlight f = new skyscannerLiveFlight("an318955594945738134979842887915", "JFK-sky", "LAX-sky", "2016-06-20", "", "Economy", 1, 0, 0);
	System.out.println(f.sessionURL);
	try{
	    f.pollSession();
	} catch (Exception e){
	    
	}
    }
    
}
