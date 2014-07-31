import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;


public abstract class VideoParse {
	
	protected HashMap<QUALITY, String> stream = new HashMap<QUALITY, String>(3);
	protected enum QUALITY {
		HIGH,
		MEDIUM,
		SMALL,
	}

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
	
	public String getMediumUrl() {
		return stream.get(QUALITY.MEDIUM);
	}
	
	public String getSmallUrl() {
		return stream.get(QUALITY.SMALL);
	}
	
	public String getHighUrl() {
		return stream.get(QUALITY.HIGH);
	}
	
	public String getUrl() {
		String url = null;
		if (getHighUrl() != null) {
			url = getHighUrl();
		} else if (getMediumUrl() != null) {
			url = getMediumUrl();
		} else if (getSmallUrl() != null) {
			url = getSmallUrl();
		}
		return url;
	}
	
	abstract public boolean parse(String hashID);
	abstract public String getTitle();
	abstract public String getDescription();
	abstract public String getImageUrl();
}
