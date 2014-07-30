import java.io.UnsupportedEncodingException;
import java.util.HashMap;



public class YoutubeParse extends VideoParse {
	
	private final static String URL = "http://www.youtube.com/get_video_info?video_id=";
	private final static String TITLE = "title";
	private final static String IMAGE_URL = "thumbnail_url";
	private final static String STREAM_MAP = "url_encoded_fmt_stream_map";
	private enum QUALITY {
		HIGH,
		MEDIUM,
		SMALL,
	}
	private HashMap<String, String> parameter = new HashMap<String, String>();
	private HashMap<QUALITY, String> stream = new HashMap<QUALITY, String>(3);
	
	@Override
	public boolean parse(String youtubeID) {
		String source = getHTML(URL + youtubeID);
		String pairs[] = source.split("&");
		
		for (String pair: pairs) {
			//System.out.println(pair);
			String temp[] = pair.split("=");
			
			String key = temp[0];
			String value = null;
			try {
				value = temp[1];
			} catch (ArrayIndexOutOfBoundsException e) {}
			
			parameter.put(key, value);
		}
		
		String streamMap = parameter.get(STREAM_MAP);
		String decode = null;
		try {
			decode = urldecode(streamMap);
			//System.out.println(decode);
			String streamContents[] = decode.split(",");
			for (String content: streamContents) {
				//System.out.println(content);
				
				if ( ! stream.containsKey(QUALITY.HIGH)) {
					if (content.indexOf("hd720") != 1) {
						stream.put(QUALITY.HIGH, checkUrl(content));
					}
				} else if ( ! stream.containsKey(QUALITY.MEDIUM)) {
					if (content.indexOf("medium") != 1) {
						stream.put(QUALITY.MEDIUM, checkUrl(content));
					}
				} else if ( ! stream.containsKey(QUALITY.SMALL)) {
					if (content.indexOf("small") != 1) {
						stream.put(QUALITY.SMALL, checkUrl(content));
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String checkUrl(String content) {
		String temps[] = content.split("&");
		String url = null;
		for (String temp: temps) {
			if (temp.indexOf("url") != -1) {
				try {
					url = urldecode(temp.split("=")[1]);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return url;
	}
	
	@Override
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
	
	@Override
	public String getMediumUrl() {
		return stream.get(QUALITY.MEDIUM);
	}
	
	@Override
	public String getSmallUrl() {
		return stream.get(QUALITY.SMALL);
	}
	
	@Override
	public String getHighUrl() {
		return stream.get(QUALITY.HIGH);
	}

	@Override
	public String getTitle() {
		String title = parameter.get(TITLE);
		if (title != null) {
			try {
				title = urldecode(title);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return title;
	}

	@Override
	public String getDescription() {
		//TODO:
		return null;
	}

	@Override
	public String getImageUrl() {
		String imageUrl = parameter.get(IMAGE_URL);
		if (imageUrl != null) {
			try {
				imageUrl = urldecode(imageUrl);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return imageUrl;
	}
}
