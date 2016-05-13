import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class skyscannerRequest {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void createSession() throws Exception {
       
	String url = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0";
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	con.setRequestMethod("POST");
	//con.setRequestMethod("User-Agent", USER_AGENT);
	//con.setRequestMethod("Accept-Language", "en-US.en;q=0.5");
	
	String urlParameters = "";
	    //"apikey=an619533267721094791408626689612&country=USA&currency=USD&locale=en-US&originplace=JFK&destinationplace=LAX&outbounddate = 2016-06-02";
	
	con.setDoOutput(true);
	
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
       
	wr.writeBytes(urlParameters);
	wr.flush();
	wr.close();
	

	int responseCode = con.getResponseCode();
	System.out.println("\nSending 'POST' request to URL: " + url);
	System.out.println("Post parameters :" + urlParameters);
	System.out.println("Response code :" + responseCode);

	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	System.out.println(response.toString());
	System.out.println("done");
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
