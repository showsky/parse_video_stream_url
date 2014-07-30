import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DialymotionParse extends VideoParse {
	
	private final static String URL = "http://www.dailymotion.com/embed/video/";
	private final static String PATTERN_INFO = "var info = \\{(.+)\\}\\}";
	private HashMap<QUALITY, String> stream = new HashMap<QUALITY, String>(3);

	@Override
	public boolean parse(String hashID) {
		String source = getHTML(URL + hashID);
		Pattern pattern = Pattern.compile(PATTERN_INFO);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			String info = matcher.group(1);
			//System.out.println(info);
			
			String temps[] = info.split(",");
			for (String temp: temps) {
				//System.out.println(temp);
				
				if ( ! stream.containsKey(QUALITY.HIGH)) {
					if (temp.indexOf("stream_h264_hd1080_url") != -1 ||
							temp.indexOf("stream_h264_hd_url") != -1) {
						stream.put(QUALITY.HIGH, checkUrl(temp));
						continue;
					}
				} else if ( ! stream.containsKey(QUALITY.MEDIUM)) {
					if (temp.indexOf("stream_h264_hq_url") != -1) {
						stream.put(QUALITY.MEDIUM, checkUrl(temp));
						continue;
					}
				} else if ( ! stream.containsKey(QUALITY.SMALL)) {
					if (temp.indexOf("stream_h264_url") != -1) {
						stream.put(QUALITY.SMALL, checkUrl(temp));
						continue;
					}
				}
			}
			
		}
		return true;
	}
	
	private String checkUrl(String temp) {
		temp = temp.replace("/", "");
		String content[] = temp.split("\":\"");
		int length = content[1].length();
		return content[1].substring(0, length -1);
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
		//TODO:
		return null;
	}

	@Override
	public String getDescription() {
		//TODO:
		return null;
	}

	@Override
	public String getImageUrl() {
		//TODO:
		return null;
	}

}
