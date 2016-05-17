import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Map;
import java.util.LinkedHashMap;
import java.net.URLEncoder;
import java.sql.Date;

public class skyscannerRequest {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void createSession() throws Exception {
     
	String url = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0";
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US.en;q=0.5");
	con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	con.setRequestProperty("Accept", "application/json");
	
	Map<String,Object> params = new LinkedHashMap<>();
	params.put("apiKey", "an318955594945738134979842887915");
	params.put("country", "US");
	params.put("currency","USD");
	params.put("locale", "en-US");
	params.put("originplace", "JFK-sky");
	params.put("destinationplace", "LAX-sky");
	params.put("outbounddate", "2016-06-02");
	params.put("inbounddate", "");
	params.put("adults", 1);

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


	con.setDoOutput(true);
	
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
       
	wr.write(postDataBytes);
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

	System.out.println(response.toString());
	    }

    public static void pollSession() throws Exception {
	
	

    }

    public static void main(String[] args) {
	try {
	    createSession();
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

}

// Credit: www.mkyong.com/java/how-to-send-https-request-getpost-in-java/
