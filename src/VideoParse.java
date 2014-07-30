import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;


public abstract class VideoParse {

	protected static String getHTML(String urlString) {
	    HttpURLConnection conn;
	    BufferedReader rd;
	    StringBuilder result = new StringBuilder();
	    try {
	    	URL url = new URL(urlString);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line = null;
	        while ((line = rd.readLine()) != null) {
	           result.append(line);
	        }
	        rd.close();
	     } catch (IOException e) {
	        e.printStackTrace();
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	     return result.toString();
	}
	
	protected static String urldecode(String content) throws UnsupportedEncodingException {
		return URLDecoder.decode(content, "UTF-8");
	}
	
	abstract public boolean parse(String url);
	abstract public String getUrl();
	abstract public String getMediumUrl();
	abstract public String getSmallUrl();
	abstract public String getHighUrl();
	abstract public String getTitle();
	abstract public String getDescription();
	abstract public String getImageUrl();
}
